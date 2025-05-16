import SwiftUI

struct FolderTypeBadge: View {
    let intent: FolderTypeIntent
    let clickable: Bool
    let onClick: (() -> Void)?
    
    init(intent: FolderTypeIntent, clickable: Bool = false, onClick: (() -> Void)? = nil) {
        self.intent = intent
        self.clickable = clickable
        self.onClick = onClick
    }

    var body: some View {
        HStack(spacing: 4) {
            Image(intent.iconName)
                .font(.system(size: 12))
            Text(text: intent.title, intent: .small, color: intent.borderColor)
                .lineLimit(1)
        }
        .fixedSize(horizontal: true, vertical: false)
        .foregroundColor(intent.borderColor)
        .padding(.horizontal, 8)
        .padding(.vertical, 4)
        .background(intent.containerColor)
        .clipShape(Capsule())
        .overlay(
            Capsule()
                .stroke(intent.borderColor, lineWidth: 1)
        )
        .onTapGesture {
            if clickable {
                onClick?()
            }
        }
    }
}

enum FolderTypeIntent {
    case ticket
    case accommodation
    case vehicleRental
    case other
    
    var title: String {
        switch self {
        case .ticket:
            return "Billet"
        case .accommodation:
            return "Hébergement"
        case .vehicleRental:
            return "Location véhicule"
        case .other:
            return "Autre"
        }
    }
    
    var iconName: String {
        switch self {
        case .ticket:
            return Icons.plane
        case .accommodation:
            return Icons.home
        case .vehicleRental:
            return Icons.car
        case .other:
            return Icons.files
        }
    }
    
    var containerColor: Color {
        switch self {
        case .ticket:
            return Color(hex: "FAD2D2")
        case .accommodation:
            return Color(hex: "A9D6E5")
        case .vehicleRental:
            return Color(hex: "E4CAFF")
        case .other:
            return Color(hex: "A8DCD1")
        }
    }
    
    var borderColor: Color {
        switch self {
        case .ticket:
            return Color(hex: "E63946")
        case .accommodation:
            return Color(hex: "0077B6")
        case .vehicleRental:
            return Color(hex: "9B5DE5")
        case .other:
            return Color(hex: "1B8E80")
        }
    }
}
