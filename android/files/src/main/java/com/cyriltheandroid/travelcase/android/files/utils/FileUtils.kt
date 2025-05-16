package com.cyriltheandroid.travelcase.android.files.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.core.content.FileProvider
import androidx.core.graphics.createBitmap
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random

fun copyFileToLocal(context: Context, uri: Uri): File? {
    val contentResolver = context.contentResolver
    val inputStream = contentResolver.openInputStream(uri) ?: return null
    val folderName = generateRandomFolderName()
    val folder = File(context.filesDir, folderName)
    if (!folder.exists()) folder.mkdirs()

    val fileName = queryFileName(contentResolver, uri) ?: "document"

    val file = File(folder, fileName)

    inputStream.use { input ->
        FileOutputStream(file).use { output ->
            input.copyTo(output)
        }
    }

    return file
}

fun queryFileName(contentResolver: ContentResolver, uri: Uri): String? {
    val returnCursor = contentResolver.query(uri, null, null, null, null)
    returnCursor?.use {
        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (it.moveToFirst() && nameIndex != -1) {
            return it.getString(nameIndex)
        }
    }
    return null
}

fun downloadFileInMediaStore(context: Context, filesUris: List<String>): List<Uri> {
    val contentResolver = context.contentResolver
    val savedUris = mutableListOf<Uri>()

    for (fileUri in filesUris) {
        val file = File(fileUri)
        val fileName = "${System.currentTimeMillis()}-${file.name}"
        val uri = file.toUri()

        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(
                MediaStore.Images.Media.RELATIVE_PATH,
                Environment.DIRECTORY_PICTURES + "/TravelCase"
            )
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        val galleryUri = contentResolver
            .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            ?: continue

        try {
            val inputStream = contentResolver.openInputStream(uri)
            val outputStream = contentResolver.openOutputStream(galleryUri)

            inputStream?.use { input ->
                outputStream?.use { output ->
                    input.copyTo(output)
                }
            }

            contentValues.clear()
            contentValues.put(MediaStore.Images.Media.IS_PENDING, 0)
            contentResolver.update(galleryUri, contentValues, null, null)

            savedUris.add(galleryUri)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    return savedUris
}

fun openShareFilesModal(context: Context, fileUris: List<String>) {
    val uris = fileUris.map { fileUri ->
        val file = File(fileUri)
        FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    val shareIntent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
        type = "image/*"
        putParcelableArrayListExtra(Intent.EXTRA_STREAM, ArrayList(uris))
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(Intent.createChooser(shareIntent, null))
}

fun convertPdfToImages(context: Context, pdfFile: File): List<File> {
    val images = mutableListOf<File>()

    if (!pdfFile.exists()) return emptyList()

    val fileDescriptor = ParcelFileDescriptor.open(pdfFile, ParcelFileDescriptor.MODE_READ_ONLY)
    val pdfRenderer = PdfRenderer(fileDescriptor)

    for (i in 0 until pdfRenderer.pageCount) {
        val page = pdfRenderer.openPage(i)

        val bitmap = createBitmap(page.width, page.height)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        page.close()

        val imageFile = File(
            context.filesDir,
            "${pdfFile.name} ($i).jpg"
        )

        FileOutputStream(imageFile).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        }

        images.add(imageFile)
    }

    pdfRenderer.close()
    fileDescriptor.close()

    return images
}

fun generateRandomFolderName() = Random.nextString(length = 24)

fun Random.nextString(
    length: Int,
    charset: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
): String {
    return (1..length)
        .map { charset.random(this) }
        .joinToString("")
}

fun File?.isPdf(): Boolean {
    return this?.extension?.equals("pdf", ignoreCase = true) == true
}