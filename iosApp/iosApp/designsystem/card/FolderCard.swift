import SwiftUI

struct FolderCard: View {
    let title: String
    let filesUri: [String]
    let folderTypeIntent: FolderTypeIntent?
    let onFileClick: (Int, String) -> Void
    
    init(
        title: String,
        filesUri: [String],
        folderTypeIntent: FolderTypeIntent?,
        onFileClick: @escaping (Int, String) -> Void
    ) {
        self.title = title
        self.filesUri = filesUri
        self.folderTypeIntent = folderTypeIntent
        self.onFileClick = onFileClick
    }
    
    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            ScrollView(.horizontal, showsIndicators: false) {
                HStack(spacing: 4) {
                    ForEach(Array(filesUri.enumerated()), id: \.element) { index, fileUri in
                        if let url = getImageURL(uri: fileUri),
                           let image = UIImage(contentsOfFile: url.path) {
                            ZStack {
                                Image(uiImage: image)
                                    .resizable()
                                    .aspectRatio(contentMode: .fill)
                                    .frame(width: 86, height: 125)
                                    .clipped()
                                    .clipShape(RoundedRectangle(cornerRadius: 24))
                                
                                Color.clear
                                    .contentShape(Rectangle())
                                    .onTapGesture {
                                        onFileClick(index, fileUri)
                                    }
                            }
                            .frame(width: 86, height: 125)
                        } else {
                            Color.gray
                                .frame(width: 86, height: 125)
                                .clipShape(RoundedRectangle(cornerRadius: 24))
                        }
                    }
                }
                .padding(filesUri.isEmpty ? 8 : 16)
            }
            
            if let intent = folderTypeIntent {
                FolderTypeBadge(intent: intent)
                    .padding(.horizontal, 16)
                    .frame(maxWidth: .infinity, alignment: .leading)
            }
            
            Text(
                text: title,
                intent: .h3
            )
            .padding(.horizontal, 16)
            .frame(maxWidth: .infinity, alignment: .leading)
            
            Spacer()
                .frame(height: 16)
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(Color.white)
        .clipShape(RoundedRectangle(cornerRadius: 32))
    }
}
