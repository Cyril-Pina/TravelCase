package com.cyriltheandroid.travelcase.features.folderdetails.viewmodel

import com.cyriltheandroid.core.utils.base.BaseViewModel
import com.cyriltheandroid.travelcase.domain.files.model.Folder
import com.cyriltheandroid.travelcase.domain.files.model.FolderType
import com.cyriltheandroid.travelcase.domain.files.usecase.AddFileUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.DeleteFileUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.DeleteFolderUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.GetFolderDetailsUseCase
import com.cyriltheandroid.travelcase.domain.files.usecase.UpdateFolderDetailsUseCase
import com.cyriltheandroid.travelcase.features.folderdetails.mapper.updateFolder
import com.cyriltheandroid.travelcase.features.folderdetails.model.FolderDetailsUiState
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import com.cyriltheandroid.travelcase.features.model.FileUi
import com.cyriltheandroid.travelcase.features.model.mapper.toFile
import com.cyriltheandroid.travelcase.features.model.mapper.toFileUi
import com.cyriltheandroid.travelcase.features.model.mapper.toFilesUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
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
@ObjCName("FolderDetailsViewModel", exact = true)
class FolderDetailsViewModel(
    private val folderId: Long,
    private val getFolderDetailsUseCase: GetFolderDetailsUseCase,
    private val updateFolderDetailsUseCase: UpdateFolderDetailsUseCase,
    private val deleteFolderUseCase: DeleteFolderUseCase,
    private val deleteFileUseCase: DeleteFileUseCase,
    private val addFileUseCase: AddFileUseCase,
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(FolderDetailsUiState())
    val uiState: StateFlow<FolderDetailsUiState> = _uiState.asStateFlow()

    private val _event = MutableStateFlow<FolderDetailsEvent>(FolderDetailsEvent.Undefined)
    val event: StateFlow<FolderDetailsEvent> = _event.asStateFlow()

    private lateinit var folder: Folder

    init {
        scope.launch {
            getFolderDetailsUseCase(folderId).collectLatest {
                folder = it

                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        title = it.title,
                        type = it.type,
                        files = it.files.toFilesUi(),
                        showFullButton = it.files.isEmpty(),
                        updateFolderDetailsUiState = state.updateFolderDetailsUiState.copy(
                            title = it.title,
                            selectedFolderType = it.type,
                            nextButtonEnabled = false
                        )
                    )
                }
            }
        }
    }

    fun onAddNewFile(fileUri: String?, fileName: String?) {
        if (fileUri == null || fileName == null) {
            return
        }

        scope.launch {
            val newFile = FileUi(name = fileName, fileUri = fileUri)

            addFileUseCase(newFile.toFile(folderId))

            _uiState.update { state ->
                state.copy(
                    files = state.files + newFile,
                    showFullButton = false,
                )
            }
        }
    }

    fun onDisplayFilePreview(index: Int) {
        _event.value = FolderDetailsEvent.ClickOnFile(
            filePreviewData = FilePreviewData(
                files = folder.files,
                folderId = folderId,
                initialIndex = index
            )
        )
    }

    fun onDeleteFile(index: Int) {
        scope.launch {
            val fileToDelete = folder.files[index]

            deleteFileUseCase(file = fileToDelete)

            _uiState.update { state ->
                state.copy(
                    files = state.files - fileToDelete.toFileUi(),
                    showFullButton = state.files.size == 1,
                )
            }

            _event.value = FolderDetailsEvent.DeleteFile(fileToDelete.fileUri)
        }
    }

    fun onDeleteFolder() {
        scope.launch {
            val filesToDeleteUris = folder.files.map { file -> file.fileUri }

            deleteFolderUseCase(folder)
            _event.value = FolderDetailsEvent.DeleteFolder(filesToDeleteUris)
        }
    }

    fun onUpdateTitle(newTitle: String) {
        _uiState.update { state ->
            state.copy(
                updateFolderDetailsUiState = state.updateFolderDetailsUiState.copy(
                    title = newTitle,
                    nextButtonEnabled = newTitle != state.title
                            || state.updateFolderDetailsUiState.selectedFolderType != state.type
                ),
            )
        }
    }

    fun onUpdateFolderType(folderType: FolderType) {
        _uiState.update { state ->
            state.copy(
                updateFolderDetailsUiState = state.updateFolderDetailsUiState.copy(
                    selectedFolderType = folderType,
                    nextButtonEnabled = state.updateFolderDetailsUiState.title != state.title
                            || folderType != state.type
                ),
            )
        }
    }

    fun onUpdateFolder() {
        scope.launch {
            val updatedTrip = folder.updateFolder(uiState.value.updateFolderDetailsUiState)
            updateFolderDetailsUseCase(updatedTrip)
        }
    }

    fun onDispose() {
        _event.value = FolderDetailsEvent.Undefined
    }

    @ObjCName("FolderDetailsEvent", exact = true)
    sealed class FolderDetailsEvent {
        data object Undefined : FolderDetailsEvent()
        data class ClickOnFile(val filePreviewData: FilePreviewData) : FolderDetailsEvent()
        data class DeleteFile(val fileUri: String) : FolderDetailsEvent()
        data class DeleteFolder(val filesToDeleteUris: List<String>) : FolderDetailsEvent()
    }
}