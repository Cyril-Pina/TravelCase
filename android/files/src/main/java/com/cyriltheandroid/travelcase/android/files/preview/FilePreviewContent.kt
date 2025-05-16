package com.cyriltheandroid.travelcase.android.files.preview

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import com.cyriltheandroid.travelcase.android.files.R
import com.cyriltheandroid.travelcase.designsystem.alertdialog.AlertDialog
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent
import java.io.File
import kotlin.math.abs

@Composable
fun FilePreviewContent(
    state: PagerState,
    context: Context,
    fileUris: List<String>,
    modifier: Modifier = Modifier,
) {
    val scale = remember { mutableFloatStateOf(1f) }
    val offset = remember { mutableStateOf(Offset.Zero) }
    val imageSize = remember { mutableStateOf(Size.Zero) }
    val containerSize = remember { mutableStateOf(Size.Zero) }
    val isZoomed = remember { mutableStateOf(false) }

    HorizontalPager(
        modifier = modifier,
        state = state,
        userScrollEnabled = !isZoomed.value
    ) { page ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .onGloballyPositioned { coordinates ->
                    containerSize.value = coordinates.size.toSize()
                }
                .pointerInput(Unit) {
                    awaitPointerEventScope {
                        while (true) {
                            val event = awaitPointerEvent()
                            val zoomChange = event.calculateZoom()
                            val panChange = event.calculatePan()

                            val minScale = minOf(
                                imageSize.value.width / size.width,
                                imageSize.value.height / size.height
                            ).coerceAtMost(1f)

                            // On ne permet le zoom que si le scroll n'est pas en cours
                            if (!state.isScrollInProgress) {
                                if (abs(zoomChange - 1f) > 0.05f) {
                                    isZoomed.value = true
                                    val newScale = scale.floatValue * zoomChange
                                    scale.floatValue = newScale.coerceIn(minScale, 5f)

                                    // Calcul des limites de déplacement
                                    val scaledWidth = imageSize.value.width * scale.floatValue
                                    val scaledHeight = imageSize.value.height * scale.floatValue
                                    val maxOffsetX = (scaledWidth - containerSize.value.width) / 2
                                    val maxOffsetY = (scaledHeight - containerSize.value.height) / 2

                                    // Limitation du déplacement aux bords de l'image
                                    offset.value = Offset(
                                        (offset.value.x + panChange.x).coerceIn(-maxOffsetX, maxOffsetX),
                                        (offset.value.y + panChange.y).coerceIn(-maxOffsetY, maxOffsetY)
                                    )
                                } else if (isZoomed.value) {
                                    // Si l'image est zoomée, on continue de gérer le zoom et le déplacement
                                    val newScale = scale.floatValue * zoomChange
                                    scale.floatValue = newScale.coerceIn(minScale, 5f)

                                    // Si l'image revient à sa taille initiale, on désactive le mode zoom
                                    if (abs(scale.floatValue - minScale) < 0.01f) {
                                        isZoomed.value = false
                                        scale.floatValue = minScale
                                        offset.value = Offset.Zero
                                    } else {
                                        // Calcul des limites de déplacement
                                        val scaledWidth = imageSize.value.width * scale.floatValue
                                        val scaledHeight = imageSize.value.height * scale.floatValue
                                        val maxOffsetX = (scaledWidth - containerSize.value.width) / 2
                                        val maxOffsetY = (scaledHeight - containerSize.value.height) / 2

                                        // Limitation du déplacement aux bords de l'image
                                        offset.value = Offset(
                                            (offset.value.x + panChange.x).coerceIn(-maxOffsetX, maxOffsetX),
                                            (offset.value.y + panChange.y).coerceIn(-maxOffsetY, maxOffsetY)
                                        )
                                    }
                                } else if (abs(panChange.x) > abs(panChange.y) * 1.5) {
                                    // Si le déplacement est principalement horizontal et l'image n'est pas zoomée
                                    isZoomed.value = false
                                    scale.floatValue = minScale
                                    offset.value = Offset.Zero
                                }
                            }
                        }
                    }
                }
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scale.floatValue,
                        scaleY = scale.floatValue,
                        translationX = offset.value.x,
                        translationY = offset.value.y
                    )
                    .onGloballyPositioned { coordinates ->
                        imageSize.value = coordinates.size.toSize()
                    },
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context = context)
                        .crossfade(true)
                        .data(File(fileUris[page]))
                        .scale(Scale.FIT)
                        .build()
                ),
                contentDescription = null,
            )
        }
    }
}

@Composable
internal fun FilePreviewBottomBar(
    onDownloadFileClick: () -> Unit,
    onShareFileClick: () -> Unit,
    onDeleteFileClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color(0xFFFFFFFF), CircleShape)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            filePreviewButtonActions.forEach { item ->
                FilePreviewBottomBarItem(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            when (item.action) {
                                FilePreviewAction.Download -> onDownloadFileClick()
                                FilePreviewAction.Share -> onShareFileClick()
                                FilePreviewAction.Delete -> onDeleteFileClick()
                            }
                        }
                        .padding(16.dp),
                    icon = painterResource(item.iconResId),
                    text = stringResource(item.textResId),
                )
            }
        }
    }
}

@Composable
internal fun FilePreviewBottomBarItem(
    icon: Painter,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
        )
        Text(
            text = text,
            intent = TextIntent.SmallBold,
        )
    }
}

@Composable
internal fun DeleteFileAlertDialog(
    page: Int,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        title = stringResource(R.string.file_preview_alert_dialog_title, page + 1),
        subtitle = stringResource(R.string.file_preview_alert_dialog_subtitle, page + 1),
        actionButtonText = stringResource(R.string.file_preview_alert_dialog_action_button),
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        actionButtonIcon = painterResource(Icons.Trash)
    )
}