package com.cyriltheandroid.travelcase.android.files.preview

import com.cyriltheandroid.travelcase.android.files.R
import com.cyriltheandroid.travelcase.designsystem.icon.Icons

sealed class FilePreviewButtonAction {
    abstract val iconResId: Int
    abstract val textResId: Int
    abstract val action: FilePreviewAction

    data class Download(
        override val iconResId: Int = Icons.Download,
        override val textResId: Int = R.string.file_preview_download_action,
        override val action: FilePreviewAction = FilePreviewAction.Download,
    ) : FilePreviewButtonAction()

    data class Share(
        override val iconResId: Int = Icons.Share,
        override val textResId: Int = R.string.file_preview_share_action,
        override val action: FilePreviewAction = FilePreviewAction.Share,
    ) : FilePreviewButtonAction()

    data class Delete(
        override val iconResId: Int = Icons.Trash,
        override val textResId: Int = R.string.file_preview_delete_action,
        override val action: FilePreviewAction = FilePreviewAction.Delete,
    ) : FilePreviewButtonAction()
}

val filePreviewButtonActions = listOf(
    FilePreviewButtonAction.Download(),
    FilePreviewButtonAction.Share(),
    FilePreviewButtonAction.Delete(),
)

enum class FilePreviewAction {
    Download,
    Share,
    Delete,
}