package com.cyriltheandroid.travelcase.android.files.picker

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.cyriltheandroid.travelcase.android.files.model.FileData
import com.cyriltheandroid.travelcase.android.files.utils.convertPdfToImages
import com.cyriltheandroid.travelcase.android.files.utils.copyFileToLocal
import com.cyriltheandroid.travelcase.android.files.utils.isPdf

@Composable
fun FilePicker(
    contentTypes: FileContentType = FileContentType.All,
    onDismiss: (List<FileData>?) -> Unit
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            uri?.let {
                val localFile = copyFileToLocal(context, it)

                val files = if (localFile.isPdf()) {
                    val imageFiles = convertPdfToImages(context, requireNotNull(localFile))
                    imageFiles.map { imageFile ->
                        FileData(uri = imageFile.absolutePath, name = imageFile.name)
                    }
                } else {
                    listOf(FileData(uri = localFile?.absolutePath, name = localFile?.name))
                }

                onDismiss(files)
            } ?: onDismiss(null)
        }
    )

    LaunchedEffect(Unit) {
        launcher.launch(contentTypes.value.contentTypes.toTypedArray())
    }
}

enum class FileContentType(val value: FileContentTypeValue) {
    All(value = FileContentTypeValue.All()),
    Images(value = FileContentTypeValue.Images()),
    PDF(value = FileContentTypeValue.PDF()),
}

sealed class FileContentTypeValue {
    abstract val contentTypes: List<String>

    data class Images(
        override val contentTypes: List<String> = listOf("image/*")
    ) : FileContentTypeValue()

    data class PDF(
        override val contentTypes: List<String> = listOf("application/pdf")
    ) : FileContentTypeValue()

    data class All(
        override val contentTypes: List<String> = listOf(
            "application/pdf",
            "image/*",
        )
    ) : FileContentTypeValue()
}