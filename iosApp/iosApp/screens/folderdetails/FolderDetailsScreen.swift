import SwiftUI
import Shared

extension FolderDetailsScreen {
    
    @MainActor
    class ViewModelWrapper: ObservableObject {
        let folderId: Int64
        let folderDetailsViewModel: FolderDetailsViewModel
        @Published var uiState: FolderDetailsUiState
        @Published var event: FolderDetailsEvent

        init(folderId: Int64) {
            self.folderId = folderId
            self.folderDetailsViewModel = ViewModelInjector().folderDetailsViewModel(folderId: folderId)
            self.uiState = folderDetailsViewModel.uiState.value
            self.event = folderDetailsViewModel.event.value
        }

        func startObserving() {
            Task {
                for await uiState in folderDetailsViewModel.uiState {
                    self.uiState = uiState
                }
            }
            Task {
                for await event in folderDetailsViewModel.event {
                    self.event = event
                }
            }
        }
    }
}

struct FolderDetailsScreen: View {
    @StateObject private var viewModel: ViewModelWrapper
    @Environment(\.dismiss) private var dismiss

    @State private var showDeleteFolderAlertDialog = false
    @State private var showUpdateFolderDetailsBottomSheet = false
    @State private var showFilePicker = false
    @State private var showFileShareBottomSheet = false
    @Binding var path: [TripsNavigation]
        
    init(folderId: Int64, path: Binding<[TripsNavigation]>) {
        _viewModel = StateObject(wrappedValue: ViewModelWrapper(folderId: folderId))
        _path = path
    }
    
    var body: some View {
        NavigationView {
            ZStack {
                Color(red: 0.95, green: 0.95, blue: 0.95)
                    .ignoresSafeArea()
                
                if viewModel.uiState.isLoading {
                    FolderDetailsLoading()
                } else {
                    FolderDetailsContent(
                        title: viewModel.uiState.title,
                        folderType: viewModel.uiState.type,
                        showFullButton: viewModel.uiState.showFullButton,
                        filesUris: viewModel.uiState.files.map { $0.fileUri },
                        onDeleteFileClick: { index in
                            viewModel.folderDetailsViewModel.onDeleteFile(index: Int32(index))
                        },
                        onAddFileClick: {
                            showFilePicker = true
                        },
                        onFileClick: { index in
                            viewModel.folderDetailsViewModel.onDisplayFilePreview(index: Int32(index))
                        },
                        onFolderDetailsClick: {
                            showUpdateFolderDetailsBottomSheet = true
                        }
                    )
                    .padding(.top, 16)
                }
            }
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    IconButton(
                        icon: Icons.leftArrow,
                        action: { dismiss() },
                        intent: .secondary
                    )
                }
                
                ToolbarItem(placement: .navigationBarTrailing) {
                    HStack(spacing: 4) {
                        IconButton(
                            icon: Icons.share,
                            action: { showFileShareBottomSheet = true },
                            intent: .secondary
                        )
                        
                        IconButton(
                            icon: Icons.trash,
                            action: { showDeleteFolderAlertDialog = true },
                            intent: .secondaryDanger
                        )
                    }
                }
            }
        }
        .navigationBarBackButtonHidden(true)
        .alert("Suppression du dossier \(viewModel.uiState.title)", isPresented: $showDeleteFolderAlertDialog) {
            Button(text: "Annuler", action: {})
            Button(text: "Supprimer", action: {
                viewModel.folderDetailsViewModel.onDeleteFolder()
            })
        } message: {
            Text(text: "Confirmez-vous la suppression du dossier \(viewModel.uiState.title) ? Cette action est irréversible.", intent: .body)
        }
        .sheet(isPresented: $showUpdateFolderDetailsBottomSheet) {
            UpdateFolderDetailsScreen(
                folderId: viewModel.folderId,
                onDismiss: {
                    showUpdateFolderDetailsBottomSheet = false
                }
            )
            .presentationDetents([.medium])
            .presentationCornerRadius(32)
        }
        .sheet(isPresented: $showFilePicker) {
            FilePicker(
                onPick: { urls in
                    if let url = urls.first {
                        viewModel.folderDetailsViewModel.onAddNewFile(fileUri: url.path, fileName: url.lastPathComponent)
                    }
                    self.showFilePicker = false
                },
                onDismiss: {
                    self.showFilePicker = false
                }
            )
        }
        .sheet(isPresented: $showFileShareBottomSheet) {
            let images = viewModel.uiState.files.compactMap { file in
                if let url = getImageURL(uri: file.fileUri),
                   let image = UIImage(contentsOfFile: url.path) {
                    return image
                }
                return nil
            }
            FileShareSheet(activityItems: images)
        }
        .onReceive(viewModel.$event) { event in
            switch event {
            case is FolderDetailsEvent.Undefined:
                break
                
            case let clickEvent as FolderDetailsEvent.ClickOnFile:
                let filePreviewData = clickEvent.filePreviewData
                path.removeAll { navigation in
                    if case .filePreview = navigation {
                        return true
                    }
                    return false
                }
                path.append(TripsNavigation.filePreview(filePreviewData: filePreviewData))
                viewModel.folderDetailsViewModel.onDispose()
                break
                
            case let deleteEvent as FolderDetailsEvent.DeleteFile:
                let fileToDeleteUri = deleteEvent.fileUri
                deleteImageFile(uri: fileToDeleteUri)
                viewModel.folderDetailsViewModel.onDispose()
                break
                
            case let deleteEvent as FolderDetailsEvent.DeleteFolder:
                let filesToDeleteUris = deleteEvent.filesToDeleteUris
                filesToDeleteUris.forEach { deleteImageFile(uri: $0) }
                dismiss()
                viewModel.folderDetailsViewModel.onDispose()
                break
                
            default:
                break
            }
        }
        .onAppear {
            viewModel.startObserving()
        }
    }
}

struct FolderDetailsLoading: View {
    var body: some View {
        ProgressView()
            .progressViewStyle(CircularProgressViewStyle())
    }
}

struct FolderDetailsContent: View {
    let title: String
    let folderType: FolderType?
    let showFullButton: Bool
    let filesUris: [String]
    let onDeleteFileClick: (Int) -> Void
    let onAddFileClick: () -> Void
    let onFileClick: (Int) -> Void
    let onFolderDetailsClick: () -> Void
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                VStack(alignment: .leading, spacing: 8) {
                    if let folderTypeIntent = mapFolderTypeToIntent(folderType) {
                        FolderTypeBadge(intent: folderTypeIntent)
                    }
                    Text(text: title, intent: .h1)
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                .onTapGesture { onFolderDetailsClick() }

                Spacer()
                    .frame(height: 16)

                if showFullButton {
                    FolderAddButton(onClick: onAddFileClick)
                        .transition(.opacity)
                } else {
                    FilesLazyGrid(
                        onDeleteFileClick: onDeleteFileClick,
                        onAddFileClick: onAddFileClick,
                        onFileClick: onFileClick,
                        filesUris: filesUris
                    )
                    .transition(.opacity)
                }
            }
            .animation(.easeInOut(duration: 0.2), value: showFullButton)
            .padding()
        }
    }
}
