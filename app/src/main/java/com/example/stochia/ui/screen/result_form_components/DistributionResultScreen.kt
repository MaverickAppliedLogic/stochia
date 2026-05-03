package com.example.stochia.ui.screen.result_form_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.ui.theme.LocalDimens
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.Primary
import com.example.stochia.ui.theme.PrimaryLightest
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainScreenEvent
import java.math.BigDecimal
import java.math.RoundingMode

@Composable
fun DistributionResultScreen(
    result: DistributionResult,
    isResultNew: Boolean,
    onEvent: (MainScreenEvent) -> Unit,
    modifier: Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = Neutral)
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
    )
    {
        Text(
            "Distribución de probabilidades",
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = PrimaryLightest
        )
        Spacer(modifier = Modifier.height(50.dp))
        SingleResultCard(
            title = "Media: ",
            stats = "%.2f".format(result.mean),
            modifier = Modifier.height(80.dp).fillMaxWidth(0.7f)
        )
        Spacer(modifier = Modifier.height(20.dp))
        SingleResultCard(
            title = "Desviación estándar: ",
            stats = "%.2f".format(result.stdDev),
            modifier = Modifier.height(80.dp).fillMaxWidth(0.7f)
        )
        Spacer(modifier = Modifier.height(40.dp))
        ComplexResultCard(
            title = "Valores de rango",
            stats = mapOf(
                result.min.toString() to "Valor mínimo",
                result.max.toString() to "Valor máximo",
                result.p5.toString() to "Percentil 5",
                result.p95.toString() to "Percentil 95"
            ).toList()
                .sortedBy { it.first.toDouble() }
                .toMap(),
            modifier = Modifier.padding(bottom = 50.dp)
        )
        Spacer(modifier = Modifier)
        Row(modifier = Modifier) {
            ComplexResultCard(
                title = "Frecuencia de valores",
                stats = result.frequencies
                    .toList() // convierte a lista de Pair(prob, lista)
                    .sortedByDescending { it.second.toDouble() }
                    .toMap()
                    .mapValues { "${it.value} veces" },
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        ComplexResultCard(
            title = "Probabilidad de valores",
            stats = result
                .probabilities
                .map {
                    it.key to
                            BigDecimal(it.value)
                                .setScale(2, RoundingMode.HALF_UP).toDouble()
                }
                .toList()
                .sortedByDescending { it.second }
                .toMap()
                .mapValues { "${it.value}%" },
            modifier = Modifier
        )
        if(isResultNew){
            Spacer(modifier.height(30.dp))
            Card(
                shape = RoundedCornerShape(5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Primary,
                    contentColor = SecondaryLight,
                    disabledContainerColor = Primary,
                    disabledContentColor = SecondaryLight
                ),
                modifier = Modifier
                    .fillMaxWidth(0.60f)
                    .height(LocalDimens.current.commitButton),
                onClick = {
                    onEvent(MainScreenEvent.SaveStudyButtonClicked)
                }
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "GUARDAR ESTUDIO",
                    textAlign = TextAlign.Center,
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                )
                Spacer(modifier = Modifier.weight(1f))

            }
            Spacer(modifier.height(30.dp))
        }
    }
    Spacer(modifier = Modifier)
}
