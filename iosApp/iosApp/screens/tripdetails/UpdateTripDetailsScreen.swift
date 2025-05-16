import SwiftUI
import Shared

extension UpdateTripDetailsScreen {
    
    @MainActor
    class ViewModelWrapper: ObservableObject {
        let tripId: Int64
        let tripDetailsViewModel: TripDetailsViewModel
        @Published var uiState: UpdateTripDetailsUiState

        init(tripId: Int64, tripDetailsViewModel: TripDetailsViewModel) {
            self.tripId = tripId
            self.tripDetailsViewModel = tripDetailsViewModel
            self.uiState = tripDetailsViewModel.uiState.value.updateTripDetailsUiState
        }

        func startObserving() {
            Task {
                for await uiState in tripDetailsViewModel.uiState {
                    self.uiState = uiState.updateTripDetailsUiState
                }
            }
        }
    }
}

struct UpdateTripDetailsScreen: View {
    @StateObject private var viewModel: ViewModelWrapper
    @Environment(\.dismiss) private var dismiss
    @State private var isFocused: Bool = false
    
    init(tripId: Int64, onDismiss: @escaping () -> Void) {
        let tripDetailsViewModel = ViewModelInjector().tripDetailsViewModel(tripId: tripId)
        _viewModel = StateObject(wrappedValue: ViewModelWrapper(tripId: tripId, tripDetailsViewModel: tripDetailsViewModel))
    }
    
    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                DropDownTextField(
                    value: Binding(
                        get: { return viewModel.uiState.queriedCountry },
                        set: {
                            if isFocused {
                                viewModel.tripDetailsViewModel.onFilterCountry(query: $0)
                            }
                        }
                    ),
                    items: viewModel.uiState.countries.map { "\($0.flag) \($0.value)" },
                    onItemClick: { index in
                        viewModel.tripDetailsViewModel.onCountrySelected(country: viewModel.uiState.countries[index])
                    },
                    label: "Indiquer le pays de votre destination",
                    leadingIcon: Icons.globe,
                    onFocusChanged: { isFocused in
                        self.isFocused = isFocused
                    }
                )
                
                TextField(
                    value: Binding(
                        get: { viewModel.uiState.title },
                        set: { viewModel.tripDetailsViewModel.onUpdateTitle(newTitle: $0) }
                    ),
                    label: "Ajouter un titre"
                )
                
                DateTextField(
                    departureDate: viewModel.uiState.departureDate,
                    returnDate: viewModel.uiState.returnDate,
                    departureDateMillis: viewModel.uiState.departureDateMillis.map { TimeInterval(truncating: $0) / 1000 },
                    returnDateMillis: viewModel.uiState.returnDateMillis.map { TimeInterval(truncating: $0) / 1000 },
                    onDatesUpdated: { departureTimeInterval, returnTimeInterval in
                    
                        let departureDateMillis = Int64((departureTimeInterval ?? 0) * 1000)
                        let returnDateMillis = Int64((returnTimeInterval ?? 0) * 1000)
                        
                        viewModel.tripDetailsViewModel.onUpdateDates(departureDateMillis: KotlinLong(longLong: departureDateMillis), returnDateMillis: KotlinLong(longLong: returnDateMillis))
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
                            viewModel.tripDetailsViewModel.onUpdateTrip()
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
