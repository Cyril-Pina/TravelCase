package com.cyriltheandroid.travelcase.android.files.preview

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cyriltheandroid.travelcase.android.files.R
import com.cyriltheandroid.travelcase.android.files.utils.downloadFileInMediaStore
import com.cyriltheandroid.travelcase.android.files.utils.openShareFilesModal
import com.cyriltheandroid.travelcase.designsystem.animation.AnimatedFadeVisibility
import com.cyriltheandroid.travelcase.designsystem.badge.Badge
import com.cyriltheandroid.travelcase.designsystem.button.IconButton
import com.cyriltheandroid.travelcase.designsystem.button.IconButtonIntent
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.features.filepreview.viewmodel.FilePreviewViewModel
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilePreviewScreen(
    filePreviewData: FilePreviewData,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    filePreviewViewModel: FilePreviewViewModel = koinViewModel(
        parameters = { parametersOf(filePreviewData) }
    )
) {
    val uiState by filePreviewViewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val pagerState = rememberPagerState(initialPage = uiState.index) { uiState.filesUris.size }
    var showInFullScreen by remember { mutableStateOf(false) }
    var showDeleteFileAlertDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        filePreviewViewModel.event.collectLatest { event ->
            when (event) {
                FilePreviewViewModel.FilePreviewEvent.EmptyFilesUris -> {
                    onBackClick()
                    filePreviewViewModel.onDispose()
                }

                FilePreviewViewModel.FilePreviewEvent.Undefined -> Unit
            }
        }
    }

    Scaffold(
        modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {},
                navigationIcon = {
                    AnimatedFadeVisibility(
                        visible = !showInFullScreen,
                    ) {
                        IconButton(
                            icon = painterResource(Icons.LeftArrow),
                            onClick = onBackClick,
                            intent = IconButtonIntent.Secondary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
        bottomBar = {
            AnimatedFadeVisibility(visible = !showInFullScreen) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Badge(
                        text = "${if (pagerState.pageCount == 0) 0 else pagerState.currentPage + 1} / ${pagerState.pageCount}"
                    )
                    if (uiState.showButtonActions) {
                        FilePreviewBottomBar(
                            onDownloadFileClick = {
                                val currentFileUri = uiState.filesUris[pagerState.currentPage]
                                val savedFilesUris = downloadFileInMediaStore(
                                    context = context,
                                    filesUris = listOf(currentFileUri.fileUri)
                                )

                                if (savedFilesUris.isNotEmpty()) {
                                    Toast.makeText(
                                        context,
                                        context.getString(R.string.file_preview_image_saved_message),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            onShareFileClick = {
                                val currentFileUri = uiState.filesUris[pagerState.currentPage]
                                openShareFilesModal(context, listOf(currentFileUri.fileUri))
                            },
                            onDeleteFileClick = { showDeleteFileAlertDialog = true },
                        )
                    }
                }
            }
        }
    ) {
        FilePreviewContent(
            context = context,
            modifier = Modifier
                .background(Color(0xFF000000))
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { showInFullScreen = !showInFullScreen }
                ),
            state = pagerState,
            fileUris = uiState.filesUris.map { it.fileUri },
        )
    }

    if (showDeleteFileAlertDialog) {
        DeleteFileAlertDialog(
            page = pagerState.currentPage,
            onConfirm = {
                showDeleteFileAlertDialog = false
                filePreviewViewModel.onDeleteFile(pagerState.currentPage)
            },
            onDismiss = { showDeleteFileAlertDialog = false },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilePreviewPreview(modifier: Modifier = Modifier) {
    FilePreviewScreen(
        onBackClick = {},
        filePreviewData = FilePreviewData(),
        modifier = Modifier.fillMaxSize()
    )
}