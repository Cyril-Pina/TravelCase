import SwiftUI
import Shared

extension TripDetailsScreen {
    
    @MainActor
    class ViewModelWrapper: ObservableObject {
        let tripId: Int64
        let tripDetailsViewModel: TripDetailsViewModel
        @Published var uiState: TripDetailsUiState
        @Published var event: TripDetailsEvent

        init(tripId: Int64) {
            self.tripId = tripId
            self.tripDetailsViewModel = ViewModelInjector().tripDetailsViewModel(tripId: tripId)
            self.uiState = tripDetailsViewModel.uiState.value
            self.event = tripDetailsViewModel.event.value
        }

        func startObserving() {
            Task {
                for await uiState in tripDetailsViewModel.uiState {
                    withAnimation {
                        self.uiState = uiState
                    }
                }
            }
            Task {
                for await event in tripDetailsViewModel.event {
                    self.event = event
                }
            }
        }
    }
}

struct TripDetailsScreen: View {
    @StateObject private var viewModel: ViewModelWrapper
    @Environment(\.dismiss) private var dismiss
    @Binding var path: [TripsNavigation]
    @State private var showDeleteTripAlertDialog = false
    @State private var showUpdateTripDetailsBottomSheet = false
    @State private var showBannerPicker = false
    @State private var showFolderCreationBottomSheet = false
    
    init(tripId: Int64, path: Binding<[TripsNavigation]>) {
        _viewModel = StateObject(wrappedValue: ViewModelWrapper(tripId: tripId))
        self._path = path
    }
    
