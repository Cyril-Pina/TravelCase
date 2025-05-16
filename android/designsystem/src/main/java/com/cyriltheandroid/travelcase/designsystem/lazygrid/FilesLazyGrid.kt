package com.cyriltheandroid.travelcase.designsystem.lazygrid

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cyriltheandroid.travelcase.android.designsystem.R
import com.cyriltheandroid.travelcase.designsystem.badge.SmallBadge
import com.cyriltheandroid.travelcase.designsystem.button.Button
import com.cyriltheandroid.travelcase.designsystem.button.IconButton
import com.cyriltheandroid.travelcase.designsystem.button.SmallIconButton
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import java.io.File

@Composable
fun FilesLazyGrid(
    onDeleteFileClick: (index: Int) -> Unit,
    onAddFileClick: () -> Unit,
    onFileClick: (index: Int) -> Unit,
    filesUri: List<String>,
    modifier: Modifier = Modifier,
) {
    val items = remember(filesUri) { buildGridItems(filesUri) }
    var fileIndex = 0

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(36.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth(),
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(items, key = { it.hashCode() }) { item ->
                when (item) {
                    GridItem.AddButton -> {
                        AddFileButton(
                            modifier = Modifier.height(125.dp),
                            onClick = onAddFileClick
                        )
                    }

                    GridItem.SpacerItem -> {
                        Spacer(modifier = Modifier.size(0.dp))
                    }

                    is GridItem.File -> {
                        val currentIndex = fileIndex
                        FileCard(
                            modifier = Modifier
                                .clickable { onFileClick(currentIndex) }
                                .animateItem(),
                            fileUri = item.uri,
                            index = fileIndex + 1,
                            onDeleteClick = { onDeleteFileClick(currentIndex) }
                        )
                        fileIndex += 1
                    }
                }
            }
        }
    }
}

@Composable
private fun AddFileButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            modifier = Modifier,
            icon = painterResource(Icons.Add),
            onClick = onClick
        )
    }
}

@Composable
private fun FileCard(
    fileUri: String,
    index: Int,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .height(125.dp)
            .clip(RoundedCornerShape(24.dp)),
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
        SmallIconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(all = 8.dp)
                .shadow(elevation = 3.dp, shape = CircleShape),
            icon = painterResource(Icons.Close),
            onClick = onDeleteClick,
        )
        SmallBadge(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(all = 8.dp),
            text = index.toString(),
        )
    }
}

@Composable
fun FolderAddButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(36.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
    ) {
        Button(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.add_file_button),
            onClick = onClick,
            leadingIcon = painterResource(Icons.Add)
        )
    }
}

fun buildGridItems(filesUri: List<String>): List<GridItem> {
    val items = mutableListOf<GridItem>()

    filesUri.forEachIndexed { index, uri ->
        when {
            index == 0 -> {
                items.add(GridItem.AddButton)
                items.add(GridItem.File(uri))
            }

            index % 3 == 0 -> {
                items.add(GridItem.SpacerItem)
                items.add(GridItem.File(uri))
            }

            else -> {
                items.add(GridItem.File(uri))
            }
        }
    }

    return items
}

sealed interface GridItem {
    data object AddButton : GridItem
    data object SpacerItem : GridItem
    data class File(val uri: String) : GridItem
}

@Preview
@Composable
fun FilesLazyGridPreview(modifier: Modifier = Modifier) {
    FilesLazyGrid(
        modifier = Modifier.fillMaxWidth(),
        filesUri = listOf("Tesdt", "test", "tese", "tesdef", "tesdef"),
        onAddFileClick = {},
        onDeleteFileClick = {},
        onFileClick = {},
    )
}