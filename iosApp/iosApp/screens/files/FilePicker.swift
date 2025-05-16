import SwiftUI
import PhotosUI

struct FilePicker: UIViewControllerRepresentable {
    let onPick: ([URL]) -> Void
    let onDismiss: (() -> Void)?
    let autoDismiss: Bool
    
    init(onPick: @escaping ([URL]) -> Void, onDismiss: (() -> Void)? = nil, autoDismiss: Bool = true) {
        self.onPick = onPick
        self.onDismiss = onDismiss
        self.autoDismiss = autoDismiss
    }
    
    func makeUIViewController(context: Context) -> UIViewController {
        let photoPicker = PHPickerViewController(configuration: {
            var config = PHPickerConfiguration()
            config.filter = .images
            config.selectionLimit = 1
            return config
        }())
        photoPicker.delegate = context.coordinator
        
        return photoPicker
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    
    class Coordinator: NSObject, PHPickerViewControllerDelegate {
        let parent: FilePicker
        private var selectedImage: UIImage?
        
        init(_ parent: FilePicker) {
            self.parent = parent
        }
        
        private func getDocumentsDirectory() -> URL {
            let supportDirectory = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
            let imagesDirectory = supportDirectory.appendingPathComponent("Images", isDirectory: true)
            
            if !FileManager.default.fileExists(atPath: imagesDirectory.path) {
                do {
                    try FileManager.default.createDirectory(at: imagesDirectory, withIntermediateDirectories: true)
                } catch {
                    print(error)
                }
            }
            
            return imagesDirectory
        }
        
        func picker(_ picker: PHPickerViewController, didFinishPicking results: [PHPickerResult]) {
            if parent.autoDismiss {
                picker.dismiss(animated: true)
            }
            
            if results.isEmpty {
                parent.onDismiss?()
                return
            }
            
            guard let result = results.first else { return }
            
            if result.itemProvider.canLoadObject(ofClass: UIImage.self) {
                result.itemProvider.loadObject(ofClass: UIImage.self) { [weak self] image, error in
                    guard let self = self,
                          let image = image as? UIImage else {
                        return
                    }
                                        
                    self.selectedImage = image
                    
                    guard let data = image.jpegData(compressionQuality: 1) else {
                        return
                    }
                    
                    let documentsDirectory = self.getDocumentsDirectory()
                    let fileName = self.getRandomFileName() + ".jpg"
                    let fileURL = documentsDirectory.appendingPathComponent(fileName)
                    
                    do {
                        try data.write(to: fileURL)
                        DispatchQueue.main.async {
                            self.parent.onPick([URL(fileURLWithPath: fileName)])
                        }
                    } catch {
                        print(error)
                    }
                }
            }
        }
        
        private func getSelectedImage() -> UIImage? {
            if selectedImage == nil {
                let fileURL = getDocumentsDirectory().appendingPathComponent("selected_image.jpg")
                if let imageData = try? Data(contentsOf: fileURL) {
                    selectedImage = UIImage(data: imageData)
                }
            }
            return selectedImage
        }

        private func getRandomFileName() -> String {
            let characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
            return String((0..<24).map { _ in characters.randomElement()! })
        }
    }
}

func getImageURL(uri: String) -> URL? {
    let supportDirectory = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
    let imagesDirectory = supportDirectory.appendingPathComponent("Images", isDirectory: true)
    return imagesDirectory.appendingPathComponent(uri)
}

func deleteImageFile(uri: String) {
    let supportDirectory = FileManager.default.urls(for: .applicationSupportDirectory, in: .userDomainMask)[0]
    let imagesDirectory = supportDirectory.appendingPathComponent("Images", isDirectory: true)
    let fileURL = imagesDirectory.appendingPathComponent(uri)
    
    do {
        if FileManager.default.fileExists(atPath: fileURL.path) {
            try FileManager.default.removeItem(at: fileURL)
        }
    } catch {
        print(error)
    }
}
