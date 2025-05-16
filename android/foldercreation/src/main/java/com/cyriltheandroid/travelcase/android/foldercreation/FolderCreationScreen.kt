package com.cyriltheandroid.travelcase.android.foldercreation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyriltheandroid.travelcase.android.files.picker.FilePicker
import com.cyriltheandroid.travelcase.designsystem.button.IconButton
import com.cyriltheandroid.travelcase.designsystem.button.IconButtonIntent
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.feature.foldercreation.viewmodel.FolderCreationViewModel
import com.cyriltheandroid.travelcase.feature.foldercreation.viewmodel.FolderCreationViewModel.FolderCreationEvent
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FolderCreationScreen(
    tripId: Long?,
    onBackClick: () -> Unit,
    onFileClick: (FilePreviewData) -> Unit,
    modifier: Modifier = Modifier,
    folderCreationViewModel: FolderCreationViewModel = koinViewModel(
        parameters = { parametersOf(tripId) }
    )
) {
    val uiState by folderCreationViewModel.uiState.collectAsStateWithLifecycle()
    var showFilePicker by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        folderCreationViewModel.event.collectLatest { event ->
            when (event) {
                is FolderCreationEvent.BackClick -> {
                    event.filesToDeleteUris.forEach { file ->
                        File(file).delete()
                    }
                    onBackClick()
                    folderCreationViewModel.onDispose()
                }

                is FolderCreationEvent.ClickOnFile -> {
                    onFileClick(event.filePreviewData)
                    folderCreationViewModel.onDispose()
                }

                is FolderCreationEvent.DeleteFile -> {
                    File(event.fileToDeleteUri).delete()
                    folderCreationViewModel.onDispose()
                }

                FolderCreationEvent.Undefined -> Unit
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {},
                navigationIcon = {
                    IconButton(
                        icon = painterResource(Icons.LeftArrow),
                        onClick = folderCreationViewModel::onBackClick,
                        intent = IconButtonIntent.Secondary
                    )
                },
                actions = {
                    IconButton(
                        modifier = Modifier.alpha(if (uiState.nextButtonEnabled) 1f else .5f),
                        icon = painterResource(Icons.Check),
                        onClick = {
                            folderCreationViewModel.addFolder()
                            onBackClick()
                        },
                        enabled = uiState.nextButtonEnabled
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
    ) { innerPadding ->
        FolderCreationContent(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F2F2))
                .padding(top = innerPadding.calculateTopPadding())
                .padding(16.dp),
            uiState = uiState,
            onFolderTypeClick = folderCreationViewModel::onUpdateFolderType,
            onUpdateTitle = folderCreationViewModel::onUpdateTitle,
            onAddFileClick = { showFilePicker = true },
            onDeleteFileClick = folderCreationViewModel::onDeleteFile,
            onFileClick = folderCreationViewModel::onDisplayFilePreview
        )
    }

    if (showFilePicker) {
        FilePicker { files ->
            showFilePicker = false
            files?.forEach { fileData ->
                folderCreationViewModel.onAddNewFile(
                    fileUri = fileData.uri,
                    fileName = fileData.name
                )
            }
        }
    }
}

@Preview
@Composable
fun FolderCreationScreenPreview() {
    FolderCreationScreen(
        modifier = Modifier.fillMaxSize(),
        onBackClick = {},
        tripId = 0L,
        onFileClick = {}
    )
}