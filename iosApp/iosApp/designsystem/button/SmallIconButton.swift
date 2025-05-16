import SwiftUI

struct SmallIconButton: View {
    let icon: String
    let action: () -> Void
    let isEnabled: Bool
    
    init(
        icon: String,
        action: @escaping () -> Void,
        isEnabled: Bool = true
    ) {
        self.icon = icon
        self.action = action
        self.isEnabled = isEnabled
    }
    
    var body: some View {
        SwiftUI.Button(action: action) {
            Image(icon)
                .font(.system(size: 7))
                .foregroundColor(Color.black)
                .frame(width: 14, height: 14)
                .background(Color.white)
                .clipShape(Circle())
        }
        .disabled(!isEnabled)
    }
}
