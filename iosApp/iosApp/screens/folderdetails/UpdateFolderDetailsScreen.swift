import SwiftUI
import Shared

extension UpdateFolderDetailsScreen {
    
    @MainActor
    class ViewModelWrapper: ObservableObject {
        let folderId: Int64
        let folderDetailsViewModel: FolderDetailsViewModel
        @Published var uiState: UpdateFolderDetailsUiState

        init(folderId: Int64, folderDetailsViewModel: FolderDetailsViewModel) {
            self.folderId = folderId
            self.folderDetailsViewModel = folderDetailsViewModel
            self.uiState = folderDetailsViewModel.uiState.value.updateFolderDetailsUiState
        }

        func startObserving() {
            Task {
                for await uiState in folderDetailsViewModel.uiState {
                    self.uiState = uiState.updateFolderDetailsUiState
                }
            }
        }
    }
}

struct UpdateFolderDetailsScreen: View {
    @StateObject private var viewModel: ViewModelWrapper
    @Environment(\.dismiss) private var dismiss
    
    init(folderId: Int64, onDismiss: @escaping () -> Void) {
        let folderDetailsViewModel = ViewModelInjector().folderDetailsViewModel(folderId: folderId)
        _viewModel = StateObject(wrappedValue: ViewModelWrapper(folderId: folderId, folderDetailsViewModel: folderDetailsViewModel))
    }
    
    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                TextField(
                    value: Binding(
                        get: { viewModel.uiState.title },
                        set: { viewModel.folderDetailsViewModel.onUpdateTitle(newTitle: $0) }
                    ),
                    label: "Titre du dossier",
                    leadingIcon: Icons.folder
                )
                
                FolderTypeSelector(
                    selectedFolderType: mapFolderTypeToIntent(viewModel.uiState.selectedFolderType),
                    onFolderTypeClick: {  type in
                        let folderType = mapIntentToFolderType(type)
                        viewModel.folderDetailsViewModel.onUpdateFolderType(folderType: folderType)
                    }
                )
                
                Spacer()
            }
            .padding(.top, 32)
            .padding(.horizontal, 16)
            .background(Color(uiColor: UIColor(red: 0.95, green: 0.95, blue: 0.95, alpha: 1.0)))
            .navigationBarTitleDisplayMode(.inline)
            .toolbar {
                ToolbarItem(placement: .navigationBarLeading) {
                    IconButton(
                        icon: Icons.close,
                        action: { dismiss() },
                        intent: .secondary
                    )
                    .padding(.top, 24)
                }
                
                ToolbarItem(placement: .navigationBarTrailing) {
                    IconButton(
                        icon: Icons.check,
                        action: {
                            viewModel.folderDetailsViewModel.onUpdateFolder()
                            dismiss()
                        },
                        isEnabled: viewModel.uiState.nextButtonEnabled
                    )
                    .padding(.top, 24)
                }
            }
        }
        .navigationBarBackButtonHidden(true)
        .onAppear {
            viewModel.startObserving()
        }
    }
} 
