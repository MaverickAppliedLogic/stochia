package com.example.stochia.ui.screen.montecarlo_form_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.ui.screen.common_components.CustomDropdownMenu
import com.example.stochia.ui.screen.montecarlo_form_components.montecarlo_formfields_components.OneParamFields
import com.example.stochia.ui.screen.montecarlo_form_components.montecarlo_formfields_components.OneParamFieldsWithSize
import com.example.stochia.ui.screen.montecarlo_form_components.montecarlo_formfields_components.ThreeParamsFieldsWithSize
import com.example.stochia.ui.screen.montecarlo_form_components.montecarlo_formfields_components.TwoParamsFieldsWithSize
import com.example.stochia.ui.theme.LocalDimens
import com.example.stochia.ui.theme.Neutral
import com.example.stochia.ui.theme.Primary
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.Typography
import com.example.stochia.ui.viewmodel.MainScreenEvent

enum class DistributionType(val label: String) {
    NORMAL("Normal"),
    UNIFORM("Uniform"),
    BETA("Beta"),
    BINOMIAL("Binomial"),
    EXPONENTIAL("Exponential"),
    POISSON("Poisson"),
    GEOMETRICAL("Geométrica"),
    MULTINOMIAL("Multinomial")
}

@Composable
fun MontecarloFormFields(
    allParams: MontecarloParams,
    modifier: Modifier = Modifier,
    onEvent: (MainScreenEvent) -> Unit
) {
    val distribution = allParams.distribution
    val params = allParams.params
    val size = allParams.size
    val dimens = LocalDimens.current
    val spacerModifier = Modifier.height(dimens.spacerXSmall)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(dimens.cornerRadiusMedium))
            .background(color = Neutral)
            .fillMaxWidth(0.9f)
            .padding(vertical = dimens.spacerMedium)
    ) {
        Text(
            "TIPO DE DISTRIBUCION",
            color = SecondaryLight,
            textAlign = TextAlign.Start,
            style = Typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(0.65f)
        )
        Spacer(modifier = Modifier.height(dimens.spacerXSmall))
        CustomDropdownMenu(
            selected = distribution,
            options = DistributionType.entries.map { it.name },
            onClick = { onEvent(it) }
        )
        Spacer(modifier = Modifier.height(dimens.spacerMedium))

        when (DistributionType.valueOf(distribution)) {
            DistributionType.NORMAL,
            DistributionType.UNIFORM,
            DistributionType.BETA,
            DistributionType.BINOMIAL -> {
                TwoParamsFieldsWithSize(
                    params = params.toMutableList(),
                    size = size,
                    onParamsChange = { onEvent(MainScreenEvent.ChangeMontecarloParams(it)) },
                    onSizeChange = { onEvent(MainScreenEvent.ChangeMontecarloSize(it)) },
                    modifier = spacerModifier
                )
            }

            DistributionType.EXPONENTIAL,
            DistributionType.POISSON -> {
                OneParamFieldsWithSize(
                    params = params.toMutableList(),
                    size = size,
                    onParamsChange = { onEvent(MainScreenEvent.ChangeMontecarloParams(it)) },
                    onSizeChange = { onEvent(MainScreenEvent.ChangeMontecarloSize(it)) },
                    modifier = spacerModifier
                )
            }

            DistributionType.GEOMETRICAL -> {
                OneParamFields(
                    params = params.toMutableList(),
                    onParamsChange = { onEvent(MainScreenEvent.ChangeMontecarloParams(it)) },
                    modifier = spacerModifier
                )
            }

            DistributionType.MULTINOMIAL -> {
                ThreeParamsFieldsWithSize(
                    params = params.toMutableList(),
                    size = size,
                    onParamsChange = { onEvent(MainScreenEvent.ChangeMontecarloParams(it)) },
                    onSizeChange = { onEvent(MainScreenEvent.ChangeMontecarloSize(it)) },
                    modifier = spacerModifier
                )
            }
        }

        Spacer(modifier = Modifier.height(dimens.spacerLarge))
        Card(
            shape = RoundedCornerShape(dimens.cornerRadiusSmall),
            colors = CardDefaults.cardColors(
                containerColor = Primary,
                contentColor = SecondaryLight
            ),
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .height(dimens.commitButton),
            onClick = {
                onEvent(
                    MainScreenEvent.SimulateMontecarloButtonClicked(
                        MontecarloParams(
                            distribution = distribution,
                            params = params,
                            size = size
                        )
                    )
                )
            }
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
        Spacer(modifier = Modifier.height(dimens.spacerSmall))
    }
}
