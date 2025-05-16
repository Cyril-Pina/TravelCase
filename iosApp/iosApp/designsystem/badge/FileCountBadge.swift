import SwiftUI

struct FileCountBadge: View {
    let count: Int
    
    var body: some View {
        HStack(spacing: 4) {
            Image(Icons.folder)
                .font(.system(size: 12))
            Text(text: "\(count)", intent: .small)
        }
        .foregroundColor(Color(hex: "666666"))
        .padding(.horizontal, 8)
        .padding(.vertical, 4)
        .background(Color(hex: "F5F5F5"))
        .clipShape(Capsule())
    }
}
