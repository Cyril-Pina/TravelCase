import SwiftUI
import Shared

extension FilePreviewScreen {

    @MainActor
    class ViewModelWrapper: ObservableObject {
        let filePreviewViewModel: FilePreviewViewModel
        @Published var uiState: FilePreviewUiState
        @Published var event: FilePreviewEvent

        init(filePreviewData: FilePreviewData) {
            self.filePreviewViewModel = ViewModelInjector().filePreviewViewModel(filePreviewData: filePreviewData)
            self.uiState = filePreviewViewModel.uiState.value
            self.event = filePreviewViewModel.event.value
        }

        func startObserving() {
            Task {
                for await uiState in filePreviewViewModel.uiState {
                    self.uiState = uiState
                }
            }
            Task {
                for await event in filePreviewViewModel.event {
                    self.event = event
                }
            }
        }
    }
}

struct FilePreviewScreen: View {
    @StateObject private var viewModel: ViewModelWrapper
    @Environment(\.dismiss) private var dismiss
    
    @State private var selectedIndex: Int
    @State private var showDeleteFileAlertDialog = false
    @State private var showFileShareBottomSheet = false

    init(filePreviewData: FilePreviewData) {
        _viewModel = StateObject(wrappedValue: ViewModelWrapper(filePreviewData: filePreviewData))
        _selectedIndex = State(initialValue: Int(filePreviewData.initialIndex))
    }

    var body: some View {
        NavigationView {
            ZStack(alignment: .bottom) {
                TabView(selection: $selectedIndex) {
                    ForEach(Array(viewModel.uiState.filesUris.enumerated()), id: \.element.id) { index, fileUri in
                        if let url = getImageURL(uri: fileUri.fileUri),
                           let image = UIImage(contentsOfFile: url.path) {
                            Image(uiImage: image)
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                                .clipped()
                                .tag(index)
                        } else {
                            Color.gray
                        }
                    }
                }
                
                VStack(alignment: .center, spacing: 8) {
                    FilePreviewPageIndicator(
                        currentPage: selectedIndex + 1,
                        totalPages: viewModel.uiState.filesUris.count
                    )
                    
                    FilePreviewBottomBar(
                        onShareClick: {
                            showFileShareBottomSheet = true
                        },
                        onDeleteClick: {
                            showDeleteFileAlertDialog = true
                        }
                    )
                    .padding(.bottom, 16)
                    .padding(.horizontal, 16)
                }
            }
            .sheet(isPresented: $showFileShareBottomSheet) {
                let file = viewModel.uiState.filesUris[Int(selectedIndex)]
                if let url = getImageURL(uri: file.fileUri),
                   let image = UIImage(contentsOfFile: url.path) {
                    FileShareSheet(activityItems: [image])
                }
            }
            .alert("Suppression de la page \(selectedIndex + 1)", isPresented: $showDeleteFileAlertDialog) {
                Button(text: "Annuler", action: {})
                Button(text: "Supprimer", action: {
                    viewModel.filePreviewViewModel.onDeleteFile(index: Int32(selectedIndex))
                })
            } message: {
                Text(text: "Confirmez-vous la suppression de la page \(selectedIndex + 1) ? Cette action est irréversible.", intent: .body)
            }
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    IconButton(icon: Icons.leftArrow, action: { dismiss() }, intent: .secondary)
                }
            }
            .toolbarBackground(.hidden, for: .navigationBar)
            .tabViewStyle(.page(indexDisplayMode: .always))
        }
        .navigationBarBackButtonHidden(true)
        .onAppear {
            viewModel.startObserving()
        }
        .onChange(of: viewModel.uiState.filesUris) { filesUris in
            if filesUris.isEmpty {
                return
            }
            
            if selectedIndex >= filesUris.count {
                selectedIndex = filesUris.count - 1
            }
        }
        .onReceive(viewModel.$event) { event in
            switch event {
            case is FilePreviewEvent.EmptyFilesUris:
                dismiss()
                viewModel.filePreviewViewModel.onDispose()

            case is FilePreviewEvent.Undefined:
                break

            default:
                break
            }
        }
    }
}

struct FilePreviewPageIndicator: View {
    let currentPage: Int
    let totalPages: Int
    
    var body: some View {
        Badge(text: "\(currentPage)/\(totalPages)")
            .shadow(color: .black.opacity(0.1), radius: 4, x: 0, y: 2)
    }
}

struct FilePreviewBottomBar: View {
    let onShareClick: () -> Void
    let onDeleteClick: () -> Void
    
    var body: some View {
        HStack(spacing: 4) {
            FilePreviewActionButtonView(
                action: .share,
                onClick: onShareClick
            )
            .frame(maxWidth: .infinity)
            .onTapGesture { onShareClick() }
            
            FilePreviewActionButtonView(
                action: .delete,
                onClick: onDeleteClick
            )
            .frame(maxWidth: .infinity)
            .onTapGesture { onDeleteClick() }
        }
        .frame(maxWidth: .infinity)
        .padding(.all, 16)
        .background(Color.white)
        .clipShape(Capsule())
        .shadow(color: .black.opacity(0.1), radius: 4, x: 0, y: 2)
    }
}

struct FilePreviewActionButtonView: View {
    let action: FilePreviewActionButton
    let onClick: () -> Void
    
    var body: some View {
        VStack(spacing: 4) {
            Image(action.iconName)
                .font(.system(size: 14))
            Text(text: action.title, intent: .smallBold)
        }
    }
}
