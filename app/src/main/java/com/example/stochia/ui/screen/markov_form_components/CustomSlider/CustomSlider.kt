package com.example.stochia.ui.screen.markov_form_components.CustomSlider

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSlider(
    alignment: Alignment,
    value: Float,
    steps: Int = 0,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit
) {
    Slider(
    value = value,
    onValueChange = { onValueChange(it) },
    valueRange = valueRange,
    steps = steps,
    track = { slider ->
        CustomTrack(
            alignment = alignment,
            sliderValue = slider.value,
            valueRange = valueRange,
            modifier = Modifier
        )
    },
    modifier = Modifier.padding(
    top = 0.dp,
    bottom = 10.dp, start = 10.dp, end = 10.dp
    ).height(10.dp)
    )
}