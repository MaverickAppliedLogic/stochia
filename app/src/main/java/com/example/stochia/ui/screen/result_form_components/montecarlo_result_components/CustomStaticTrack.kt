package com.example.stochia.ui.screen.result_form_components.montecarlo_result_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.Primary
import com.example.stochia.ui.theme.Secondary

@Composable
fun CustomStaticTrack(
    activeWidth: Float,
    alignment: Alignment,
    modifier: Modifier
) {
    Box(
    contentAlignment = alignment,
    modifier = modifier
    .fillMaxWidth()
    .fillMaxSize()
    )
    {
        Box(
            modifier = Modifier
                .height(14.dp)
                .padding(top=10.dp,start = 5.dp, end = 5.dp)
                .fillMaxSize()
                .background(Secondary)
        )
        Box(
            modifier = Modifier
                .height(14.dp)
                .padding(top=10.dp, start = 5.dp, end = 5.dp)
                .fillMaxWidth(activeWidth.minus(0.05f))
                .background(Primary),
        )

    }
}