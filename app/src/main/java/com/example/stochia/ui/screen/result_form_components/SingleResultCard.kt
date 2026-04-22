package com.example.stochia.ui.screen.result_form_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.NeutralLight
import com.example.stochia.ui.theme.PrimaryLightest
import com.example.stochia.ui.theme.Typography

@Composable
fun SingleResultCard(
    title: String,
    stats: String,
    modifier: Modifier
) {
    Card(
    colors = CardDefaults.cardColors(containerColor = NeutralLight),
    modifier = modifier.fillMaxWidth(0.7f)
    ) {
        Text(
            title,
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center,
            maxLines = 2,
            color = PrimaryLightest,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            stats,
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = PrimaryLightest,
            modifier = Modifier.fillMaxWidth()
        )
    }
}