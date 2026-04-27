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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.PrimaryLightest
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.TertiaryLight
import com.example.stochia.ui.theme.Typography

@Composable
fun MarkovResultScreen(
    result: MarkovResult,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .background(NeutralDarker)
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Neutral),
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.65f)
            ) {
                Spacer(modifier.weight(0.05f))
                Text(
                    text = "Markov Summary",
                    style = Typography.headlineLarge,
                    color = Color.White,
                    modifier = Modifier.weight(0.1f).fillMaxWidth(0.9f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .weight(0.15f)
                )
                {
                    SingleResultCard(
                        title = "Start",
                        titleColor = SecondaryLight,
                        stats = result.path[0],
                        statsColor = PrimaryLightest,
                        modifier = modifier.weight(0.4f)
                    )
                    Spacer(modifier.weight(0.05f))
                    SingleResultCard(
                        title = "End",
                        titleColor = SecondaryLight,
                        stats = result.path.last(),
                        statsColor = PrimaryLightest,
                        modifier =modifier.weight(0.4f)
                    )
                    Spacer(modifier.weight(0.05f))
                    SingleResultCard(
                        title = "Steps",
                        titleColor = SecondaryLight,
                        stats = result.path.size.toString(),
                        statsColor = PrimaryLightest,
                        modifier =modifier.weight(0.4f)
                    )
                    Spacer(modifier.weight(0.05f))

                }
                val conv1 = "${result.conv[0].times(100)}%"
                val conv2 = "${result.conv[1].times(100)}%"
                val conv3 = "${result.conv[2].times(100)}%"
                Spacer(modifier.weight(0.05f))
                SingleResultCard(
                    title = "Convergencia",
                    titleColor = SecondaryLight,
                    stats = "A: $conv1 / B: $conv2 / C: $conv3",
                    statsColor = TertiaryLight,
                    modifier =modifier
                        .fillMaxWidth(0.9f)
                        .weight(0.25f)
                )
                Spacer(modifier.weight(0.05f))
            }
        }
        Spacer(modifier.height(50.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "State Path:",
                    style = MaterialTheme.typography.titleMedium
                )
                var lastState = result.path[0]
                Text("Initial State: $lastState")
                result.path.forEachIndexed { index, state ->
                    if (index != 0) {
                        val prob = getProb(lastState, state, result.probs)
                        Text(
                            text = "t=${index.toString().padStart(2, '0')} " +
                                    " $lastState → $state           P= $prob%",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        lastState = state
                    }
                }

                Spacer(Modifier.height(16.dp))

            }
        }
    }
}

fun getProb(state: String, nextState: String, matrix: Map<String, List<Double>>): String {
    var prob = ""
    when (state) {
        "A" -> {
            if (nextState == ("A")) prob = matrix.getValue(state)[0].times(100).toString()
            if (nextState == ("B")) prob = matrix.getValue(state)[1].times(100).toString()
            if (nextState == ("C")) prob = matrix.getValue(state)[2].times(100).toString()
        }

        "B" -> {
            if (nextState == ("A")) prob = matrix.getValue(state)[0].times(100).toString()
            if (nextState == ("B")) prob = matrix.getValue(state)[1].times(100).toString()
            if (nextState == ("C")) prob = matrix.getValue(state)[2].times(100).toString()
        }

        "C" -> {
            if (nextState == ("A")) prob = matrix.getValue(state)[0].times(100).toString()
            if (nextState == ("B")) prob = matrix.getValue(state)[1].times(100).toString()
            if (nextState == ("C")) prob = matrix.getValue(state)[2].times(100).toString()
        }
    }
    return prob
}