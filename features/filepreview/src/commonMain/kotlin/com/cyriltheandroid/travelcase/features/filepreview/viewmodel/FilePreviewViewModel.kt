package com.cyriltheandroid.travelcase.features.filepreview.viewmodel

import com.cyriltheandroid.core.utils.base.BaseViewModel
import com.cyriltheandroid.travelcase.domain.files.usecase.DeleteFileUseCase
import com.cyriltheandroid.travelcase.features.filepreview.model.FilePreviewUiState
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import com.cyriltheandroid.travelcase.features.model.mapper.toFile
import com.cyriltheandroid.travelcase.features.model.mapper.toFileUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

/**
 * @ObjCName is used to set an exact class name for
 * iOS export. Without this annotation, the class name
 * is prefixed by the module name.
 */
@OptIn(ExperimentalObjCName::class)
@ObjCName("FilePreviewViewModel", exact = true)
class FilePreviewViewModel(
    private val filePreviewData: FilePreviewData,
    private val deleteFileUseCase: DeleteFileUseCase,
) : BaseViewModel() {

    private val _uiState =
        MutableStateFlow(
            FilePreviewUiState(
                filesUris = filePreviewData.files.map { it.toFileUi() },
                index = filePreviewData.initialIndex,
                showButtonActions = filePreviewData.showButtonActions,
            )
        )
    val uiState: StateFlow<FilePreviewUiState> = _uiState.asStateFlow()

    private val _event = MutableStateFlow<FilePreviewEvent>(FilePreviewEvent.Undefined)
    val event: StateFlow<FilePreviewEvent> = _event.asStateFlow()

    fun onDeleteFile(index: Int) {
        val fileToDelete = uiState.value.filesUris[index]

        scope.launch {
            deleteFileUseCase(fileToDelete.toFile(folderId = filePreviewData.folderId))

            _uiState.update { state ->
                state.copy(
                    filesUris = state.filesUris - fileToDelete,
                )
            }

            if (uiState.value.filesUris.isEmpty()) {
                _event.value = FilePreviewEvent.EmptyFilesUris
            }
        }
    }

    fun onDispose() {
        _event.value = FilePreviewEvent.Undefined
    }

    @ObjCName("FilePreviewEvent", exact = true)
    sealed class FilePreviewEvent {
        data object Undefined : FilePreviewEvent()
        data object EmptyFilesUris : FilePreviewEvent()
    }
}