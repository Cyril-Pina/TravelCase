package com.cyriltheandroid.travelcase.android.tripdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.android.tripcreation.TripCreationContent
import com.cyriltheandroid.travelcase.core.model.mapper.toCountryIntent
import com.cyriltheandroid.travelcase.designsystem.alertdialog.AlertDialog
import com.cyriltheandroid.travelcase.designsystem.badge.CountryBadge
import com.cyriltheandroid.travelcase.designsystem.badge.FolderTypeIntent
import com.cyriltheandroid.travelcase.designsystem.badge.TripStepBadge
import com.cyriltheandroid.travelcase.designsystem.badge.TripStepIntent
import com.cyriltheandroid.travelcase.designsystem.bottomsheet.BottomSheet
import com.cyriltheandroid.travelcase.designsystem.button.IconButton
import com.cyriltheandroid.travelcase.designsystem.card.FolderCard
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent
import com.cyriltheandroid.travelcase.designsystem.textfield.TextField
import com.cyriltheandroid.travelcase.domain.files.model.FolderType
import com.cyriltheandroid.travelcase.features.model.TripStepUi
import com.cyriltheandroid.travelcase.features.model.TripUi
import com.cyriltheandroid.travelcase.features.tripdetails.model.TripDetailsUiState
import com.cyriltheandroid.travelcase.features.tripdetails.model.UpdateTripDetailsUiState
import kotlinx.coroutines.launch

@Composable
fun TripDetailsContent(
    uiState: TripDetailsUiState,
    onTripDetailsClick: () -> Unit,
    onAddFolderClick: () -> Unit,
    onSearchForFolder: (String) -> Unit,
    onFolderClick: (Long) -> Unit,
    onFileClick: (Long, Int, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navigationBars = WindowInsets.navigationBars.asPaddingValues()

    LazyColumn(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 16.dp,
            end = 16.dp,
            bottom = navigationBars.calculateBottomPadding() + 16.dp
        ),
        userScrollEnabled = !uiState.isTripLoading && !uiState.isFoldersLoading,
    ) {
        stickyHeader {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF2F2F2))
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (uiState.isTripLoading) {
                    TripDetailsHeaderLoading(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                } else {
                    TripDetailsHeader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = onTripDetailsClick
                            ),
                        trip = uiState.tripUi,
                    )
                }
                if (uiState.isFoldersLoading) {
                    TripSearchBarLoading(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                    )
                } else {
                    TripSearchBar(
                        modifier = Modifier
                            .fillMaxWidth(),
                        onAddFolderClick = onAddFolderClick,
                        onSearchForFolder = onSearchForFolder,
                    )
                }
            }
        }

        if (uiState.isFoldersLoading) {
            item {
                TripDetailsFolderLazyColumnLoading(modifier = Modifier.padding(horizontal = 16.dp))
            }
        } else {
            items(uiState.tripDetailsFoldersUiState.folders, key = { it.id }) { folder ->
                FolderCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    title = folder.title,
                    filesUri = folder.files.map { it.fileUri },
                    folderTypeIntent = when (folder.type) {
                        FolderType.Ticket -> FolderTypeIntent.Ticket
                        FolderType.Accommodation -> FolderTypeIntent.Accommodation
                        FolderType.VehicleRental -> FolderTypeIntent.VehicleRental
                        FolderType.Other -> FolderTypeIntent.Other
                        null -> null
                    },
                    onClick = { onFolderClick(folder.id) },
                    onFileClick = { fileIndex, fileUri ->
                        onFileClick(folder.id, fileIndex, fileUri)
                    },
                )
            }
        }
    }
}

@Composable
private fun TripDetailsHeader(
    trip: TripUi,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = trip.title,
                intent = TextIntent.H1,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
            trip.country?.toCountryIntent()?.let { countryIntent ->
                CountryBadge(intent = countryIntent)
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = if (trip.departureDate != null && trip.returnDate != null) {
                pluralStringResource(
                    R.plurals.trip_details_header_subtitle,
                    trip.daysOfTrip,
                    requireNotNull(trip.departureDate),
                    requireNotNull(trip.returnDate),
                    trip.daysOfTrip
                )
            } else "",
            intent = TextIntent.SmallDim,
        )
    }
}

@Composable
private fun TripSearchBar(
    onAddFolderClick: () -> Unit,
    onSearchForFolder: (query: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf("") }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(
            icon = painterResource(Icons.Add),
            onClick = onAddFolderClick
        )
        TextField(
            label = stringResource(R.string.search_folder_label),
            value = value,
            onValueChange = {
                value = it
                onSearchForFolder(it)
            },
            leadingIcon = painterResource(Icons.Search),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            )
        )
    }
}

@Composable
internal fun DeleteTripAlertDialog(
    trip: TripUi,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AlertDialog(
        modifier = modifier,
        title = stringResource(R.string.delete_trip_alert_dialog_title, trip.title),
        subtitle = stringResource(R.string.delete_trip_alert_dialog_subtitle, trip.title),
        actionButtonText = stringResource(R.string.delete_trip_alert_dialog_action_button),
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        actionButtonIcon = painterResource(Icons.Trash)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UpdateTripDetailsBottomSheet(
    uiState: UpdateTripDetailsUiState,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onDropDownValueChange: (String) -> Unit,
    onTextFieldValueChange: (String) -> Unit,
    onCountrySelected: (Int) -> Unit,
    onUpdateDates: (Long?, Long?) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val updateTripDetailsSheetState = rememberModalBottomSheetState()

    BottomSheet(
        sheetState = updateTripDetailsSheetState,
        confirmButtonEnabled = uiState.nextButtonEnabled,
        onConfirm = {
            scope.launch { updateTripDetailsSheetState.hide() }.invokeOnCompletion {
                if (!updateTripDetailsSheetState.isVisible) {
                    onConfirm()
                }
            }
        },
        onDismiss = {
            scope.launch { updateTripDetailsSheetState.hide() }.invokeOnCompletion {
                if (!updateTripDetailsSheetState.isVisible) {
                    onDismiss()
                }
            }
        }
    ) {
        TripCreationContent(
            modifier = Modifier.padding(16.dp),
            onDropDownValueChange = onDropDownValueChange,
            onTextFieldValueChange = onTextFieldValueChange,
            onCountrySelected = onCountrySelected,
            onUpdateDates = onUpdateDates,
            queriedCountry = uiState.queriedCountry,
            countries = uiState.countries,
            tripTitle = uiState.title,
            departureDate = uiState.departureDate,
            returnDate = uiState.returnDate,
            departureDateMillis = uiState.departureDateMillis,
            returnDateMillis = uiState.returnDateMillis,
        )
    }
}

@Composable
internal fun TripDetailBadge(
    tripStepUi: TripStepUi,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        TripStepBadge(
            intent = when (tripStepUi) {
                is TripStepUi.Incoming -> TripStepIntent.Incoming(tripStepUi.daysLeft)
                TripStepUi.Pending -> TripStepIntent.Pending
                TripStepUi.Finished -> TripStepIntent.Finished
            },
        )
    }
}