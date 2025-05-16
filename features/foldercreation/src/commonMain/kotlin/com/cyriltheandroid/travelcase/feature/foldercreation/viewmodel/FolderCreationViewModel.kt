package com.cyriltheandroid.travelcase.feature.foldercreation.viewmodel

import com.cyriltheandroid.core.utils.base.BaseViewModel
import com.cyriltheandroid.travelcase.domain.files.model.FolderType
import com.cyriltheandroid.travelcase.domain.files.usecase.AddFolderToTripUseCase
import com.cyriltheandroid.travelcase.feature.foldercreation.mapper.toFolder
import com.cyriltheandroid.travelcase.feature.foldercreation.model.FolderCreationUiState
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import com.cyriltheandroid.travelcase.features.model.FileUi
import com.cyriltheandroid.travelcase.features.model.mapper.toFiles
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
@ObjCName("FolderCreationViewModel", exact = true)
class FolderCreationViewModel(
    private val tripId: Long,
    private val addFolderToTripUseCase: AddFolderToTripUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(FolderCreationUiState())
    val uiState: StateFlow<FolderCreationUiState> = _uiState.asStateFlow()

    private val _event = MutableStateFlow<FolderCreationEvent>(FolderCreationEvent.Undefined)
    val event: StateFlow<FolderCreationEvent> = _event.asStateFlow()

    fun addFolder() {
        scope.launch {
            addFolderToTripUseCase(folder = uiState.value.toFolder(tripId))
        }
    }

    fun onUpdateFolderType(folderType: FolderType) {
        _uiState.update { state ->
            state.copy(
                selectedFolderType = folderType,
                showTitleTextField = true,
            )
        }
    }

    fun onUpdateTitle(newTitle: String) {
        _uiState.update { state ->
            state.copy(
                title = newTitle,
                nextButtonEnabled = newTitle.isNotBlank()
                        && state.selectedFolderType != null
                        && state.files.isNotEmpty()
            )
        }
    }

    fun onAddNewFile(fileUri: String?, fileName: String?) {
        if (fileUri == null || fileName == null) {
            return
        }

        _uiState.update { state ->
            val newFile = FileUi(
                name = fileName,
                fileUri = fileUri
            )

            state.copy(
                showFullButton = false,
                files = state.files + newFile,
                nextButtonEnabled = state.title.isNotBlank()
                        && state.selectedFolderType != null
            )
        }
    }

    fun onDisplayFilePreview(index: Int) {
        _event.value = FolderCreationEvent.ClickOnFile(
            filePreviewData = FilePreviewData(
                files = uiState.value.files.toFiles(),
                initialIndex = index,
                showButtonActions = false,
            )
        )
    }

    fun onDeleteFile(index: Int) {
        val fileToDelete = _uiState.value.files[index]

        _uiState.update { state ->
            state.copy(
                files = state.files - fileToDelete,
                showFullButton = state.files.size == 1,
                nextButtonEnabled = false
            )
        }

        _event.value = FolderCreationEvent.DeleteFile(fileToDelete.fileUri)
    }

    fun onBackClick() {
        _event.value =
            FolderCreationEvent.BackClick(filesToDeleteUris = uiState.value.files.map { it.fileUri })
    }

    fun onDispose() {
        _event.value = FolderCreationEvent.Undefined
    }

    @ObjCName("FolderCreationEvent", exact = true)
    sealed class FolderCreationEvent {
        data object Undefined : FolderCreationEvent()
        data class ClickOnFile(val filePreviewData: FilePreviewData) : FolderCreationEvent()
        data class DeleteFile(val fileToDeleteUri: String) : FolderCreationEvent()
        data class BackClick(val filesToDeleteUris: List<String>) : FolderCreationEvent()
    }
}