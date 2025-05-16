plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    androidLibrary {
        namespace = "com.cyriltheandroid.travelcase.features.model"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "FeaturesTripDetails"
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.date)
            implementation(projects.core.model)
            implementation(projects.core.utils)
            implementation(projects.domain.travel)
            implementation(projects.domain.files)
            implementation(libs.kotlinx.serialization.json)
        }

        androidMain.dependencies {
            // To use @MyParcelize annotation.
            compilerOptions.freeCompilerArgs.addAll(
                "-P",
                "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=com.cyriltheandroid.core.utils.base.MyParcelize"
            )
        }

        iosMain.dependencies {}
    }
}