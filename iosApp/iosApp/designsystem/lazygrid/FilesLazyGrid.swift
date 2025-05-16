import SwiftUI

struct FilesLazyGrid: View {
    let onDeleteFileClick: (Int) -> Void
    let onAddFileClick: () -> Void
    let onFileClick: (Int) -> Void
    let filesUris: [String]
    
    @State private var items: [GridItemType] = []
    
    init(
        onDeleteFileClick: @escaping (Int) -> Void,
        onAddFileClick: @escaping () -> Void,
        onFileClick: @escaping (Int) -> Void,
        filesUris: [String]
    ) {
        self.onDeleteFileClick = onDeleteFileClick
        self.onAddFileClick = onAddFileClick
        self.onFileClick = onFileClick
        self.filesUris = filesUris
        self._items = State(initialValue: buildGridItems(filesUris))
    }
    
    var body: some View {
        VStack {
            LazyVGrid(
                columns: [
                    SwiftUI.GridItem(.flexible(), spacing: 8),
                    SwiftUI.GridItem(.flexible(), spacing: 8),
                    SwiftUI.GridItem(.flexible(), spacing: 8),
                    SwiftUI.GridItem(.flexible(), spacing: 8)
                ],
                spacing: 8
            ) {
                ForEach(Array(items.enumerated()), id: \.element) { index, item in
                    let currentIndex: Int = {
                        items[..<index].filter {
                            if case .file = $0 { return true }
                            return false
                        }.count
                    }()
                    
                    switch item {
                        case .addButton:
                            AddFileButton(
                                height: 125,
                                onClick: onAddFileClick
                            )
                            .transition(.opacity)
                            
                        case .spacerItem:
                            Spacer()
                                .frame(width: 0, height: 0)
                            
                        case .file(let uri):
                            FileCard(
                                fileUri: uri,
                                index: currentIndex + 1,
                                onDeleteClick: { onDeleteFileClick(currentIndex) }
                            )
                            .onTapGesture {
                                onFileClick(currentIndex)
                            }
                            .transition(.opacity)
                    }
                }
            }
            .padding(16)
            .animation(.easeInOut(duration: 0.2), value: items)
        }
        .background(Color.white)
        .clipShape(RoundedRectangle(cornerRadius: 36))
        .onAppear {
            items = buildGridItems(filesUris)
        }
        .onChange(of: filesUris) { newFilesUris in
            items = buildGridItems(newFilesUris)
        }
    }
}

private struct AddFileButton: View {
    let height: CGFloat
    let onClick: () -> Void
    
    var body: some View {
        IconButton(
            icon: Icons.add,
            action: onClick
        ).frame(height: height)
    }
}

struct FileCard: View {
    let fileUri: String
    let index: Int
    let onDeleteClick: () -> Void
    
    var body: some View {
        ZStack(alignment: .topTrailing) {
            if let url = getImageURL(uri: fileUri),
               let image = UIImage(contentsOfFile: url.path) {
                GeometryReader { geo in
                    Image(uiImage: image)
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(width: geo.size.width, height: 125)
                        .clipped()
                        .clipShape(RoundedRectangle(cornerRadius: 24))
                }
                .frame(height: 125)
            } else {
                Color.gray
                    .frame(height: 125)
                    .clipShape(RoundedRectangle(cornerRadius: 24))
            }
            
            SmallIconButton(
                icon: Icons.close,
                action: onDeleteClick
            )
            .padding(8)
            .shadow(radius: 3)
            
            SmallBadge(
                text: "\(index)"
            )
            .padding(8)
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .bottomLeading)
        }
    }
}

struct FolderAddButton: View {
    let onClick: () -> Void
    
    var body: some View {
        ZStack(alignment: .leading) {
            RoundedRectangle(cornerRadius: 36)
                .fill(Color.white)
                .frame(maxWidth: .infinity)
            
            Button(
                text: "Ajouter un fichier",
                action: onClick,
                leadingIcon: "plus"
            )
            .padding(16)
        }
        .fixedSize(horizontal: false, vertical: true)
    }
}

private func buildGridItems(_ filesUris: [String]) -> [GridItemType] {
    var items: [GridItemType] = []
    
    for (index, uri) in filesUris.enumerated() {
        if index == 0 {
            items.append(.addButton)
            items.append(.file(uri))
        } else if index % 3 == 0 {
            items.append(.spacerItem)
            items.append(.file(uri))
        } else {
            items.append(.file(uri))
        }
    }
    
    return items
}

enum GridItemType: Hashable {
    case addButton
    case spacerItem
    case file(String)
}
