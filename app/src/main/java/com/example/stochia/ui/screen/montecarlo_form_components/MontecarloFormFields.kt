package com.example.stochia.ui.screen.montecarlo_form_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.ui.screen.common_components.CustomDropdownMenu
import com.example.stochia.ui.screen.common_components.CustomEditText
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

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = Neutral)
            .fillMaxWidth(0.9f)
    ) {
        Spacer(modifier = Modifier.weight(0.5f))

        Text(
            "TIPO DE DISTRIBUCION",
            color = SecondaryLight,
            textAlign = TextAlign.Start,
            style = Typography.bodyLarge,
            modifier = Modifier.fillMaxWidth(0.65f)
        )

        Spacer(modifier = Modifier.weight(0.05f))

        CustomDropdownMenu(
            selected = distribution,
            options = DistributionType.entries.map { it.name },
            onClick = {
                onEvent(it)
            }
        )

        Spacer(modifier = Modifier.weight(0.5f))

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
                    modifier = Modifier.weight(0.2f)
                )
            }

            DistributionType.EXPONENTIAL,
            DistributionType.POISSON -> {
                OneParamFieldsWithSize(
                    params = params.toMutableList(),
                    size = size,
                    onParamsChange = { onEvent(MainScreenEvent.ChangeMontecarloParams(it)) },
                    onSizeChange = { onEvent(MainScreenEvent.ChangeMontecarloSize(it)) },
                    modifier = Modifier.weight(0.2f)
                )
            }

            DistributionType.GEOMETRICAL -> {
                OneParamFields(
                    params = params.toMutableList(),
                    onParamsChange = { onEvent(MainScreenEvent.ChangeMontecarloParams(it)) },
                    modifier = Modifier.weight(0.2f)
                )
            }

            DistributionType.MULTINOMIAL -> {
                ThreeParamsFieldsWithSize(
                    params = params.toMutableList(),
                    size = size,
                    onParamsChange = { onEvent(MainScreenEvent.ChangeMontecarloParams(it)) },
                    onSizeChange = { onEvent(MainScreenEvent.ChangeMontecarloSize(it)) },
                    modifier = Modifier.weight(0.2f)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Card(
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Primary,
                contentColor = SecondaryLight
            ),
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .height(LocalDimens.current.commitButton),
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
                    .fillMaxWidth(),
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.weight(0.3f))
    }
}


@Composable
fun ThreeParamsFieldsWithSize(
    params: MutableList<Double>,
    size: Int,
    onParamsChange: (List<Double>) -> Unit,
    onSizeChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    //TODO Binomial debe ser P1 Int, P2 0 < P2 < 1

    Text(
        "PARAMETRO 1",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText(
        value = params[0].toString(),
        onValueChange = {
            params[0] = it.toDoubleOrNull() ?: 0.0
            onParamsChange(params.toList())
        },
        modifier = Modifier.height(LocalDimens.current.editTextHeight)
    )
    Spacer(modifier = modifier)
    Spacer(modifier = modifier)
    Text(
        "PARAMETRO 2",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText(
        value = params[1].toString(),
        onValueChange = {
            params[1] = it.toDoubleOrNull() ?: 0.0
            onParamsChange(params)
        },
        modifier = Modifier.height(LocalDimens.current.editTextHeight)
    )
    Spacer(modifier = modifier)
    Spacer(modifier = modifier)
    Text(
        "PARAMETRO 2",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText(
        value = params[2].toString(),
        onValueChange = {
            params[2] = it.toDoubleOrNull() ?: 0.0
            onParamsChange(params)
        },
        modifier = Modifier.height(LocalDimens.current.editTextHeight)
    )
    Spacer(modifier = modifier)
    Spacer(modifier = modifier)
    Text(
        "NUMERO DE INTERACCIONES (N)",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)

    )
    Spacer(modifier = modifier)
    CustomEditText(
        value = size.toString(),
        onValueChange = { onSizeChange(it.toIntOrNull() ?: 0) },
        modifier = Modifier.height(LocalDimens.current.editTextHeight)
    )
}

@Composable
fun TwoParamsFieldsWithSize(
    params: MutableList<Double>,
    size: Int,
    onParamsChange: (List<Double>) -> Unit,
    onSizeChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    //TODO Binomial debe ser P1 Int, P2 0 < P2 < 1

    Text(
        "PARAMETRO 1",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText(
        value = params[0].toString(),
        onValueChange = {
            params[0] = it.toDoubleOrNull() ?: 0.0
            onParamsChange(params.toList())
        },
        modifier = Modifier.height(LocalDimens.current.editTextHeight)
    )
    Spacer(modifier = modifier)
    Spacer(modifier = modifier)
    Text(
        "PARAMETRO 2",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText(
        value = params[1].toString(),
        onValueChange = {
            params[1] = it.toDoubleOrNull() ?: 0.0
            onParamsChange(params)
        },
        modifier = Modifier.height(LocalDimens.current.editTextHeight)
    )
    Spacer(modifier = modifier)
    Spacer(modifier = modifier)
    Text(
        "NUMERO DE INTERACCIONES (N)",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)

    )
    Spacer(modifier = modifier)
    CustomEditText(
        value = size.toString(),
        onValueChange = { onSizeChange(it.toIntOrNull() ?: 0) },
        modifier = Modifier.height(LocalDimens.current.editTextHeight)
    )
}

@Composable
fun OneParamFieldsWithSize(
    params: MutableList<Double>,
    size: Int,
    onParamsChange: (List<Double>) -> Unit,
    onSizeChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        "PARAMETRO 1",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText(
        value = params[0].toString(),
        onValueChange = {
            params[0] = it.toDoubleOrNull() ?: 0.0
            onParamsChange(params.toList())
        },
        modifier = Modifier.height(LocalDimens.current.editTextHeight)
    )
    Spacer(modifier = modifier)
    Spacer(modifier = modifier)
    Text(
        "NUMERO DE INTERACCIONES (N)",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)

    )
    Spacer(modifier = modifier)
    CustomEditText(
        value = size.toString(),
        onValueChange = {
            onSizeChange(it.toIntOrNull() ?: 0)
        },
        modifier = Modifier.height(LocalDimens.current.editTextHeight)
    )
    Spacer(modifier = modifier)
}

@Composable
fun OneParamFields(
    params: MutableList<Double>,
    onParamsChange: (List<Double>) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        "PARAMETRO 1",
        color = SecondaryLight,
        textAlign = TextAlign.Start,
        style = Typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth(0.65f)
    )
    Spacer(modifier = modifier)
    CustomEditText(
        value = params[0].toString(),
        onValueChange = {
            params[0] = it.toDoubleOrNull() ?: 0.0
            onParamsChange(params.toList())
        },
        modifier = Modifier.height(LocalDimens.current.editTextHeight)
    )
}