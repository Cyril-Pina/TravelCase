package com.cyriltheandroid.travelcase.android.tripdetails

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cyriltheandroid.travelcase.android.files.picker.FileContentType
import com.cyriltheandroid.travelcase.android.files.picker.FilePicker
import com.cyriltheandroid.travelcase.designsystem.button.IconButton
import com.cyriltheandroid.travelcase.designsystem.button.IconButtonIntent
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.features.model.FilePreviewData
import com.cyriltheandroid.travelcase.features.tripdetails.viewmodel.TripDetailsViewModel
import com.cyriltheandroid.travelcase.features.tripdetails.viewmodel.TripDetailsViewModel.TripDetailsEvent
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import java.io.File

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailsScreen(
    tripId: Long?,
    onBackClick: () -> Unit,
    onAddFolderClick: (Long?) -> Unit,
    onFolderClick: (Long) -> Unit,
    onFileClick: (FilePreviewData) -> Unit,
    modifier: Modifier = Modifier,
    tripDetailsViewModel: TripDetailsViewModel = koinViewModel(
        parameters = { parametersOf(tripId) }
    ),
) {
    val uiState by tripDetailsViewModel.uiState.collectAsStateWithLifecycle()

    var showDeleteTripAlertDialog by remember { mutableStateOf(false) }
    var showUpdateTripDetailsBottomSheet by remember { mutableStateOf(false) }
    var showBannerPicker by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val maxBannerHeight = 248.dp
    val minBannerHeight = 112.dp
    val maxBannerHeightPx = with(LocalDensity.current) { maxBannerHeight.toPx() }
    val minBannerHeightPx = with(LocalDensity.current) { minBannerHeight.toPx() }

    var headerHeightPx by rememberSaveable { mutableFloatStateOf(maxBannerHeightPx) }
    val headerHeight = with(LocalDensity.current) { headerHeightPx.toDp() }

    val visibilityProgress =
        ((maxBannerHeightPx - headerHeightPx) / (maxBannerHeightPx - minBannerHeightPx))
            .coerceIn(0f, 1f)

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newHeight =
                    (headerHeightPx + delta).coerceIn(minBannerHeightPx, maxBannerHeightPx)
                val consumed = newHeight - headerHeightPx
                headerHeightPx = newHeight
                return Offset(x = 0f, y = consumed)
            }
        }
    }

    LaunchedEffect(Unit) {
        tripDetailsViewModel.event.collectLatest { event ->
            when (event) {
                is TripDetailsEvent.DeleteTrip -> {
                    event.filesToDeleteUris.forEach { file ->
                        File(file).delete()
                    }
                    onBackClick()
                    tripDetailsViewModel.onDispose()
                }

                is TripDetailsEvent.ClickOnFile -> {
                    onFileClick(event.filePreviewData)
                    tripDetailsViewModel.onDispose()
                }

                TripDetailsEvent.Undefined -> Unit
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {
                    uiState.tripUi.tripStepUi?.let { tripStepUi ->
                        TripDetailBadge(
                            modifier = Modifier
                                .alpha(visibilityProgress)
                                .padding(16.dp),
                            tripStepUi = tripStepUi,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        icon = painterResource(Icons.LeftArrow),
                        onClick = onBackClick,
                        intent = IconButtonIntent.Secondary
                    )
                },
                actions = {
                    IconButton(
                        icon = painterResource(Icons.Landscape),
                        onClick = { showBannerPicker = true },
                        intent = IconButtonIntent.Secondary
                    )
                    Spacer(Modifier.size(8.dp))
                    IconButton(
                        icon = painterResource(Icons.Trash),
                        onClick = { showDeleteTripAlertDialog = true },
                        intent = IconButtonIntent.SecondaryDanger
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF2F2F2))
                .nestedScroll(nestedScrollConnection)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight + 32.dp)
                    .background(Color.DarkGray),
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context = context)
                        .crossfade(true)
                        .data(File(uiState.tripUi.bannerUri))
                        .build()
                ),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(headerHeight)
                    .background(Color.Transparent),
                contentAlignment = Alignment.BottomStart,
            ) {
                uiState.tripUi.tripStepUi?.let { tripStepUi ->
                    TripDetailBadge(
                        modifier = Modifier
                            .alpha(1f - visibilityProgress)
                            .padding(16.dp),
                        tripStepUi = tripStepUi,
                    )
                }
            }

            TripDetailsContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = headerHeight)
                    .background(
                        color = Color(0xFFF2F2F2),
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    ),
                uiState = uiState,
                onTripDetailsClick = { showUpdateTripDetailsBottomSheet = true },
                onAddFolderClick = { onAddFolderClick(tripId) },
                onSearchForFolder = tripDetailsViewModel::onSearchForFolder,
                onFolderClick = onFolderClick,
                onFileClick = tripDetailsViewModel::onDisplayFilePreview
            )
        }
    }

    if (showDeleteTripAlertDialog && !uiState.isTripLoading) {
        DeleteTripAlertDialog(
            modifier = Modifier.fillMaxWidth(),
            trip = uiState.tripUi,
            onDismiss = { showDeleteTripAlertDialog = false },
            onConfirm = {
                showDeleteTripAlertDialog = false
                tripDetailsViewModel.onDeleteTrip()
            }
        )
    }

    if (showUpdateTripDetailsBottomSheet) {
        UpdateTripDetailsBottomSheet(
            uiState = uiState.updateTripDetailsUiState,
            onConfirm = {
                showUpdateTripDetailsBottomSheet = false
                tripDetailsViewModel.onUpdateTrip()
            },
            onDismiss = { showUpdateTripDetailsBottomSheet = false },
            onDropDownValueChange = tripDetailsViewModel::onFilterCountry,
            onCountrySelected = { index ->
                tripDetailsViewModel.onCountrySelected(uiState.updateTripDetailsUiState.countries[index])
            },
            onTextFieldValueChange = tripDetailsViewModel::onUpdateTitle,
            onUpdateDates = tripDetailsViewModel::onUpdateDates,
        )
    }

    if (showBannerPicker) {
        FilePicker(contentTypes = FileContentType.Images) { files ->
            showBannerPicker = false
            tripDetailsViewModel.onUpdateBanner(
                fileUri = files?.first()?.uri,
            )
        }
    }
}