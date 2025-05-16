plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
}

kotlin {
    androidLibrary {
        namespace = "com.cyriltheandroid.travelcase.core.model"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "CoreModel"
        }
    }

    sourceSets {
        commonMain.dependencies {}

        androidMain.dependencies {
            implementation(projects.android.designsystem)
        }

        iosMain.dependencies {}
    }
}