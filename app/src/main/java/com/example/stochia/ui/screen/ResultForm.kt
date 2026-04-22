package com.example.stochia.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.innerShadow
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.result.Result
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.ui.screen.result_form_components.ComplexResultCard
import com.example.stochia.ui.screen.result_form_components.SingleResultCard
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDarkest
import com.example.stochia.ui.theme.PrimaryLightest
import com.example.stochia.ui.theme.Typography

@Composable
fun ResultForm(
    result: Result?
) {
    val scrollState = rememberScrollState()

    when (result) {
        is DistributionResult -> {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(color = Neutral)
                    .fillMaxHeight(0.9f)
                    .innerShadow(
                        shape = RoundedCornerShape(5.dp),
                        shadow = Shadow(
                            radius = 10.dp,
                            spread = 2.dp,
                            color = NeutralDarkest
                        )
                    )
                    .verticalScroll(scrollState)
            )
            {
                Text(
                    "Distribución de probabilidades",
                    style = Typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    color = PrimaryLightest
                )
                Spacer(modifier = Modifier.weight(0.1f))
                SingleResultCard(
                    title = "Media: ",
                    stats = "%.2f".format(result.mean),
                    modifier = Modifier.weight(0.2f)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                SingleResultCard(
                    title = "Desviación estándar: ",
                    stats = "%.2f".format(result.stdDev),
                    modifier = Modifier.weight(0.2f)
                )
                Spacer(modifier = Modifier.weight(0.1f))
                ComplexResultCard(
                    title = "Valores de rango",
                    stats = mapOf(
                        "Valor mínimo" to result.min.toString(),
                        "Valor máxio" to result.max.toString(),
                        "Percentil 5" to result.min.toString(),
                        "Percentil 95" to result.max.toString()
                    ),
                    modifier = Modifier.weight(0.1f)
                )
                Spacer(modifier = Modifier.weight(0.05f))
                ComplexResultCard(
                    title = "Frecuencia de valores",
                    stats = result.frequencies,
                    modifier = Modifier.weight(0.1f)
                )
                Spacer(modifier = Modifier.weight(0.05f))
                ComplexResultCard(
                    title = "Probabilidad de valores",
                    stats = result.probabilities,
                    modifier = Modifier.weight(0.1f)
                )
                Spacer(modifier = Modifier.weight(0.05f))
            }
        }

        is MontecarloResult -> {
            Text(
                result.toString(),
                style = Typography.headlineLarge
            )
        }

        is MarkovResult -> {
            Text(
                result.toString(),
                style = Typography.headlineLarge
            )
        }

    }
}