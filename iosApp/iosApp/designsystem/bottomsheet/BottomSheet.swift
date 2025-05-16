import SwiftUI

struct BottomSheet<Content: View>: View {
    let onConfirm: () -> Void
    let onDismiss: () -> Void
    let isConfirmButtonEnabled: Bool
    let content: () -> Content
    
    init(
        onConfirm: @escaping () -> Void,
        onDismiss: @escaping () -> Void,
        isConfirmButtonEnabled: Bool = true,
        @ViewBuilder content: @escaping () -> Content
    ) {
        self.onConfirm = onConfirm
        self.onDismiss = onDismiss
        self.isConfirmButtonEnabled = isConfirmButtonEnabled
        self.content = content
    }
    
    var body: some View {
        VStack(spacing: 0) {
            HStack {
                IconButton(
                    icon: Icons.close,
                    action: onDismiss,
                    intent: .secondary
                )
                
                Spacer()
                
                IconButton(
                    icon: Icons.check,
                    action: onConfirm,
                    isEnabled: isConfirmButtonEnabled
                )
            }
            .padding(16)
            
            content()
        }
        .frame(maxWidth: .infinity)
        .background(Color(hex: "F2F2F2"))
        .clipShape(RoundedRectangle(cornerRadius: 32, style: .continuous))
        .ignoresSafeArea(edges: .bottom)
    }
}
