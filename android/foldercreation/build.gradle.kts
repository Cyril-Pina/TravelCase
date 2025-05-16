plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "com.cyriltheandroid.travelcase.android.foldercreation"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.koin.androidx.compose)
    implementation(libs.material)
    implementation(libs.material3)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(projects.android.designsystem)
    implementation(projects.android.files)
    implementation(projects.core.model)
    implementation(projects.features.foldercreation)
    debugImplementation(libs.androidx.ui.tooling)
}