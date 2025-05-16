import SwiftUI

struct AlertDialog: View {
    let title: String
    let subtitle: String
    let actionButtonText: String
    let onConfirm: () -> Void
    let onDismiss: () -> Void
    let actionButtonIcon: String?
    
    init(
        title: String,
        subtitle: String,
        actionButtonText: String,
        onConfirm: @escaping () -> Void,
        onDismiss: @escaping () -> Void,
        actionButtonIcon: String? = nil
    ) {
        self.title = title
        self.subtitle = subtitle
        self.actionButtonText = actionButtonText
        self.onConfirm = onConfirm
        self.onDismiss = onDismiss
        self.actionButtonIcon = actionButtonIcon
    }
    
    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Spacer()
                IconButton(
                    icon: Icons.close,
                    action: onDismiss,
                    intent: .secondary
                )
            }
            
            Spacer()
                .frame(height: 16)
            
            Text(
                text: title,
                intent: .smallBold,
                textAlignment: .center
            )
            
            Spacer()
                .frame(height: 8)
            
            Text(
                text: subtitle,
                intent: .body,
                textAlignment: .center
            )
            
            Spacer()
                .frame(height: 16)
            
            Button(
                text: actionButtonText,
                action: onConfirm,
                intent: .ghostDanger,
                leadingIcon: actionButtonIcon
            )
        }
        .padding(16)
        .background(Color(hex: "F2F2F2"))
        .clipShape(RoundedRectangle(cornerRadius: 32, style: .continuous))
    }
}
