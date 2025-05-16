# TravelCase

TravelCase is a modern mobile application designed to simplify travel document management. It provides a centralized solution for organizing and accessing all your travel-related documents in one place.

## 🚀 Features

- Organize travel documents by trips and folders
- Support for various document types
- Cross-platform availability (Android & iOS)
- Modern and intuitive user interface
- Offline access to your documents

## 🛠️ Technology Stack

- **Kotlin Multiplatform (KMP)** for shared business logic
- **Jetpack Compose** for Android UI
- **SwiftUI** for iOS UI
- **Multi-modular architecture** for better code organization and maintainability

## 📱 Installation

1. Clone the repository:
```bash
git clone https://github.com/Cyril-Pina/TravelCase.git
```

2. Open the project in Android Studio or Xcode

3. For Android:
   - Open the project in Android Studio
   - Sync Gradle files
   - Run the app on your device or emulator

4. For iOS:
   - Open the `iosApp` folder in Xcode
   - Install dependencies using CocoaPods
   - Run the app on your device or simulator

## 🏗️ Project Architecture

The project follows a multi-modular architecture with the following structure:

- **:composeApp** - Main Compose Multiplatform application
- **:shared** - Shared code between platforms
- **:core** - Core modules (config, database, datastore, etc.)
- **:data** - Data layer modules
- **:domain** - Domain layer modules
- **:features** - Feature modules
- **:android** - Android-specific implementations
- **:iosApp** - iOS application

## 🤝 Contributing

We welcome contributions to TravelCase! Here's how you can help:

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Contact

- **[LinkedIn](https://www.linkedin.com/in/cyril-pina-lopes/)**
- **[YouTube](https://www.youtube.com/@cyriltheandroid)**

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.