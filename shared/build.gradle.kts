import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("co.touchlab.skie") version "0.10.1"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
            api(projects.core.database)
            api(projects.core.model)
            api(projects.core.utils)
            api(projects.data.files)
            api(projects.data.travel)
            api(projects.domain.country)
            api(projects.domain.files)
            api(projects.domain.travel)
            api(projects.features.filepreview)
            api(projects.features.foldercreation)
            api(projects.features.folderdetails)
            api(projects.features.model)
            api(projects.features.trips)
            api(projects.features.tripcreation)
            api(projects.features.tripdetails)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
        }
    }
}

android {
    namespace = "com.cyriltheandroid.travelcase.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
