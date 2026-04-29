package com.example.stochia.ui.screen.result_form_components.montecarlo_result_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp

@Composable
fun GlowCustomStaticTrack(
    modifier: Modifier = Modifier,
    activeWidth: Float = 0.9f
) {

    CustomStaticTrack(
        activeWidth = activeWidth,
        alignment = Alignment.TopCenter,
        modifier = modifier
    )
    CustomStaticTrack(
        activeWidth = activeWidth,
        alignment = Alignment.TopCenter,
        modifier = modifier.blur(5.dp)
    )
}