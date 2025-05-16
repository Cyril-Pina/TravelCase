package com.cyriltheandroid.travelcase.android.folderdetails

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyriltheandroid.travelcase.android.files.picker.FilePicker
import com.cyriltheandroid.travelcase.android.files.utils.downloadFileInMediaStore
import com.cyriltheandroid.travelcase.android.files.utils.openShareFilesModal
import com.cyriltheandroid.travelcase.designsystem.alertdialog.AlertDialog
import com.cyriltheandroid.travelcase.designsystem.badge.FolderTypeIntent
import com.cyriltheandroid.travelcase.designsystem.button.IconButton
import com.cyriltheandroid.travelcase.designsystem.button.IconButtonIntent
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.domain.files.model.FolderType
import com.cyriltheandroid.travelcase.features.folderdetails.viewmodel.FolderDetailsViewModel
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderDetailsScreen(
    folderId: Long?,
    onBackClick: () -> Unit,
    onFileClick: (FilePreviewData) -> Unit,
    modifier: Modifier = Modifier,
    folderDetailsViewModel: FolderDetailsViewModel = koinViewModel(
        parameters = { parametersOf(folderId) }
    ),
) {
    val uiState by folderDetailsViewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    var showDeleteFolderAlertDialog by remember { mutableStateOf(false) }
    var showUpdateFolderDetailsBottomSheet by remember { mutableStateOf(false) }
    var showFilePicker by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        folderDetailsViewModel.event.collectLatest { event ->
            when (event) {
                is FolderDetailsViewModel.FolderDetailsEvent.ClickOnFile -> {
                    onFileClick(event.filePreviewData)
                    folderDetailsViewModel.onDispose()
                }

                is FolderDetailsViewModel.FolderDetailsEvent.DeleteFile -> {
                    File(event.fileUri).delete()
                    folderDetailsViewModel.onDispose()
                }

                is FolderDetailsViewModel.FolderDetailsEvent.DeleteFolder -> {
                    event.filesToDeleteUris.forEach { file ->
                        File(file).delete()
                    }
                    onBackClick()
                    folderDetailsViewModel.onDispose()
                }

                FolderDetailsViewModel.FolderDetailsEvent.Undefined -> Unit
            }
        }
    }

    Scaffold(
        modifier = modifier.background(Color.Red),
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {},
                navigationIcon = {
                    IconButton(
                        icon = painterResource(Icons.LeftArrow),
                        onClick = onBackClick,
                        intent = IconButtonIntent.Secondary
                    )
                },
                actions = {
                    IconButton(
                        icon = painterResource(Icons.Download),
                        onClick = {
                            val filesUris = uiState.files.map { it.fileUri }
                            val savedFilesUris = downloadFileInMediaStore(
                                context = context,
                                filesUris = filesUris
                            )

                            if (savedFilesUris.isNotEmpty()) {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.folder_images_saved_message),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        intent = IconButtonIntent.Secondary
                    )
                    Spacer(Modifier.size(8.dp))
                    IconButton(
                        icon = painterResource(Icons.Share),
                        onClick = {
                            val filesUris = uiState.files.map { it.fileUri }
                            openShareFilesModal(context, filesUris)
                        },
                        intent = IconButtonIntent.Secondary
                    )
                    Spacer(Modifier.size(8.dp))
                    IconButton(
                        icon = painterResource(Icons.Trash),
                        onClick = { showDeleteFolderAlertDialog = true },
                        intent = IconButtonIntent.SecondaryDanger
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
    ) { innerPadding ->
        if (uiState.isLoading) {
            FolderDetailsLoading(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            )
        } else {
            FolderDetailsContent(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF2F2F2))
                    .padding(innerPadding)
                    .padding(16.dp),
                title = uiState.title,
                folderTypeIntent = when (uiState.type) {
                    FolderType.Ticket -> FolderTypeIntent.Ticket
                    FolderType.Accommodation -> FolderTypeIntent.Accommodation
                    FolderType.VehicleRental -> FolderTypeIntent.VehicleRental
                    FolderType.Other -> FolderTypeIntent.Other
                    else -> null
                },
                showFullButton = uiState.showFullButton,
                filesUri = uiState.files.map { it.fileUri },
                onDeleteFileClick = folderDetailsViewModel::onDeleteFile,
                onAddFileClick = { showFilePicker = true },
                onFolderDetailsClick = { showUpdateFolderDetailsBottomSheet = true },
                onFileClick = folderDetailsViewModel::onDisplayFilePreview,
            )
        }
    }

    if (showDeleteFolderAlertDialog && !uiState.isLoading) {
        AlertDialog(
            title = stringResource(R.string.delete_folder_alert_dialog_title, uiState.title),
            subtitle = stringResource(R.string.delete_folder_alert_dialog_subtitle, uiState.title),
            actionButtonText = stringResource(R.string.delete_folder_alert_dialog_action_button),
            onConfirm = {
                showDeleteFolderAlertDialog = false
                folderDetailsViewModel.onDeleteFolder()
            },
            onDismiss = { showDeleteFolderAlertDialog = false },
            actionButtonIcon = painterResource(Icons.Trash),
        )
    }

    if (showUpdateFolderDetailsBottomSheet) {
        UpdateFolderDetailsBottomSheet(
            uiState = uiState.updateFolderDetailsUiState,
            onConfirm = {
                showUpdateFolderDetailsBottomSheet = false
                folderDetailsViewModel.onUpdateFolder()
            },
            onDismiss = { showUpdateFolderDetailsBottomSheet = false },
            onFolderTypeClick = folderDetailsViewModel::onUpdateFolderType,
            onTextFieldValueChange = folderDetailsViewModel::onUpdateTitle
        )
    }

    if (showFilePicker) {
        FilePicker { files ->
            showFilePicker = false
            files?.forEach { fileData ->
                folderDetailsViewModel.onAddNewFile(
                    fileUri = fileData.uri,
                    fileName = fileData.name
                )
            }
        }
    }
}