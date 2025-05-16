import SwiftUI

struct TextField: View {
    @Binding var value: String
    let label: String?
    let leadingIcon: String?
    let errorMessage: String?
    let isEnabled: Bool
    let isReadOnly: Bool
    let isSingleLine: Bool
    
    @FocusState private var isFocused: Bool
    
    init(
        value: Binding<String>,
        label: String? = nil,
        leadingIcon: String? = nil,
        errorMessage: String? = nil,
        isEnabled: Bool = true,
        isReadOnly: Bool = false,
        isSingleLine: Bool = false
    ) {
        self._value = value
        self.label = label
        self.leadingIcon = leadingIcon
        self.errorMessage = errorMessage
        self.isEnabled = isEnabled
        self.isReadOnly = isReadOnly
        self.isSingleLine = isSingleLine
    }
    
    private var promptText: SwiftUICore.Text? {
        guard let label = label, !isFocused, value.isEmpty else {
            return nil
        }
        return SwiftUICore.Text(label)
            .foregroundColor(Color(hex: "9E9E9E"))
    }

    var body: some View {
        VStack(spacing: 4) {
            HStack(spacing: 8) {
                if let leadingIcon = leadingIcon {
                    Image(leadingIcon)
                        .font(.system(size: 14))
                        .foregroundColor(errorMessage != nil ? .red : Color(hex: "9E9E9E"))
                }
                
                SwiftUI.TextField(
                    label ?? "",
                    text: $value,
                    prompt: promptText
                )
                .focused($isFocused)
                .disabled(!isEnabled)
                .foregroundColor(errorMessage != nil ? .red : .black)
                .font(.system(size: 14))
                .textFieldStyle(PlainTextFieldStyle())
                
                if isFocused && !value.isEmpty {
                    Image(systemName: "xmark.circle.fill")
                        .font(.system(size: 14))
                        .foregroundColor(.black)
                        .onTapGesture {
                            value = ""
                        }
                }
            }
            .padding(16)
            .background(Color.white)
            .clipShape(Capsule())
            .overlay(
                errorMessage != nil ?
                    Capsule()
                        .stroke(Color.red, lineWidth: 1)
                    : nil
            )
            
            if let errorMessage = errorMessage {
                Text(text: errorMessage, intent: .body)
                    .foregroundColor(.red)
                    .padding(.horizontal, 16)
            }
        }
    }
}
