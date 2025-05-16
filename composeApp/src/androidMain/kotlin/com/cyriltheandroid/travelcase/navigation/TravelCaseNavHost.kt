package com.cyriltheandroid.travelcase.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cyriltheandroid.travelcase.android.files.preview.FilePreviewScreen
import com.cyriltheandroid.travelcase.android.foldercreation.FolderCreationScreen
import com.cyriltheandroid.travelcase.android.folderdetails.FolderDetailsScreen
import com.cyriltheandroid.travelcase.android.tripcreation.TripCreationScreen
import com.cyriltheandroid.travelcase.android.tripdetails.TripDetailsScreen
import com.cyriltheandroid.travelcase.android.trips.TripsScreen

@Composable
fun TravelCaseNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Trips.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Trips.route) {
            TripsScreen(
                modifier = Modifier.fillMaxSize(),
                onAddTripClick = navController::navigateToTripCreation,
                onTripClick = navController::navigateToTripDetails
            )
        }

        composable(Screen.TripCreation.route) {
            TripCreationScreen(
                modifier = Modifier.fillMaxSize(),
                onBackClick = navController::navigateUp,
                onTripCreated = { tripId ->
                    navController.navigateUp()
                    navController.navigateToTripDetails(tripId)
                },
            )
        }

        composable(Screen.TripDetails.route) { navBackStackEntry ->
            val tripId = navBackStackEntry.arguments?.getString("tripId")?.toLong()
            TripDetailsScreen(
                modifier = Modifier.fillMaxSize(),
                tripId = tripId,
                onBackClick = navController::navigateUp,
                onAddFolderClick = navController::navigateToFolderCreation,
                onFolderClick = navController::navigateToFolderDetails,
                onFileClick = navController::navigateToFilePreview,
            )
        }

        composable(Screen.FolderCreation.route) { navBackStackEntry ->
            val tripId = navBackStackEntry.arguments?.getString("tripId")?.toLong()
            FolderCreationScreen(
                modifier = Modifier.fillMaxSize(),
                tripId = tripId,
                onBackClick = navController::navigateUp,
                onFileClick = navController::navigateToFilePreview,
            )
        }

        composable(Screen.FolderDetails.route) { navBackStackEntry ->
            val folderId = navBackStackEntry.arguments?.getString("folderId")?.toLong()

            FolderDetailsScreen(
                modifier = Modifier.fillMaxSize(),
                folderId = folderId,
                onBackClick = navController::navigateUp,
                onFileClick = navController::navigateToFilePreview,
            )
        }

        composable(Screen.FilePreview.route) {
            val filePreview = navController.getFilePreviewSaveStateData()
            navController.emptySaveStateHandle()

            FilePreviewScreen(
                modifier = Modifier.fillMaxSize(),
                onBackClick = navController::navigateUp,
                filePreviewData = filePreview
            )
        }
    }
}