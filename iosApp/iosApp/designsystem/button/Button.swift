import SwiftUI

struct Button: View {
    let text: String
    let action: () -> Void
    let intent: ButtonIntent
    let isEnabled: Bool
    let leadingIcon: String?
    
    init(
        text: String,
        action: @escaping () -> Void,
        intent: ButtonIntent = .default,
        isEnabled: Bool = true,
        leadingIcon: String? = nil
    ) {
        self.text = text
        self.action = action
        self.intent = intent
        self.isEnabled = isEnabled
        self.leadingIcon = leadingIcon
    }
    
    var body: some View {
        SwiftUI.Button(action: action) {
            HStack(spacing: 4) {
                if let icon = leadingIcon {
                    Image(systemName: icon)
                        .font(.system(size: 14))
                        .foregroundColor(intent.contentColor)
                }
                Text(text: text, intent: .body, color: intent.contentColor)
            }
            .padding(.horizontal, 16)
            .padding(.vertical, 8)
            .frame(minHeight: 48)
            .background(intent.containerColor)
            .clipShape(Capsule())
        }
        .opacity(isEnabled ? 1 : 0.5)
        .disabled(!isEnabled)
    }
}

enum ButtonIntent {
    case `default`
    case ghost
    case ghostDanger
    
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
        case .ghost, .ghostDanger:
            return LinearGradient(colors: [.clear], startPoint: .leading, endPoint: .trailing)
        }
    }
    
    var contentColor: Color {
        switch self {
        case .default:
            return .white
        case .ghost:
            return .black
        case .ghostDanger:
            return Color(hex: "F90C0C")
        }
    }
}

extension Color {
    init(hex: String) {
        let hex = hex.trimmingCharacters(in: CharacterSet.alphanumerics.inverted)
        var int: UInt64 = 0
        Scanner(string: hex).scanHexInt64(&int)
        let a, r, g, b: UInt64
        switch hex.count {
        case 3: // RGB (12-bit)
            (a, r, g, b) = (255, (int >> 8) * 17, (int >> 4 & 0xF) * 17, (int & 0xF) * 17)
        case 6: // RGB (24-bit)
            (a, r, g, b) = (255, int >> 16, int >> 8 & 0xFF, int & 0xFF)
        case 8: // ARGB (32-bit)
            (a, r, g, b) = (int >> 24, int >> 16 & 0xFF, int >> 8 & 0xFF, int & 0xFF)
        default:
            (a, r, g, b) = (1, 1, 1, 0)
        }
        self.init(
            .sRGB,
            red: Double(r) / 255,
            green: Double(g) / 255,
            blue:  Double(b) / 255,
            opacity: Double(a) / 255
        )
    }
} 
