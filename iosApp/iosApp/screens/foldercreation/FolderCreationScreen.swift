import SwiftUI
import Shared

extension FolderCreationScreen {
    
    @MainActor
    class ViewModelWrapper: ObservableObject {
        let folderCreationViewModel: FolderCreationViewModel
        @Published var uiState: FolderCreationUiState
        @Published var event: FolderCreationEvent

        init(tripId: Int64) {
            self.folderCreationViewModel = ViewModelInjector().folderCreationViewModel(tripId: tripId)
            self.uiState = folderCreationViewModel.uiState.value
            self.event = folderCreationViewModel.event.value
        }

        func startObserving() {
            Task {
                for await uiState in folderCreationViewModel.uiState {
                    self.uiState = uiState
                }
            }
            Task {
                for await event in folderCreationViewModel.event {
                    self.event = event
                }
            }
        }
    }
}

struct FolderCreationScreen: View {
    @StateObject private var viewModel: ViewModelWrapper
    @Environment(\.dismiss) private var dismiss
    @State private var showFilePicker = false
    
    init(tripId: Int64) {
        _viewModel = StateObject(wrappedValue: ViewModelWrapper(tripId: tripId))
    }

    var body: some View {
        NavigationView {
            FolderCreationContent(
                uiState: viewModel.uiState,
                selectedFolderType: mapFolderTypeToIntent(viewModel.uiState.selectedFolderType),
                onFolderTypeClick: { type in
                    let folderType = mapIntentToFolderType(type)
                    viewModel.folderCreationViewModel.onUpdateFolderType(folderType: folderType)
                },
                onUpdateTitle: { title in
                    viewModel.folderCreationViewModel.onUpdateTitle(newTitle: title)
                },
                onAddFileClick: { showFilePicker = true },
                onDeleteFileClick: { index in
                    viewModel.folderCreationViewModel.onDeleteFile(index: Int32(index))
                }
            )
            .padding(.top, 32)
            .padding(.horizontal, 16)
            .background(Color(uiColor: UIColor(red: 0.95, green: 0.95, blue: 0.95, alpha: 1.0)))
            .toolbar {
                ToolbarItem(placement: .topBarLeading) {
                    IconButton(
                        icon: Icons.close,
                        action: {
                            viewModel.folderCreationViewModel.onBackClick()
                        },
                        intent: .secondary
                    )
                    .padding(.top, 24)
                }
                
                ToolbarItem(placement: .topBarTrailing) {
                    IconButton(
                        icon: Icons.check,
                        action: {
                            viewModel.folderCreationViewModel.addFolder()
                            dismiss()
                        },
                        isEnabled: viewModel.uiState.nextButtonEnabled
                    )
                    .padding(.top, 24)
                }
            }
        }
        .sheet(isPresented: $showFilePicker) {
            FilePicker(
                onPick: { urls in
                    if let url = urls.first {
                        viewModel.folderCreationViewModel.onAddNewFile(fileUri: url.path, fileName: url.lastPathComponent)
                    }
                    self.showFilePicker = false
                },
                onDismiss: {
                    self.showFilePicker = false
                },
                autoDismiss: false
            )
        }
        .onReceive(viewModel.$event) { event in
            switch event {
            case is FolderCreationEvent.Undefined:
                break
                
            case let deleteEvent as FolderCreationEvent.DeleteFile:
                let fileToDeleteUri = deleteEvent.fileToDeleteUri
                deleteImageFile(uri: fileToDeleteUri)
                viewModel.folderCreationViewModel.onDispose()
                break
                
            case let backEvent as FolderCreationEvent.BackClick:
                let filesToDeleteUris = backEvent.filesToDeleteUris
                filesToDeleteUris.forEach { deleteImageFile(uri: $0) }
                dismiss()
                viewModel.folderCreationViewModel.onDispose()
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

private struct FolderCreationContent: View {
    let uiState: FolderCreationUiState
    let selectedFolderType: FolderTypeIntent?
    let onFolderTypeClick: (FolderTypeIntent) -> Void
    let onUpdateTitle: (String) -> Void
    let onAddFileClick: () -> Void
    let onDeleteFileClick: (Int) -> Void
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                FolderTypeSelector(
                    selectedFolderType: selectedFolderType,
                    onFolderTypeClick: onFolderTypeClick
                )
                
                if uiState.showTitleTextField {
                    VStack(alignment: .leading, spacing: 8) {
                        TextField(
                            value: Binding(
                                get: { uiState.title },
                                set: onUpdateTitle
                            ),
                            label: "Titre du dossier",
                            leadingIcon: Icons.folder,
                            isSingleLine: true
                        )
                        .padding(.top, 16)
                        
                        if uiState.showFullButton {
                            FolderAddButton(onClick: onAddFileClick)
                                .transition(.opacity)
                        } else {
                            FilesLazyGrid(
                                onDeleteFileClick: onDeleteFileClick,
                                onAddFileClick: onAddFileClick,
                                onFileClick: { _ in },
                                filesUris: uiState.files.map { $0.fileUri })
                                .transition(.opacity)
                        }
                    }
                    .transition(.move(edge: .top).combined(with: .opacity))
                }
                Spacer()
            }
            .frame(maxWidth: .infinity)
            .animation(.easeInOut(duration: 0.2), value: uiState.showTitleTextField)
            .animation(.easeInOut(duration: 0.2), value: uiState.showFullButton)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
    }
}

private let folderTypes: [FolderTypeIntent] = [
    FolderTypeIntent.ticket,
    FolderTypeIntent.accommodation,
    FolderTypeIntent.vehicleRental,
    FolderTypeIntent.other
]

struct FolderTypeSelector: View {
    let selectedFolderType: FolderTypeIntent?
    let onFolderTypeClick: (FolderTypeIntent) -> Void
    
    var body: some View {
        FlowLayout(spacing: 8) {
            ForEach(folderTypes, id: \.self) { type in
                folderTypeBadge(for: type)
            }
        }
    }
    
    private func folderTypeBadge(for type: FolderTypeIntent) -> some View {
        FolderTypeBadge(
            intent: type,
            clickable: true,
            onClick: { onFolderTypeClick(type) }
        )
        .opacity(selectedFolderType == type ? 1 : 0.3)
    }
}
