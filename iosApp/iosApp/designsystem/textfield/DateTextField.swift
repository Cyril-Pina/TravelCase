import SwiftUI

struct DateTextField: View {
    let departureDate: String?
    let returnDate: String?
    let departureDateMillis: TimeInterval?
    let returnDateMillis: TimeInterval?
    let onDatesUpdated: (TimeInterval?, TimeInterval?) -> Void
    
    @State private var showDatePicker = false
    
    var body: some View {
        HStack(spacing: 8) {
            Text(
                text: departureDate ?? "Date de départ",
                intent: .body,
                textAlignment: .center,
                color: departureDate != nil ? Color(hex: "000000") : Color(hex: "9E9E9E")
            )
            .frame(maxWidth: .infinity)
            
            Image(systemName: "arrow.right")
                .font(.system(size: 14))
            
            Text(
                text: returnDate ?? "Date de retour",
                intent: .body,
                textAlignment: .center,
                color: returnDate != nil ? Color(hex: "000000") : Color(hex: "9E9E9E")
            )
            .frame(maxWidth: .infinity)
        }
        .padding(16)
        .background(Color.white)
        .clipShape(RoundedRectangle(cornerRadius: 32))
        .onTapGesture {
            showDatePicker = true
        }
        .sheet(isPresented: $showDatePicker) {
            DatePickerView(
                initialStartDate: departureDateMillis.map { Date(timeIntervalSince1970: $0) },
                initialEndDate: returnDateMillis.map { Date(timeIntervalSince1970: $0) },
                onDismiss: { startDate, endDate in
                    if startDate != nil && endDate != nil {
                        onDatesUpdated(
                            startDate?.timeIntervalSince1970,
                            endDate?.timeIntervalSince1970
                        )
                    }
                    showDatePicker = false
                }
            )
            .presentationDetents([.medium])
            .presentationCornerRadius(32)
        }
    }
}

struct DatePickerView: View {
    let initialStartDate: Date?
    let initialEndDate: Date?
    let onDismiss: (Date?, Date?) -> Void
    
    @State private var startDate: Date?
    @State private var endDate: Date?
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading, spacing: 8) {
                DatePicker(
                    "Date de départ",
                    selection: Binding(
                        get: { startDate ?? Date() },
                        set: { startDate = $0 }
                    ),
                    in: (startDate ?? Date())...,
                    displayedComponents: .date
                )
                .datePickerStyle(.compact)

                DatePicker(
                    "Date de retour",
                    selection: Binding(
                        get: { endDate ?? Date() },
                        set: { endDate = $0 }
                    ),
                    in: (startDate != nil ? startDate! + 1 : Date())...,
                    displayedComponents: .date
                )
                .datePickerStyle(.compact)
                
                Spacer()
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
            .padding(.horizontal, 16)
            .padding(.vertical, 32)
            .background(Color(uiColor: UIColor(red: 0.95, green: 0.95, blue: 0.95, alpha: 1.0)))
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    IconButton(
                        icon: Icons.close,
                        action: {
                            onDismiss(nil, nil)
                            dismiss()
                        },
                        intent: .secondary
                    )
                    .padding(.top, 24)
                }
                
                ToolbarItem(placement: .confirmationAction) {
                    IconButton(
                        icon: Icons.check,
                        action: {
                            let startDateFinale = startDate ?? Date()
                            let endDateFinale = {
                                if let start = startDate, let end = endDate {
                                    return start > end ? start : end
                                } else {
                                    return endDate
                                }
                            }() ?? Date()
                            
                            onDismiss(startDateFinale, endDateFinale)
                            dismiss()
                        }
                    )
                    .padding(.top, 24)
                }
            }
        }
        .navigationViewStyle(.stack)
        .onAppear {
            startDate = initialStartDate
            endDate = initialEndDate
        }
    }
}
