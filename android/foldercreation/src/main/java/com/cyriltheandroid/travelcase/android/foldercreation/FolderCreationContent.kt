package com.cyriltheandroid.travelcase.android.foldercreation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.designsystem.animation.AnimatedTopToBottomVisibility
import com.cyriltheandroid.travelcase.designsystem.badge.FolderTypeBadge
import com.cyriltheandroid.travelcase.designsystem.badge.FolderTypeIntent
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.lazygrid.FilesLazyGrid
import com.cyriltheandroid.travelcase.designsystem.lazygrid.FolderAddButton
import com.cyriltheandroid.travelcase.designsystem.modifier.conditional
import com.cyriltheandroid.travelcase.designsystem.textfield.TextField
import com.cyriltheandroid.travelcase.domain.files.model.FolderType
import com.cyriltheandroid.travelcase.feature.foldercreation.model.FolderCreationUiState

@Composable
fun FolderCreationContent(
    uiState: FolderCreationUiState,
    onFolderTypeClick: (FolderType) -> Unit,
    onDeleteFileClick: (index: Int) -> Unit,
    onUpdateTitle: (String) -> Unit,
    onFileClick: (index: Int) -> Unit,
    onAddFileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FolderTypeSelector(
            modifier = Modifier.fillMaxWidth(),
            selectedFolderType = uiState.selectedFolderType,
            onFolderTypeClick = onFolderTypeClick,
        )
        AnimatedTopToBottomVisibility(uiState.showTitleTextField) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.title,
                    onValueChange = onUpdateTitle,
                    label = stringResource(R.string.folder_title_label),
                    leadingIcon = painterResource(Icons.Folder),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    )
                )
                AnimatedContent(
                    targetState = uiState.showFullButton,
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
                            onAddFileClick = onAddFileClick,
                            onDeleteFileClick = onDeleteFileClick,
                            onFileClick = onFileClick,
                            filesUri = uiState.files.map { it.fileUri },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FolderTypeSelector(
    selectedFolderType: FolderType?,
    onFolderTypeClick: (FolderType) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        FolderTypeIntent.entries.forEachIndexed { index, intent ->
            FolderTypeBadge(
                intent = intent,
                clickable = true,
                onClick = { onFolderTypeClick(FolderType.entries[index]) },
                modifier = Modifier
                    .conditional(selectedFolderType != null) {
                        alpha(if (intent.ordinal == selectedFolderType?.ordinal) 1f else 0.3f)
                    }
            )
        }
    }
}