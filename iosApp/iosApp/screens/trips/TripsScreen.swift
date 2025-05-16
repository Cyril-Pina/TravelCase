import SwiftUI
import Shared

extension TripsScreen {
    
    @MainActor
    class ViewModelWrapper: ObservableObject {
        let tripsViewModel: TripsViewModel
        @Published var uiState: TripsUiState

        init() {
            self.tripsViewModel = ViewModelInjector().tripsViewModel
            self.uiState = tripsViewModel.uiState.value
        }

        func startObserving() {
            Task {
                for await uiState in tripsViewModel.uiState {
                    self.uiState = uiState
                }
            }
        }

        func onSearchForTrip(query: String) {
            tripsViewModel.onSearchForTrip(query: query)
        }
    }
}

struct TripsScreen: View {
    @StateObject private var viewModel: ViewModelWrapper
    @State private var showTripCreation = false
    @State private var path: [TripsNavigation] = []
    
    init() {
        _viewModel = StateObject(wrappedValue: ViewModelWrapper())
    }

    var body: some View {
        NavigationStack(path: $path) {
            Group {
                if viewModel.uiState.loading {
                    TripsLoadingView()
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                        .background(Color(uiColor: UIColor(red: 0.95, green: 0.95, blue: 0.95, alpha: 1.0)))
                } else {
                    TripsContentView(
                        trips: viewModel.uiState.trips,
                        onAddTripClick: { showTripCreation = true },
                        onTripClick: { tripId in
                            path.append(.tripDetails(tripId: tripId))
                        },
                        onSearchForTrip: { query in
                            viewModel.tripsViewModel.onSearchForTrip(query: query)
                        }
                    )
                    .background(Color(uiColor: UIColor(red: 0.95, green: 0.95, blue: 0.95, alpha: 1.0)))
                }
            }
            .sheet(isPresented: $showTripCreation) {
                TripCreationScreen(path: $path)
                    .presentationCornerRadius(32)
            }
            .navigationDestination(for: TripsNavigation.self) { destination in
                switch destination {
                case .tripDetails(let tripId):
                    TripDetailsScreen(tripId: tripId, path: $path)
                case .folderDetails(let folderId):
                    FolderDetailsScreen(folderId: folderId, path: $path)
                case .filePreview(let filePreviewData):
                    FilePreviewScreen(filePreviewData: filePreviewData)
                }
            }
        }
        .onAppear {
            viewModel.startObserving()
        }
    }
}

struct TripsLoadingView: View {
    var body: some View {
        VStack {
            Spacer()
            ProgressView()
                .progressViewStyle(CircularProgressViewStyle())
                .scaleEffect(1.5)
            Text(text: "Chargement des voyages...", intent: .body)
                .foregroundColor(.gray)
                .padding(.top, 16)
            Spacer()
        }
        .padding(16)
    }
}

struct TripsContentView: View {
    let trips: [TripUi]
    var onAddTripClick: () -> Void
    var onTripClick: (Int64) -> Void
    var onSearchForTrip: (String) -> Void
    
    @State private var searchText: String = ""
    
    var body: some View {
        VStack(alignment: .leading, spacing: 16) {
            Text(text: "Prêt pour le prochain voyage ?", intent: .h3)
                .padding(.horizontal, 16)
            
            HStack {
                IconButton(icon: Icons.add, action: onAddTripClick)
                TextField(
                    value: $searchText,
                    label: "Rechercher un voyage",
                    leadingIcon: Icons.search
                )
                    .textFieldStyle(RoundedBorderTextFieldStyle())
                    .onChange(of: searchText) { newValue in
                        onSearchForTrip(newValue)
                    }
            }
                .padding(.horizontal, 16)
            
            ScrollView {
                LazyVStack(spacing: 12) {
                    ForEach(trips, id: \.id) { trip in
                        let subtitle: String = {
                            guard let start = trip.departureDate, let end = trip.returnDate else {
                                return ""
                            }
                            return "Du \(start) au \(end) • \(trip.daysOfTrip) jour(s)"
                        }()
                        TripCard(
                            title: trip.title,
                            subtitle: subtitle,
                            countryIntent: trip.country?.toCountryIntent(),
                            fileCount: Int(trip.fileCount),
                            bannerUri: trip.bannerUri,
                            onClick: {
                                onTripClick(trip.id)
                            },
                            tripStepIntent: trip.tripStepUi?.toTripStepIntent()
                        )
                        .transition(.opacity.combined(with: .move(edge: .bottom)))
                    }
                }
                .animation(.easeInOut(duration: 0.2), value: trips)
                .padding(.horizontal, 16)
            }
        }
    }
}
