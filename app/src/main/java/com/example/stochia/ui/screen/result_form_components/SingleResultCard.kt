package com.example.stochia.ui.screen.result_form_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralLight
import com.example.stochia.ui.theme.Tertiary
import com.example.stochia.ui.theme.Typography

@Composable
fun SingleResultCard(
    title: String? = null,
    titleColor: Color = Tertiary,
    stats: String? = null,
    statsColor: Color = Color.White,
    modifier: Modifier,
    content: @Composable () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(NeutralLight)
            .innerShadow(
                shape = RoundedCornerShape(5.dp),
                shadow = Shadow(
                    radius = 8.dp,
                    spread = 1.dp,
                    color = Neutral
                ))
            .padding(10.dp)
    )
    {
        Card(
            colors = CardDefaults.cardColors(containerColor = NeutralLight),
            modifier = modifier.fillMaxWidth(0.7f)
        ) {
            if (title != null){
                Text(
                    title,
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    color = titleColor,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                )
            }
            if (stats != null){
                Text(
                    stats,
                    style = Typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = statsColor,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
            }
            content()
        }
    }
}