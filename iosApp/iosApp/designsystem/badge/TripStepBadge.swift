import SwiftUI
import Shared

struct TripStepBadge: View {
    let intent: TripStepIntent
    
    init(intent: TripStepIntent) {
        self.intent = intent
    }
    
    var body: some View {
        HStack(spacing: 4) {
            Image(intent.iconName)
                .font(.system(size: 14))
                .foregroundColor(.white)
            Text(text: intent.title, intent: .small, color: .white)
        }
        .foregroundColor(.white)
        .padding(.horizontal, 8)
        .padding(.vertical, 4)
        .background(intent.backgroundColor)
        .clipShape(Capsule())
    }
}

enum TripStepIntent {
    case incoming(days: Int)
    case pending
    case finished
    
    var title: String {
        switch self {
        case .incoming(let days):
            return "Départ dans \(days) jour(s)"
        case .pending:
            return "Voyage en cours"
        case .finished:
            return "Voyage terminé"
        }
    }
    
    var iconName: String {
        switch self {
        case .incoming:
            return Icons.warning
        case .pending:
            return Icons.plane
        case .finished:
            return Icons.check
        }
    }
    
    var backgroundColor: Color {
        switch self {
        case .incoming:
            return Color(hex: "CA5F07")
        case .pending:
            return Color(hex: "0077B6")
        case .finished:
            return Color(hex: "000000")
        }
    }
}

extension TripStepUi {
    func toTripStepIntent() -> TripStepIntent {
        switch self {
        case let incoming as TripStepUi.Incoming:
            return .incoming(days: Int(incoming.daysLeft))
        case is TripStepUi.Pending:
            return .pending
        case is TripStepUi.Finished:
            return .finished
        default:
            return .incoming(days: 0)
        }
    }
}
