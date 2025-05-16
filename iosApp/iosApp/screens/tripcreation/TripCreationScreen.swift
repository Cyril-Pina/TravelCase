import SwiftUI
import Shared

extension TripCreationScreen {
    
    @MainActor
    class ViewModelWrapper: ObservableObject {
        let tripCreationViewModel: TripCreationViewModel
        @Published var uiState: TripCreationUiState
        @Published var event: TripCreationEvent

        init() {
            self.tripCreationViewModel = ViewModelInjector().tripCreationViewModel
            self.uiState = tripCreationViewModel.uiState.value
            self.event = tripCreationViewModel.event.value
        }

        func startObserving() {
            Task {
                for await uiState in tripCreationViewModel.uiState {
                    self.uiState = uiState
                }
            }
            Task {
                for await event in tripCreationViewModel.event {
                    self.event = event
                }
            }
        }
    }
}

struct TripCreationScreen: View {
    @StateObject private var viewModel: ViewModelWrapper
    @Environment(\.dismiss) private var dismiss
    @State private var isFocused: Bool = false
    @Binding var path: [TripsNavigation]

    init(path: Binding<[TripsNavigation]>) {
        _viewModel = StateObject(wrappedValue: ViewModelWrapper())
        _path = path
    }
    
    var body: some View {
        NavigationView {
            VStack(spacing: 16) {
                DropDownTextField(
                    value: Binding(
                        get: { return viewModel.uiState.queriedCountry },
                        set: {
                            if isFocused {
                                viewModel.tripCreationViewModel.onFilterCountry(query: $0)
                            }
                        }
                    ),
                    items: viewModel.uiState.countries.map { "\($0.flag) \($0.value)" },
                    onItemClick: { index in
                        viewModel.tripCreationViewModel.onCountrySelected(country: viewModel.uiState.countries[index])
                    },
                    label: "Indiquer le pays de votre destination",
                    leadingIcon: Icons.globe,
                    onFocusChanged: { isFocused in
                        self.isFocused = isFocused
                    }
                )
                
                if viewModel.uiState.showTitleTextField {
                    VStack(spacing: 16) {
                        TextField(
                            value: Binding(
                                get: { viewModel.uiState.title },
                                set: { viewModel.tripCreationViewModel.onUpdateTitle(newTitle: $0) }
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
                                
                                viewModel.tripCreationViewModel.onUpdateDates(departureDateMillis: KotlinLong(longLong: departureDateMillis), returnDateMillis: KotlinLong(longLong: returnDateMillis))
                            }
                        )
                    }
                    .transition(.move(edge: .top).combined(with: .opacity))
                }
                
                Spacer()
            }
            .padding(.top, 32)
            .padding(.horizontal, 16)
            .background(Color(uiColor: UIColor(red: 0.95, green: 0.95, blue: 0.95, alpha: 1.0)))
            .animation(.easeInOut(duration: 0.2), value: viewModel.uiState.showTitleTextField)
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
                        action: { viewModel.tripCreationViewModel.addTrip() },
                        isEnabled: viewModel.uiState.nextButtonEnabled
                    )
                    .padding(.top, 24)
                }
            }
        }
        .navigationBarBackButtonHidden(true)
        .onReceive(viewModel.$event) { event in
            switch event {
            case is TripCreationEvent.Undefined:
                break
                
            case let tripCreated as TripCreationEvent.TripCreated:
                let tripId = tripCreated.tripId
                dismiss()
                path.append(.tripDetails(tripId: tripId))
                viewModel.tripCreationViewModel.onDispose()
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

extension Int64 {
    func toDate() -> Date {
        Date(timeIntervalSince1970: TimeInterval(self) / 1000)
    }
}

extension Date {
    var millisecondsSince1970: Int64 {
        Int64((timeIntervalSince1970 * 1000.0).rounded())
    }
}
