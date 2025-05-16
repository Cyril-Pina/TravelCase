import Shared

func mapFolderTypeToIntent(_ folderType: FolderType?) -> FolderTypeIntent? {
    guard let folderType = folderType else { return nil }
    switch folderType {
    case .ticket:
        return .ticket
    case .accommodation:
        return .accommodation
    case .vehicleRental:
        return .vehicleRental
    case .other:
        return .other
    }
}

func mapIntentToFolderType(_ intent: FolderTypeIntent) -> FolderType {
    switch intent {
    case .ticket:
        return .ticket
    case .accommodation:
        return .accommodation
    case .vehicleRental:
        return .vehicleRental
    case .other:
        return .other
    }
}
