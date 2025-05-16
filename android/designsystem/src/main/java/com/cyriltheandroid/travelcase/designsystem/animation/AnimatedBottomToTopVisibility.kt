package com.cyriltheandroid.travelcase.designsystem.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun AnimatedBottomToTopVisibility(
    visible: Boolean,
    modifier: Modifier = Modifier,
    withFadeAnimation: Boolean = true,
    label: String = "AnimatedVisibility",
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    val enterTransition = if (withFadeAnimation) {
        fadeIn() + expandVertically(expandFrom = Alignment.Bottom)
    } else {
        expandVertically(expandFrom = Alignment.Bottom)
    }

    val exitTransition = if (withFadeAnimation) {
        fadeOut() + shrinkVertically(shrinkTowards = Alignment.Top)
    } else {
        shrinkVertically(shrinkTowards = Alignment.Top)
    }

    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = enterTransition,
        exit = exitTransition,
        label = label,
        content = content,
    )
}