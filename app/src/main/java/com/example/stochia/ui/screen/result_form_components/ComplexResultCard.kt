package com.example.stochia.ui.screen.result_form_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.NeutralLight
import com.example.stochia.ui.theme.PrimaryLightest
import com.example.stochia.ui.theme.Secondary
import com.example.stochia.ui.theme.Typography

@Composable
fun ComplexResultCard(
    title: String,
    stats: Map<String, String>,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(NeutralLight)
            .innerShadow(
                shape = RoundedCornerShape(5.dp),
                shadow = Shadow(
                    radius = 8.dp,
                    spread = 1.dp,
                    color = NeutralDarker
                ))
            .padding(10.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = NeutralLight),
            shape = RoundedCornerShape(5.dp),
            modifier = modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight()
        )
        {
            val groupedStats = stats
                .entries
                .groupBy { it.value }
                .mapValues { entry ->
                    entry.value.map { it.key } // lista de statNames
                }
            Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp)) {
                Text(
                    title,
                    style = Typography.headlineLarge,
                    textAlign = TextAlign.Start,
                    color = Secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                )
                groupedStats.forEach { (statName, stat) ->
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            buildAnnotatedString {
                                withStyle(SpanStyle(color = PrimaryLightest)) {
                                    append("\n-$statName: \n")
                                }
                                stat.forEach { stat ->
                                    withStyle(SpanStyle(color = Color.White)) {
                                        append(" $stat ")
                                    }
                                }

                            },
                            textAlign = TextAlign.Start,
                            color = PrimaryLightest,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 20.dp)
                        )
                    }
                }

            }
        }
    }
}