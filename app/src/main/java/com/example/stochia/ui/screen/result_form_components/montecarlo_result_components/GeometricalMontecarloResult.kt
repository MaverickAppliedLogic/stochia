package com.example.stochia.ui.screen.result_form_components.montecarlo_result_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.ui.screen.result_form_components.SingleResultCard
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.TertiaryLight
import com.example.stochia.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeometricalMontecarloResult(result: MontecarloResult, modifier: Modifier)
{
    Card(
        colors = CardDefaults.cardColors(containerColor = Neutral),
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.65f)
        )
        {
            Spacer(modifier.weight(0.05f))
            Text(
                text = "Montecarlo Stats",
                style = Typography.headlineLarge,
                color = Color.White,
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxWidth(0.9f)
            )
            SingleResultCard(
                title = "Distribution",
                titleColor = SecondaryLight,
                stats = result.distribution?.name,
                statsColor = TertiaryLight,
                modifier = modifier
                    .fillMaxWidth(0.9f)
                    .weight(0.1f)
            )
            Spacer(modifier.weight(0.05f))
            SingleResultCard(
                title = "Intentos hasta éxito",
                titleColor = SecondaryLight,
                stats = result.tries.toString(),
                statsColor = TertiaryLight,
                modifier = modifier
                    .fillMaxWidth(0.9f)
                    .weight(0.1f)
            )
            Spacer(modifier.weight(0.05f))
        }
    }
}