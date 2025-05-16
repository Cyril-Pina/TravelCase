import SwiftUI

struct Text: View {
    let text: String
    let intent: TextIntent
    let textAlignment: TextAlignment?
    let lineLimit: Int?
    let color: Color?
    
    init(
        text: String,
        intent: TextIntent,
        textAlignment: TextAlignment? = nil,
        lineLimit: Int? = nil,
        color: Color? = nil
    ) {
        self.text = text
        self.intent = intent
        self.textAlignment = textAlignment
        self.lineLimit = lineLimit
        self.color = color
    }
    
    var body: some View {
        SwiftUI.Text(text)
            .font(.custom(intent.fontStyle, size: intent.fontSize))
            .foregroundColor(color ?? intent.textColor)
            .multilineTextAlignment(textAlignment ?? .leading)
            .lineLimit(lineLimit)
    }
}

enum TextIntent {
    case h1
    case h2
    case h3
    case body
    case bodyBold
    case small
    case smallBold
    case smallDim
    
    var fontSize: CGFloat {
        switch self {
        case .h1:
            return 32
        case .h2:
            return 24
        case .h3:
            return 20
        case .body, .bodyBold:
            return 14
        case .small, .smallBold, .smallDim:
            return 12
        }
    }
    
    var fontStyle: String {
        switch self {
        case .h1:
            return "Poppins-Bold"
        case .h2, .h3:
            return "Poppins-SemiBold"
        case .body:
            return "Poppins-Regular"
        case .bodyBold:
            return "Poppins-Bold"
        case .small:
            return "Poppins-SemiBold"
        case .smallBold:
            return "Poppins-Black"
        case .smallDim:
            return "Poppins-SemiBold"
        }
    }
    
    var textColor: Color {
        switch self {
        case .smallDim:
            return Color(hex: "9E9E9E")
        default:
            return .primary
        }
    }
}