    var body: some View {
        TripDetailsContent(
            trip: viewModel.uiState.tripUi,
            folders: viewModel.uiState.tripDetailsFoldersUiState.folders,
            isFoldersLoading: viewModel.uiState.isFoldersLoading,
            onTripDetailsClick: {
                showUpdateTripDetailsBottomSheet = true
            },
            onSearchForFolder: { query in
                viewModel.tripDetailsViewModel.onSearchForFolder(query: query)
            },
            onAddFolderClick: {
                showFolderCreationBottomSheet = true
            },
            onFolderClick: { folderId in
                path.append(TripsNavigation.folderDetails(folderId: folderId))
            },
            onFileClick: { folderId, fileIndex, fileUri in
                viewModel.tripDetailsViewModel.onDisplayFilePreview(folderId: folderId, index: Int32(fileIndex), fileUri: fileUri)
            }
        )
        .navigationBarBackButtonHidden(true)
        .toolbar {
            ToolbarItem(placement: .navigationBarLeading) {
                IconButton(icon: Icons.leftArrow, action: { dismiss() }, intent: .secondary)
            }
            ToolbarItem(placement: .navigationBarTrailing) {
                HStack(spacing: 4) {
                    IconButton(
                        icon: Icons.landscape,
                        action: { showBannerPicker = true },
                        intent: .secondary)
                    
                    IconButton(
                        icon: Icons.trash,
                        action: { showDeleteTripAlertDialog = true },
                        intent: .secondaryDanger
                    )
                }
            }
        }
        .toolbarBackground(.hidden, for: .navigationBar)
        .alert("Suppression du voyage \(viewModel.uiState.tripUi.title)", isPresented: $showDeleteTripAlertDialog) {
            Button(text: "Annuler", action: {})
            Button(text: "Supprimer", action: {
                viewModel.tripDetailsViewModel.onDeleteTrip()
            })
        } message: {
            Text(text: "Confirmez-vous la suppression du voyage \(viewModel.uiState.tripUi.title) ? Cette action est irréversible.", intent: .body)
        }
        .sheet(isPresented: $showFolderCreationBottomSheet) {
            FolderCreationScreen(tripId: viewModel.tripId)
                .presentationCornerRadius(32)
        }
        .sheet(isPresented: $showUpdateTripDetailsBottomSheet) {
            UpdateTripDetailsScreen(
                tripId: viewModel.tripId,
                onDismiss: {
                    showUpdateTripDetailsBottomSheet = false
                }
            )
            .presentationDetents([.medium])
            .presentationCornerRadius(32)
        }
        .onChange(of: showBannerPicker) { showBannerPicker in
            if showBannerPicker {
                if let windowScene = UIApplication.shared.connectedScenes.first as? UIWindowScene,
                   let rootViewController = windowScene.windows.first?.rootViewController {
                    let filePicker = FilePicker(
                        onPick: { urls in
                            if let url = urls.first {
                                viewModel.tripDetailsViewModel.onUpdateBanner(fileUri: url.path)
                            }
                            self.showBannerPicker = false
                        },
                        onDismiss: {
                            self.showBannerPicker = false
                        }
                    )
                    let hostingController = UIHostingController(rootView: filePicker)
                    hostingController.modalPresentationStyle = .formSheet
                    rootViewController.present(hostingController, animated: true)
                }
            }
        }
        .onReceive(viewModel.$event) { event in
            switch event {
            case is TripDetailsEvent.Undefined:
                break
                
            case let clickEvent as TripDetailsEvent.ClickOnFile:
                let filePreviewData = clickEvent.filePreviewData
                path.removeAll { navigation in
                    if case .filePreview = navigation {
                        return true
                    }
                    return false
                }
                path.append(TripsNavigation.filePreview(filePreviewData: filePreviewData))
                viewModel.tripDetailsViewModel.onDispose()
                break
                
            case let deleteEvent as TripDetailsEvent.DeleteTrip:
                let filesToDeleteUris = deleteEvent.filesToDeleteUris
                filesToDeleteUris.forEach { deleteImageFile(uri: $0) }
                dismiss()
                viewModel.tripDetailsViewModel.onDispose()
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

private struct TripDetailsContent: View {
    let trip: TripUi
    let folders: [FolderUi]
    let isFoldersLoading: Bool
    let onTripDetailsClick: () -> Void
    let onSearchForFolder: (String) -> Void
    let onAddFolderClick: () -> Void
    let onFolderClick: (Int64) -> Void
    let onFileClick: (Int64, Int, String) -> Void

    var body: some View {
        ZStack(alignment: .top) {
            Color(uiColor: UIColor(red: 0.95, green: 0.95, blue: 0.95, alpha: 1.0))

            TripDetailsBanner(bannerUri: trip.bannerUri)
                .frame(height: 248)
            
            ScrollView {
                VStack(alignment: .leading) {
                    Spacer()
                        .frame(height: 180)
            
                    if let tripStepIntent = trip.tripStepUi?.toTripStepIntent() {
                        TripStepBadge(intent: tripStepIntent)
                            .padding(.horizontal, 16)
                    }
                    
                    TripDetailsMainContent(
                        trip: trip,
                        folders: folders,
                        isFoldersLoading: isFoldersLoading,
                        onTripDetailsClick: onTripDetailsClick,
                        onSearchForFolder: onSearchForFolder,
                        onAddFolderClick: onAddFolderClick,
                        onFolderClick: onFolderClick,
                        onFileClick: onFileClick
                    )
                }
                .frame(minHeight: UIScreen.main.bounds.height)
            }
        }
        .background(Color(uiColor: UIColor(red: 0.95, green: 0.95, blue: 0.95, alpha: 1.0)))
        .ignoresSafeArea(edges: .top)
    }
}

private struct TripDetailsBanner: View {
    let bannerUri: String
    
    var body: some View {
        if let url = getImageURL(uri: bannerUri),
           let image = UIImage(contentsOfFile: url.path) {
            Image(uiImage: image)
                .resizable()
                .aspectRatio(contentMode: .fill)
                .frame(height: 248)
                .clipped()
        } else {
            Color.gray
                .frame(height: 248)
        }
    }
}

private struct TripDetailsMainContent: View {
    let trip: TripUi
    let folders: [FolderUi]
    let isFoldersLoading: Bool
    let onTripDetailsClick: () -> Void
    let onSearchForFolder: (String) -> Void
    let onAddFolderClick: () -> Void
    let onFolderClick: (Int64) -> Void
    let onFileClick: (Int64, Int, String) -> Void
    
    var body: some View {
        VStack(spacing: 16) {
            TripDetailsHeader(trip: trip)
                .onTapGesture(perform: onTripDetailsClick)
                .padding(.horizontal, 16)
            
            TripSearchBar(
                onAddFolderClick: onAddFolderClick,
                onSearchForFolder: onSearchForFolder
            )
            .padding(.horizontal, 16)
            
            TripDetailsFoldersList(
                folders: folders,
                isLoading: isFoldersLoading,
                onFolderClick: onFolderClick,
                onFileClick: onFileClick
            )
            .padding(.horizontal, 16)
            
            Spacer(minLength: 0)
        }
        .padding(.top, 16)
        .frame(maxWidth: .infinity, minHeight: UIScreen.main.bounds.height - 448)
        .background(
            Color(uiColor: UIColor(red: 0.95, green: 0.95, blue: 0.95, alpha: 1.0))
                .clipShape(RoundedRectangle(cornerRadius: 32))
        )
    }
}

private struct TripDetailsFoldersList: View {
    let folders: [FolderUi]
    let isLoading: Bool
    let onFolderClick: (Int64) -> Void
    let onFileClick: (Int64, Int, String) -> Void
    
    var body: some View {
        if isLoading {
            TripDetailsLoading()
        } else if folders.isEmpty {
            Spacer()
        } else {
            LazyVStack(spacing: 16) {
                ForEach(folders, id: \.id) { folder in
                    FolderCard(
                        title: folder.title,
                        filesUri: folder.files.map { $0.fileUri },
                        folderTypeIntent: mapFolderTypeToIntent(folder.type),
                        onFileClick: { fileIndex, fileUri in
                            onFileClick(folder.id, fileIndex, fileUri)
                        }
                    )
                    .onTapGesture { onFolderClick(folder.id) }
                }
            }
        }
    }
}

private struct TripDetailsHeader: View {
    let trip: TripUi
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack(alignment: .center, spacing: 8) {
                Text(text: trip.title, intent: .h1, lineLimit: 3)
                    .truncationMode(.tail)
                
                if let country = trip.country,
                   let intent = country.toCountryIntent() {
                    CountryBadge(intent: intent)
                }
            }
            .frame(maxWidth: .infinity, alignment: .leading)
            
            if let departureDate = trip.departureDate, let returnDate = trip.returnDate {
                Text(text: "Du \(departureDate) au \(returnDate) • \(trip.daysOfTrip) jour(s)", intent: .smallDim)
                    .font(.subheadline)
                    .foregroundColor(.gray)
            }
        }
    }
}

private struct TripSearchBar: View {
    let onAddFolderClick: () -> Void
    let onSearchForFolder: (String) -> Void
    
    @State private var searchText = ""
    
    var body: some View {
        HStack(spacing: 8) {
            IconButton(icon: Icons.add, action: onAddFolderClick)
            
            TextField(
                value: $searchText,
                label: "Rechercher un dossier",
                leadingIcon: Icons.search
            )
            .textFieldStyle(RoundedBorderTextFieldStyle())
            .onChange(of: searchText) { newValue in
                onSearchForFolder(newValue)
            }
        }
    }
}
