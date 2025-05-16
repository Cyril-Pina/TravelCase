import SwiftUI

struct SmallBadge: View {
    let text: String
    
    init(text: String) {
        self.text = text
    }
    
    var body: some View {
        Text(text: text, intent: .small)
            .frame(width: 16, height: 16, alignment: .center)
            .background(Color.white)
            .clipShape(Circle())
    }
}
