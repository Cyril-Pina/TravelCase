package com.cyriltheandroid.travelcase.designsystem.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cyriltheandroid.travelcase.designsystem.badge.FolderTypeBadge
import com.cyriltheandroid.travelcase.designsystem.badge.FolderTypeIntent
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent
import java.io.File

@Composable
fun FolderCard(
    title: String,
    filesUri: List<String>,
    folderTypeIntent: FolderTypeIntent?,
    onFileClick: (Int, String) -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .clickable(onClick = onClick)
            .background(color = Color(0xFFFFFFFF), RoundedCornerShape(32.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            LazyRow(
                contentPadding = if (filesUri.isEmpty()) PaddingValues(8.dp) else PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                itemsIndexed(filesUri) { index, fileUri ->
                    Box(
                        modifier = Modifier
                            .size(width = 86.dp, height = 125.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .clickable { onFileClick(index, fileUri) }
                    ) {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(context = context)
                                    .crossfade(true)
                                    .data(File(fileUri))
                                    .build()
                            ),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
            folderTypeIntent?.let { intent ->
                FolderTypeBadge(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    intent = intent
                )
            }
            Spacer(Modifier.size(4.dp))
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = title,
                intent = TextIntent.H3
            )
            Spacer(Modifier.size(16.dp))
        }
    }
}

@Preview
@Composable
fun FolderCardPreview() {
    FolderCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
        folderTypeIntent = FolderTypeIntent.Ticket,
        filesUri = listOf("tes", "tes", "tes", "tes", "tes", "tes", "tes"),
        title = "Billet aller CDG - Brésil",
        onFileClick = {_, _ ->},
    )
}