package com.cyriltheandroid.travelcase.android.tripcreation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.cyriltheandroid.travelcase.core.model.country.Country
import com.cyriltheandroid.travelcase.designsystem.animation.AnimatedFadeVisibility
import com.cyriltheandroid.travelcase.designsystem.icon.Icons
import com.cyriltheandroid.travelcase.designsystem.textfield.DateTextField
import com.cyriltheandroid.travelcase.designsystem.textfield.DropDownTextField
import com.cyriltheandroid.travelcase.designsystem.textfield.TextField

@Composable
fun TripCreationContent(
    queriedCountry: String,
    countries: List<Country>,
    tripTitle: String,
    departureDate: String?,
    returnDate: String?,
    departureDateMillis: Long?,
    returnDateMillis: Long?,
    onDropDownValueChange: (String) -> Unit,
    onTextFieldValueChange: (String) -> Unit,
    onCountrySelected: (Int) -> Unit,
    onUpdateDates: (Long?, Long?) -> Unit,
    modifier: Modifier = Modifier,
    showTitleTextField: Boolean = true,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DropDownTextField(
            value = queriedCountry,
            onValueChange = onDropDownValueChange,
            onItemClick = { index, _ ->
                onCountrySelected(index)
            },
            leadingIcon = painterResource(Icons.Globe),
            items = countries.map { "${it.flag} ${it.value}" },
            label = stringResource(R.string.country_selector_label),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            )
        )

        AnimatedFadeVisibility(showTitleTextField) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = tripTitle,
                    onValueChange = onTextFieldValueChange,
                    label = stringResource(R.string.trip_title_label),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Text
                    )
                )

                DateTextField(
                    modifier = Modifier.fillMaxWidth(),
                    departureDate = departureDate,
                    returnDate = returnDate,
                    departureDateMillis = departureDateMillis,
                    returnDateMillis = returnDateMillis,
                    onDatesUpdated = onUpdateDates
                )
            }
        }
    }
}