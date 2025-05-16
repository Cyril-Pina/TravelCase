package com.cyriltheandroid.travelcase.designsystem.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.cyriltheandroid.travelcase.designsystem.badge.CountryBadge
import com.cyriltheandroid.travelcase.designsystem.badge.CountryIntent
import com.cyriltheandroid.travelcase.designsystem.badge.FileCountBadge
import com.cyriltheandroid.travelcase.designsystem.badge.TripStepBadge
import com.cyriltheandroid.travelcase.designsystem.badge.TripStepIntent
import com.cyriltheandroid.travelcase.designsystem.text.Text
import com.cyriltheandroid.travelcase.designsystem.text.TextIntent
import java.io.File

@Composable
fun TripCard(
    title: String,
    subtitle: String,
    countryIntent: CountryIntent?,
    fileCount: Int,
    bannerUri: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tripStepIntent: TripStepIntent? = null,
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(32.dp))
            .clickable(onClick = onClick)
            .background(color = Color(0xFFFFFFFF), RoundedCornerShape(32.dp)),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(248.dp)
                    .background(Color.DarkGray, RoundedCornerShape(32.dp))
                    .clip(RoundedCornerShape(24.dp)),
                contentAlignment = Alignment.BottomStart
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context = context)
                            .crossfade(true)
                            .data(File(bannerUri))
                            .build()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                TripCardBadges(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    countryIntent = countryIntent,
                    fileCount = fileCount,
                    tripStepIntent = tripStepIntent,
                )
            }
            Spacer(Modifier.size(8.dp))
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = title,
                intent = TextIntent.H3,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                text = subtitle,
                intent = TextIntent.SmallDim,
            )
            Spacer(Modifier.size(16.dp))
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TripCardBadges(
    countryIntent: CountryIntent?,
    fileCount: Int,
    modifier: Modifier = Modifier,
    tripStepIntent: TripStepIntent? = null,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        tripStepIntent?.let { intent -> TripStepBadge(intent = intent) }
        countryIntent?.let { intent -> CountryBadge(intent = intent) }
        FileCountBadge(count = fileCount)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TripCardPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFF2F2F2)),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TripCard(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            title = "Brésil",
            subtitle = "Du 16 Mars 2025 au 18 Mai 2025 . 8 jours",
            countryIntent = CountryIntent.SouthAfrica,
            fileCount = 4,
            onClick = {},
            bannerUri = "",
        )
        TripCard(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            title = "Brésil",
            subtitle = "Du 16 Mars 2025 au 18 Mai 2025 . 8 jours",
            countryIntent = CountryIntent.SouthAfrica,
            fileCount = 4,
            tripStepIntent = TripStepIntent.Incoming(5),
            onClick = {},
            bannerUri = "",
        )
        TripCard(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            title = "Brésil",
            subtitle = "Du 16 Mars 2025 au 18 Mai 2025 . 8 jours",
            countryIntent = CountryIntent.SouthAfrica,
            fileCount = 4,
            tripStepIntent = TripStepIntent.Finished,
            onClick = {},
            bannerUri = "",
        )
    }
}