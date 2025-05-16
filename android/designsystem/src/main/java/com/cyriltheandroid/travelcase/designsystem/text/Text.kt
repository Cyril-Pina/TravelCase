package com.cyriltheandroid.travelcase.designsystem.text

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.cyriltheandroid.travelcase.android.designsystem.R

@Composable
fun Text(
    text: String,
    intent: TextIntent,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    color: Color? = null,
) {
    val fontSize = intent.value.fontSize
    val fontWeight = intent.value.fontWeight
    val textColor: Color = color ?: if (intent == TextIntent.SmallDim) {
        Color(0xFF9E9E9E)
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    androidx.compose.material3.Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        color = textColor,
        textAlign = textAlign,
        overflow = overflow,
        maxLines = maxLines
    )
}

enum class TextIntent(val value: TextIntentValue) {
    H1(value = TextIntentValue.H1()),
    H2(value = TextIntentValue.H2()),
    H3(value = TextIntentValue.H3()),
    Body(value = TextIntentValue.Body()),
    BodyBold(value = TextIntentValue.BodyBold()),
    Small(value = TextIntentValue.Small()),
    SmallBold(value = TextIntentValue.SmallBold()),
    SmallDim(value = TextIntentValue.SmallDim());
}

sealed class TextIntentValue {
    abstract val fontSize: TextUnit
    abstract val fontWeight: FontWeight

    data class H1(
        override val fontSize: TextUnit = 32.sp,
        override val fontWeight: FontWeight = FontWeight.Bold,
    ) : TextIntentValue()

    data class H2(
        override val fontSize: TextUnit = 24.sp,
        override val fontWeight: FontWeight = FontWeight.SemiBold
    ) : TextIntentValue()

    data class H3(
        override val fontSize: TextUnit = 20.sp,
        override val fontWeight: FontWeight = FontWeight.SemiBold
    ) : TextIntentValue()

    data class Body(
        override val fontSize: TextUnit = 14.sp,
        override val fontWeight: FontWeight = FontWeight.Normal
    ) : TextIntentValue()

    data class BodyBold(
        override val fontSize: TextUnit = 14.sp,
        override val fontWeight: FontWeight = FontWeight.Bold
    ) : TextIntentValue()

    data class Small(
        override val fontSize: TextUnit = 12.sp,
        override val fontWeight: FontWeight = FontWeight.SemiBold,
    ) : TextIntentValue()

    data class SmallBold(
        override val fontSize: TextUnit = 12.sp,
        override val fontWeight: FontWeight = FontWeight.Black
    ) : TextIntentValue()

    data class SmallDim(
        override val fontSize: TextUnit = 12.sp,
        override val fontWeight: FontWeight = FontWeight.SemiBold
    ) : TextIntentValue()
}