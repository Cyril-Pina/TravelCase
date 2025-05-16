package com.cyriltheandroid.travelcase.android.folderdetails

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.android.foldercreation.FolderTypeSelector
import com.cyriltheandroid.travelcase.android.foldercreation.R
import com.cyriltheandroid.travelcase.designsystem.badge.FolderTypeBadge
import com.cyriltheandroid.travelcase.designsystem.badge.FolderTypeIntent
import com.cyriltheandroid.travelcase.designsystem.bottomsheet.BottomSheet
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.lazygrid.FilesLazyGrid
import com.cyriltheandroid.travelcase.designsystem.lazygrid.FolderAddButton
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent
import com.cyriltheandroid.travelcase.designsystem.textfield.TextField
import com.cyriltheandroid.travelcase.domain.files.model.FolderType
import com.cyriltheandroid.travelcase.features.folderdetails.model.UpdateFolderDetailsUiState
import kotlinx.coroutines.launch

@Composable
fun FolderDetailsContent(
    title: String,
    showFullButton: Boolean,
    folderTypeIntent: FolderTypeIntent?,
    filesUri: List<String>,
    onDeleteFileClick: (index: Int) -> Unit,
    onAddFileClick: () -> Unit,
    onFolderDetailsClick: () -> Unit,
    onFileClick: (index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onFolderDetailsClick
                )
        ) {
            folderTypeIntent?.let { intent -> FolderTypeBadge(intent = intent) }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                intent = TextIntent.H1
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        AnimatedContent(
            targetState = showFullButton,
            transitionSpec = {
                (fadeIn(tween(300)) + slideInVertically())
                    .togetherWith(fadeOut(tween(200)) + slideOutVertically())
            },
            label = "FolderContentTransition"
        ) { showFullButton ->
            if (showFullButton) {
                FolderAddButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onAddFileClick
                )
            } else {
                FilesLazyGrid(
                    modifier = Modifier.fillMaxWidth(),
                    onDeleteFileClick = onDeleteFileClick,
                    onAddFileClick = onAddFileClick,
                    filesUri = filesUri,
                    onFileClick = onFileClick,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UpdateFolderDetailsBottomSheet(
    uiState: UpdateFolderDetailsUiState,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onFolderTypeClick: (FolderType) -> Unit,
    onTextFieldValueChange: (String) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val updateTripDetailsSheetState = rememberModalBottomSheetState()

    BottomSheet(
        sheetState = updateTripDetailsSheetState,
        confirmButtonEnabled = uiState.nextButtonEnabled,
        onConfirm = {
            scope.launch { updateTripDetailsSheetState.hide() }.invokeOnCompletion {
                if (!updateTripDetailsSheetState.isVisible) {
                    onConfirm()
                }
            }
        },
        onDismiss = {
            scope.launch { updateTripDetailsSheetState.hide() }.invokeOnCompletion {
                if (!updateTripDetailsSheetState.isVisible) {
                    onDismiss()
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.title,
                onValueChange = onTextFieldValueChange,
                label = stringResource(R.string.folder_title_label),
                leadingIcon = painterResource(Icons.Folder),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            )
            FolderTypeSelector(
                modifier = Modifier.fillMaxWidth(),
                selectedFolderType = uiState.selectedFolderType,
                onFolderTypeClick = onFolderTypeClick,
            )
        }
    }
}

@Preview
@Composable
fun FolderDetailsContentPreview(modifier: Modifier = Modifier) {
    FolderDetailsContent(
        modifier = Modifier.fillMaxSize(),
        title = "Titre",
        showFullButton = true,
        folderTypeIntent = FolderTypeIntent.Ticket,
        filesUri = listOf("test", "testere"),
        onAddFileClick = {},
        onDeleteFileClick = {},
        onFolderDetailsClick = {},
        onFileClick = {},
    )
}