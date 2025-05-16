import SwiftUI

struct IconButton: View {
    let icon: String
    let action: () -> Void
    let intent: IconButtonIntent
    let isEnabled: Bool
    
    init(
        icon: String,
        action: @escaping () -> Void,
        intent: IconButtonIntent = .default,
        isEnabled: Bool = true
    ) {
        self.icon = icon
        self.action = action
        self.intent = intent
        self.isEnabled = isEnabled
    }
    
    var body: some View {
        SwiftUI.Button(action: action) {
            Image(icon)
                .font(.system(size: 10))
                .foregroundColor(intent.contentColor)
                .frame(width: 48, height: 48)
                .background(intent.containerColor)
                .clipShape(Circle())
        }
        .opacity(isEnabled ? 1 : 0.5)
        .disabled(!isEnabled)
    }
}

enum IconButtonIntent {
    case `default`
    case secondary
    case secondaryDanger
    
    var containerColor: LinearGradient {
        switch self {
        case .default:
            return LinearGradient(
                colors: [
                    Color(hex: "212528"),
                    Color(hex: "444444")
                ],
                startPoint: .leading,
                endPoint: .trailing
            )
        case .secondary, .secondaryDanger:
            return LinearGradient(colors: [.white], startPoint: .leading, endPoint: .trailing)
        }
    }
    
    var contentColor: Color {
        switch self {
        case .default:
            return .white
        case .secondary:
            return .black
        case .secondaryDanger:
            return Color(hex: "F90C0C")
        }
    }
}
