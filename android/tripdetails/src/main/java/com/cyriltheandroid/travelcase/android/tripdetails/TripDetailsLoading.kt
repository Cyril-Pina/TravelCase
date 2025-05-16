package com.cyriltheandroid.travelcase.android.tripdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Preview
@Composable
fun TripDetailsFolderLazyColumnLoading(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        for (i in 1..4) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .shimmer()
                    .background(Color.LightGray, shape = RoundedCornerShape(36.dp)),
            )
        }
    }
}

@Composable
fun TripSearchBarLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .shimmer()
            .background(Color.LightGray, shape = RoundedCornerShape(36.dp)),
    )
}

@Preview
@Composable
fun TripDetailsHeaderLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(76.dp)
            .shimmer()
            .background(Color.LightGray, shape = RoundedCornerShape(36.dp)),
    )
}