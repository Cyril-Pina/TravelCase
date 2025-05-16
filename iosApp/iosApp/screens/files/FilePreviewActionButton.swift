import SwiftUI

enum FilePreviewActionButton {
    case share
    case delete
    
    var title: String {
        switch self {
        case .share:
            return "Partager"
        case .delete:
            return "Supprimer"
        }
    }
    
    var iconName: String {
        switch self {
        case .share:
            return Icons.share
        case .delete:
            return Icons.trash
        }
    }
}
