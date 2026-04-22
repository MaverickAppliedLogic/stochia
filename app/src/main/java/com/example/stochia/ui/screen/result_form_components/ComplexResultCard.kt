package com.example.stochia.ui.screen.result_form_components

import androidx.compose.foundation.layout.Row
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
fun ComplexResultCard(
    title: String,
    stats: Map<String, String>,
    modifier: Modifier
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = NeutralLight),
        modifier = modifier.fillMaxWidth(0.7f)
    ) {
        Text(
            title,
            style = Typography.headlineLarge,
            textAlign = TextAlign.Start,
            color = PrimaryLightest,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
        )
        stats.forEach { (statName, stat) ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "$statName: $stat",
                    style = Typography.headlineLarge,
                    textAlign = TextAlign.Start,
                    color = PrimaryLightest,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                )
            }
        }

    }
}