import SwiftUI

struct DropDownTextField: View {
    @Binding var value: String
    let items: [String]
    let onItemClick: (Int) -> Void
    let label: String?
    let leadingIcon: String?
    let errorMessage: String?
    let isEnabled: Bool
    let isReadOnly: Bool
    let isSingleLine: Bool
    let onFocusChanged: (Bool) -> Void
    
    @State private var isExpanded = false
    @FocusState private var isFocused: Bool

    init(
        value: Binding<String>,
        items: [String],
        onItemClick: @escaping (Int) -> Void,
        label: String? = nil,
        leadingIcon: String? = nil,
        errorMessage: String? = nil,
        isEnabled: Bool = true,
        isReadOnly: Bool = false,
        isSingleLine: Bool = false,
        onFocusChanged: @escaping (Bool) -> Void
    ) {
        self._value = value
        self.items = items
        self.onItemClick = onItemClick
        self.label = label
        self.leadingIcon = leadingIcon
        self.errorMessage = errorMessage
        self.isEnabled = isEnabled
        self.isReadOnly = isReadOnly
        self.isSingleLine = isSingleLine
        self.onFocusChanged = onFocusChanged
    }
    
    var body: some View {
        VStack(spacing: 4) {
            TextField(
                value: $value,
                label: label,
                leadingIcon: leadingIcon,
                errorMessage: errorMessage,
                isEnabled: isEnabled,
                isReadOnly: isReadOnly,
                isSingleLine: isSingleLine
            )
            .focused($isFocused)

            if isExpanded && !items.isEmpty {
                GeometryReader { geometry in
                    ScrollView {
                        VStack(spacing: 0) {
                            ForEach(Array(items.enumerated()), id: \.offset) { index, item in
                                Text(text: item, intent: .body)
                                    .frame(maxWidth: .infinity, alignment: .leading)
                                    .padding(.vertical, 8)
                                    .padding(.horizontal, 32)
                                    .background(
                                        Color.white
                                            .opacity(0.01)
                                            .onTapGesture {
                                                self.onFocusChanged(false)
                                                isFocused = false
                                                onItemClick(index)
                                            }
                                    )
                            }
                        }
                    }
                    .frame(height: min(CGFloat(items.count * 40), 160))
                    .background(Color.white)
                    .clipShape(RoundedRectangle(cornerRadius: 32))
                }
                .frame(height: min(CGFloat(items.count * 40), 160))
                .transition(.opacity)
            }
        }
        .animation(.easeInOut(duration: 0.2), value: isExpanded)
        .onChange(of: isFocused) { isFocused in
            isExpanded = isFocused
            self.onFocusChanged(isFocused)
        }
    }
}
