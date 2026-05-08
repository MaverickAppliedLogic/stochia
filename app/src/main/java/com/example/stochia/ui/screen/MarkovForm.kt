package com.example.stochia.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
    markovParams: MarkovParams,
    onEvent: (MainScreenEvent) -> Unit
) {
    val dimens = LocalDimens.current
    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(NeutralDarker)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(dimens.spacerLarge))
        Text(
            "Cadenas de Markov",
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = PrimaryLightest
        )
        Spacer(modifier = Modifier.height(dimens.spacerMedium))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(dimens.cornerRadiusMedium))
                .background(color = Neutral)
                .fillMaxWidth(0.9f)
                .padding(vertical = dimens.spacerMedium, horizontal = dimens.cardInnerPadding)
        ) {
            Text(
                "ESTADO INICIAL",
                color = SecondaryLight,
                textAlign = TextAlign.Start,
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(dimens.spacerXSmall))
            CustomEditText(
                value = when (markovParams.initState) {
                    0 -> "A"
                    1 -> "B"
                    else -> "C"
                },
                type = KeyboardType.Text,
                modifier = Modifier
                    .height(dimens.editTextHeight)
                    .fillMaxWidth(0.6f)
            ) {
                if (it.isNotBlank()) {
                    when (it) {
                        "A" -> onEvent(MainScreenEvent.ChangeMarkovInitState(0))
                        "B" -> onEvent(MainScreenEvent.ChangeMarkovInitState(1))
                        "C" -> onEvent(MainScreenEvent.ChangeMarkovInitState(2))
                    }
                }
            }
            Spacer(modifier = Modifier.height(dimens.spacerMedium))
            Text(
                "MATRIZ DE TRANSICIÓN",
                color = SecondaryLight,
                textAlign = TextAlign.Start,
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(dimens.spacerXSmall))
            TransitionMatrix(
                states = markovParams.states,
                probs = markovParams.probs,
                modifier = Modifier,
                onEvent = { onEvent(MainScreenEvent.ChangeMarkovProbs(it)) }
            )
            Spacer(modifier = Modifier.height(dimens.spacerMedium))
            Text(
                "PASOS: ${markovParams.steps}",
                color = SecondaryLight,
                textAlign = TextAlign.Start,
                style = Typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(dimens.spacerSmall))
            CustomSlider(
                alignment = Alignment.CenterStart,
                value = markovParams.steps.toFloat(),
                valueRange = 0f..10000f,
                steps = 10000,
                onValueChange = { onEvent(MainScreenEvent.ChangeMarkovSteps(it.toInt())) }
            )
            Spacer(modifier = Modifier.height(dimens.spacerLarge))
            Card(
                shape = RoundedCornerShape(dimens.cornerRadiusSmall),
                colors = CardDefaults.cardColors(
                    containerColor = Primary,
                    contentColor = SecondaryLight,
                    disabledContainerColor = Primary,
                    disabledContentColor = SecondaryLight
                ),
                modifier = Modifier
                    .fillMaxWidth(0.60f)
                    .height(dimens.commitButton),
                onClick = { onEvent(MainScreenEvent.SimulateMarkovButtonClicked) }
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    "SIMULAR",
                    textAlign = TextAlign.Center,
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(dimens.spacerMedium))
        }
        Spacer(modifier = Modifier.height(dimens.spacerLarge))
    }
}
