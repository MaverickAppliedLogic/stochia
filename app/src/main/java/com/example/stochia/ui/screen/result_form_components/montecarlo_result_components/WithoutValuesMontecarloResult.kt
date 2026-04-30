package com.example.stochia.ui.screen.result_form_components.montecarlo_result_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberRangeSliderState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.ui.screen.result_form_components.SingleResultCard
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.PrimaryLight
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.TertiaryLight
import com.example.stochia.ui.theme.Typography
import java.math.BigDecimal
import java.math.RoundingMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WithoutValuesMontecarloResult(result: MontecarloResult, modifier: Modifier)
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
                    .weight(0.13f)
            )
            Spacer(modifier.weight(0.05f))
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .weight(0.15f)
            )
            {
                SingleResultCard(
                    title = "Media",
                    titleColor = SecondaryLight,
                    stats = BigDecimal(result.mean!!).setScale(2, RoundingMode.HALF_UP).toString(),
                    statsColor = PrimaryLight,
                    modifier = modifier.weight(0.4f)
                )
                Spacer(modifier.weight(0.05f))
                SingleResultCard(
                    title = "Standard Dev",
                    titleColor = SecondaryLight,
                    stats = BigDecimal(result.stdDev!!).setScale(2, RoundingMode.HALF_UP)
                        .toString(),
                    statsColor = PrimaryLight,
                    modifier = modifier.weight(0.4f)
                )

            }

            Spacer(modifier.weight(0.05f))
            SingleResultCard(
                title = "Percentiles (5% - 95%)",
                titleColor = SecondaryLight,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .weight(0.25f)

            ) {
                val rangeSliderState = rememberRangeSliderState(
                    activeRangeStart = 5f,
                    activeRangeEnd = 95f,
                    valueRange = -5f..105f
                )
                Box(modifier = Modifier.weight(1f)){
                    val rangeSliderState2 = rememberRangeSliderState(
                        activeRangeStart = 50f,
                        activeRangeEnd = 95f,
                        valueRange = 0f..100f
                    )
                    if (result.p50 != null){
                        RangeSlider(
                            state = rangeSliderState2,
                            enabled = false,
                            colors = SliderDefaults.colors(
                                activeTrackColor = Color.Transparent,
                                activeTickColor = Color.Transparent,
                                inactiveTrackColor = Color.Transparent,
                                inactiveTickColor = Color.Transparent,
                                thumbColor = Color.Transparent,
                                disabledActiveTrackColor = Color.Transparent,
                                disabledInactiveTrackColor = Color.Transparent,
                                disabledActiveTickColor = Color.Transparent,
                                disabledInactiveTickColor = Color.Transparent,
                                disabledThumbColor = Color.Transparent,
                            ),
                            track = {
                                GlowCustomStaticTrack(modifier.alpha(0f))
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
                                        text = "P50",
                                        style = Typography.labelSmall,
                                        color = SecondaryLight
                                    )
                                    Text(
                                        "%.2f".format(result.p50),
                                        color = TertiaryLight
                                    )
                                }

                            },
                            endThumb = {

                            },
                        )
                    }
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
                    )

                }

            }
            Spacer(modifier.weight(0.05f))
        }
    }
}