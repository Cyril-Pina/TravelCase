rootProject.name = "TravelCase"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":shared")
include(":core:config")
include(":core:database")
include(":core:datastore")
include(":core:date")
include(":core:model")
include(":core:network")
include(":core:utils")
include(":data:files")
include(":data:travel")
include(":domain:country")
include(":domain:files")
include(":domain:travel")
include(":features:filepreview")
include(":features:foldercreation")
include(":features:folderdetails")
include(":features:model")
include(":features:tripcreation")
include(":features:tripdetails")
include(":features:trips")
include(":android:designsystem")
include(":android:foldercreation")
include(":android:files")
include(":android:trips")
include(":android:tripcreation")
include(":android:tripdetails")
include(":android:folderdetails")
