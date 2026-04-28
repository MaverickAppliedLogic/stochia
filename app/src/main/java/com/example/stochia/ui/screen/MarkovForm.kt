package com.example.stochia.ui.screen



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.ui.screen.common_components.CustomEditText
import com.example.stochia.ui.screen.markov_form_components.CustomSlider.CustomSlider
import com.example.stochia.ui.screen.markov_form_components.TransitionMatrix
import com.example.stochia.ui.theme.LocalDimens
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.theme.Primary
import com.example.stochia.ui.theme.PrimaryLightest
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainScreenEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkovForm(
    params: MarkovParams,
    onEvent: (MainScreenEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralDarker)
    ) {
        Text(
            "Cadenas de Markov",
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = PrimaryLightest
        )
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(color = Neutral)
                .fillMaxSize(0.9f)
        ) {
            Spacer(modifier = Modifier.weight(0.15f))
            Text(
                "ESTADO INICIAL",
                color = SecondaryLight,
                textAlign = TextAlign.Start,
                style = Typography.bodyLarge,
            )
            Spacer(modifier = Modifier.weight(0.05f))
            CustomEditText(
                value = when(params.init_state){
                    0 -> "A"
                    1-> "B"
                    else -> "C"
                },
                type = KeyboardType.Text,
                modifier = Modifier.weight(0.2f).fillMaxWidth(0.6f)
                ) {
                if (it.isNotBlank()) {
                    when (it) {
                        "A" -> onEvent(MainScreenEvent.ChangeMarkovInitState(0))
                        "B" -> onEvent(MainScreenEvent.ChangeMarkovInitState(1))
                        "C" -> onEvent(MainScreenEvent.ChangeMarkovInitState(2))
                    }
                }
            }
            Spacer(modifier = Modifier.weight(0.15f))
            Text(
                "MATRIZ DE TRANSICIÓN",
                color = SecondaryLight,
                textAlign = TextAlign.Start,
                style = Typography.bodyLarge,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(0.05f))
            TransitionMatrix(
                states = params.states,
                probs = params.probs,
               modifier = Modifier,
               onEvent = {onEvent(MainScreenEvent.ChangeMarkovProbs(it))}
            )
            Spacer(modifier = Modifier.weight(0.15f))
            Text(
                "PASOS: ${params.steps}",
                color = SecondaryLight,
                textAlign = TextAlign.Start,
                style = Typography.bodyLarge,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.weight(0.1f))
            CustomSlider(
                value = params.steps.toFloat(),
                valueRange = 0f..50f,
                steps = 50,
                onValueChange = {
                    onEvent(MainScreenEvent.ChangeMarkovSteps(it.toInt()))
                }
            )
            Spacer(modifier = Modifier.weight(0.2f))
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
                    onEvent(MainScreenEvent.SimulateMarkovButtonClicked)
                }
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "SIMULAR",
                    textAlign = TextAlign.Center,
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                )
                Spacer(modifier = Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.weight(0.15f))
        }

    }
}