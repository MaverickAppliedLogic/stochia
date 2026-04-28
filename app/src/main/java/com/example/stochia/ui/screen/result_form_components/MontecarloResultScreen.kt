package com.example.stochia.ui.screen.result_form_components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.ui.screen.result_form_components.montecarlo_result_components.GlowCustomStaticTrack
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.PrimaryLight
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.Tertiary
import com.example.stochia.ui.theme.TertiaryLight
import com.example.stochia.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MontecarloResultScreen(
    result: MontecarloResult,
    scrollState: ScrollState,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(NeutralDarker)
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    )
    {
        Card(
            colors = CardDefaults.cardColors(containerColor = Neutral),
            modifier = Modifier
                .fillMaxWidth()
                .height(700.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
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
                SingleResultCard(
                    title = "Media",
                    titleColor = SecondaryLight,
                    stats = "%.2f".format(result.mean),
                    statsColor = Tertiary,
                    modifier = modifier
                        .fillMaxWidth(0.9f)
                        .weight(0.15f)
                )
                Spacer(modifier.weight(0.05f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .weight(0.15f)
                )
                {
                    SingleResultCard(
                        title = "Standard Dev",
                        titleColor = SecondaryLight,
                        stats = "%.2f".format(result.stdDev),
                        statsColor = PrimaryLight,
                        modifier = modifier.weight(0.4f)
                    )
                    Spacer(modifier.weight(0.05f))
                    SingleResultCard(
                        title = "Simulations",
                        titleColor = SecondaryLight,
                        stats = "%d".format(result.values?.size ?: result.tries),
                        statsColor = PrimaryLight,
                        modifier = modifier.weight(0.4f)
                    )
                }

                Spacer(modifier.weight(0.05f))
                SingleResultCard(
                    title = "Percentiles (5% - 95%)",
                    titleColor = SecondaryLight,
                    modifier= Modifier
                        .fillMaxWidth(0.9f)
                        .weight(0.25f)

                ){
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

                                Text(text = "|",
                                    style = Typography.labelSmall,
                                    color = SecondaryLight)
                                Text(text = "P5",
                                    style = Typography.labelSmall,
                                    color = SecondaryLight)
                                Text("%.2f".format( result.p5),
                                    color = TertiaryLight) }

                        },
                        endThumb = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(text = "|",
                                    style = Typography.labelSmall,
                                    color = SecondaryLight)
                                Text(text = "P95",
                                    style = Typography.labelSmall,
                                    color = SecondaryLight)

                                Text("%.2f".format(result.p95),
                                    color = TertiaryLight) }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier.weight(0.05f))
            }
        }
        Spacer(modifier.height(30.dp))
        Card(
            colors = CardDefaults.cardColors(containerColor = Neutral),
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Simulations",
                    style = Typography.headlineLarge,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
                Spacer(modifier.height(16.dp))
                SingleResultCard(
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                ) {
                    result.values?.forEachIndexed { index, value ->
                        SingleResultCard(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = buildAnnotatedString {
                                        withStyle(SpanStyle(color = SecondaryLight)) {
                                            append("Sim $index")
                                        }
                                        append(":    $value")
                                    },
                                    style = Typography.labelSmall
                                )
                                Spacer(modifier.weight(1f))
                            }
                        }
                        Spacer(modifier.height(16.dp))
                    }

                    Spacer(Modifier.height(16.dp))

                }
            }
        }
    }
}