package com.cyriltheandroid.travelcase.designsystem.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.android.designsystem.R
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent

@Composable
fun DateTextField(
    departureDate: String?,
    returnDate: String?,
    departureDateMillis: Long?,
    returnDateMillis: Long?,
    onDatesUpdated: (departureDate: Long?, returnDate: Long?) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showDatePicker by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .clickable { showDatePicker = true }
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(32.dp)
            ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = departureDate ?: stringResource(R.string.trip_departure_date_label),
                intent = TextIntent.Body,
                textAlign = TextAlign.Center,
                color = if (departureDate != null) {
                    Color(0xFF000000)
                } else {
                    Color(0xFF9E9E9E)
                },
            )
            Icon(
                modifier = Modifier.size(14.dp),
                painter = painterResource(Icons.ArrowNext),
                contentDescription = "",
            )
            Text(
                modifier = Modifier.weight(1f),
                text = returnDate ?: stringResource(R.string.trip_return_date_label),
                intent = TextIntent.Body,
                textAlign = TextAlign.Center,
                color = if (returnDate != null) {
                    Color(0xFF000000)
                } else {
                    Color(0xFF9E9E9E)
                },
            )
        }
    }

    if (showDatePicker) {
        TripCreationDatePicker(
            initialSelectedStartDateMillis = departureDateMillis,
            initialSelectedEndDateMillis = returnDateMillis,
            onDismiss = { startDate, endDate ->
                onDatesUpdated(startDate, endDate)
                showDatePicker = false
            }
        )
    }
}

@Preview
@Composable
fun DateTextFieldPreview(modifier: Modifier = Modifier) {
    DateTextField(
        onDatesUpdated = { _, _ -> },
        departureDate = null,
        returnDate = null,
        departureDateMillis = null,
        returnDateMillis = null,
        modifier = Modifier.fillMaxWidth(),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TripCreationDatePicker(
    initialSelectedStartDateMillis: Long?,
    initialSelectedEndDateMillis: Long?,
    onDismiss: (selectedStartDateMillis: Long?, selectedEndDateMillis: Long?) -> Unit,
) {
    val datePickerState = rememberDateRangePickerState(
        selectableDates = AfterTodaySelectableDates(),
        initialSelectedStartDateMillis = initialSelectedStartDateMillis,
        initialSelectedEndDateMillis = initialSelectedEndDateMillis,
    )

    DatePickerDialog(
        onDismissRequest = {
            onDismiss(
                datePickerState.selectedStartDateMillis,
                datePickerState.selectedEndDateMillis
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onDismiss(
                        datePickerState.selectedStartDateMillis,
                        datePickerState.selectedEndDateMillis
                    )
                },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFF000000)
                )
            ) {
                Text(
                    text = stringResource(R.string.confirm_action),
                    intent = TextIntent.SmallDim,
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss(null, null) },
                colors = ButtonDefaults.textButtonColors(
                    contentColor = Color(0xFF000000)
                )
            ) {
                Text(
                    text = stringResource(R.string.cancel_action),
                    intent = TextIntent.SmallDim,
                )
            }
        },
        colors = DatePickerDefaults.colors(
            containerColor = Color(0xFFFFFFFF),
        )
    ) {
        DateRangePicker(
            title = {
                Text(
                    modifier = Modifier.padding(24.dp),
                    text = stringResource(R.string.trip_date_picker_title),
                    intent = TextIntent.H3
                )
            },
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                containerColor = Color(0xFFFFFFFF),
                selectedDayContainerColor = Color(0xFF000000),
                selectedDayContentColor = Color(0xFFFFFFFF),
                dayContentColor = Color(0xFF000000),
                todayContentColor = Color(0xFF000000),
                dayInSelectionRangeContentColor = Color(0xFF000000),
                dayInSelectionRangeContainerColor = Color(0xFFF2F2F2),
                todayDateBorderColor = Color(0xFF000000),
                selectedYearContainerColor = Color(0xFF000000),
                currentYearContentColor = Color(0xFF000000),
                dateTextFieldColors = TextFieldDefaults.colors(
                    cursorColor = Color(0xFF000000),
                    errorTextColor = Color(0xFFF90C0C),
                    unfocusedContainerColor = Color(0xFFFFFFFF),
                    focusedContainerColor = Color(0xFFFFFFFF),
                    errorLabelColor = Color(0xFFF90C0C),
                    errorCursorColor = Color(0xFFF90C0C),
                    errorIndicatorColor = Color(0xFFF90C0C),
                    focusedLabelColor = Color(0xFF000000),
                    focusedIndicatorColor = Color(0xFF000000)
                )
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private class AfterTodaySelectableDates : SelectableDates {

    val millisecondsInOneDay = 24 * 60 * 60 * 1000

    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        val today = System.currentTimeMillis() - millisecondsInOneDay
        return utcTimeMillis >= today
    }
}