package com.example.stochia.ui.screen.result_form_components.montecarlo_result_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.material3.rememberRangeSliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.ui.screen.result_form_components.SingleResultCard
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.PrimaryLight
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.Tertiary
import com.example.stochia.ui.theme.TertiaryLight
import com.example.stochia.ui.theme.Typography
import java.math.BigDecimal
import java.math.RoundingMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultinomialMontecarloResult(result: MontecarloResult, modifier: Modifier)
{
    Card(
        colors = CardDefaults.cardColors(containerColor = Neutral),
        modifier = Modifier
            .fillMaxWidth()
            .height(700.dp),
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
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .weight(0.5f)
            )
            {
                SingleResultCard(
                    title = "Media primer parámetro",
                    titleColor = SecondaryLight,
                    stats = BigDecimal(result.results!![0].mean!!).setScale(2, RoundingMode.HALF_UP)
                        .toString(),
                    statsColor = PrimaryLight,
                    modifier = modifier.weight(0.2f)
                )
                Spacer(modifier.weight(0.05f))
                SingleResultCard(
                    title = "Media segundo parámetro",
                    titleColor = SecondaryLight,
                    stats =  BigDecimal(result.results[1].mean!!).setScale(2, RoundingMode.HALF_UP)
                        .toString(),
                    statsColor = PrimaryLight,
                    modifier = modifier.weight(0.2f)
                )
                Spacer(modifier.weight(0.05f))
                SingleResultCard(
                    title = "Media tercer parámetro",
                    titleColor = SecondaryLight,
                    stats =  BigDecimal(result.results[2].mean!!).setScale(2, RoundingMode.HALF_UP)
                        .toString(),
                    statsColor = Tertiary,
                    modifier = modifier
                        .weight(0.2f)
                )

            }
            Spacer(modifier.weight(0.05f))
        }
    }
    Spacer(modifier.height(30.dp))
    Card(
        colors = CardDefaults.cardColors(containerColor = Neutral),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp)
    )
    {
        Column(modifier = Modifier.padding(16.dp))
        {
            Text(
                text = "Parametro",
                style = Typography.headlineLarge,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Spacer(modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)   // o el alto que quieras
            ) {
                itemsIndexed(result.results!!) { index, value ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Neutral),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        elevation = CardDefaults.cardElevation(6.dp)
                    )
                    {
                        Text(
                            text = "Parametro $index",
                            style = Typography.bodyLarge,
                            color = Color.White,
                            modifier = Modifier.weight(0.2f),
                        )
                        Spacer(modifier.weight(0.05f))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(0.75f)
                        )
                        {

                            SingleResultCard(
                                title = "",
                                titleColor = SecondaryLight,
                                stats = BigDecimal(value.stdDev!!).setScale(2, RoundingMode.HALF_UP).toString(),
                                statsColor = TertiaryLight,
                                modifier = modifier
                                    .weight(0.1f)
                            )
                            val rangeSliderState = rememberRangeSliderState(
                                activeRangeStart = 5f,
                                activeRangeEnd = 95f,
                                valueRange = -5f..105f
                            )
                            RangeSlider(
                                state = rangeSliderState,
                                enabled = false,
                                track = {
                                    GlowCustomStaticTrack()
                                },
                                startThumb = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {

                                        Text(
                                            text = "|",
                                            style = Typography.labelSmall,
                                            color = SecondaryLight
                                        )
                                        Text(
                                            text = "P5",
                                            style = Typography.labelSmall,
                                            color = SecondaryLight
                                        )
                                        Text(
                                            "%.2f".format(result.p5),
                                            color = TertiaryLight
                                        )
                                    }

                                },
                                endThumb = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Text(
                                            text = "|",
                                            style = Typography.labelSmall,
                                            color = SecondaryLight
                                        )
                                        Text(
                                            text = "P95",
                                            style = Typography.labelSmall,
                                            color = SecondaryLight
                                        )

                                        Text(
                                            "%.2f".format(result.p95),
                                            color = TertiaryLight
                                        )
                                    }
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }
    }
}