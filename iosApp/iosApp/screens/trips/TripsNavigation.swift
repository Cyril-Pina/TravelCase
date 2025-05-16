import Shared

enum TripsNavigation: Hashable {
    case tripDetails(tripId: Int64)
    case folderDetails(folderId: Int64)
    case filePreview(filePreviewData: FilePreviewData)
}
