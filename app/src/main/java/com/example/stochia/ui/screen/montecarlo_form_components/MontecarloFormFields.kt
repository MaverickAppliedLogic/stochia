package com.example.stochia.ui.screen.montecarlo_form_components

import android.util.Log
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import kotlin.collections.listOf

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
    type: DistributionType,
    modifier: Modifier = Modifier,
    onClick: (MainScreenEvent) -> Unit
) {
    val distribution = remember(type) { mutableStateOf(type) }
    val params = remember { mutableStateOf(listOf(0.0,0.0,0.0)) }
    val size = remember { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color = Neutral)
            .fillMaxWidth(0.9f)
    )
    {
        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            "TIPO DE DISTRIBUCION",
            color = SecondaryLight,
            textAlign = TextAlign.Start,
            style = Typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth(0.65f)
        )
        Spacer(modifier = Modifier.weight(0.05f))
        CustomDropdownMenu(
            selected = type.label,
            options = DistributionType.entries.map { it.name },
            modifier = Modifier,
            onClick = { onClick(it) }
        )
        Spacer(modifier = Modifier.weight(0.5f))
        when (type) {
            DistributionType.NORMAL,
            DistributionType.UNIFORM,
            DistributionType.BETA,
            DistributionType.BINOMIAL -> {
                TwoParamsFieldsWithSize(
                    params = params.value.toMutableList(),
                    size = size.intValue,
                    onParamsChange = { params.value = it },
                    onSizeChange = { size.intValue = it },
                    modifier = Modifier.weight(0.2f)
                )
            }

            DistributionType.EXPONENTIAL,
            DistributionType.POISSON -> {
                OneParamFieldsWithSize(
                    params = params.value.toMutableList(),
                    size = size.intValue,
                    onParamsChange = { params.value = it },
                    onSizeChange = { size.intValue = it },
                    modifier = Modifier.weight(0.2f)
                )
            }

            DistributionType.GEOMETRICAL -> {
                OneParamFields(
                    params = params.value.toMutableList(),
                    onParamsChange = { params.value = it },
                    modifier = Modifier.weight(0.2f)
                )
            }

            DistributionType.MULTINOMIAL -> {
                ThreeParamsFieldsWithSize(
                    params = params.value.toMutableList(),
                    size = size.intValue,
                    onParamsChange = { params.value = it },
                    onSizeChange = { size.intValue = it },
                    modifier = Modifier.weight(0.2f)
                )
            }
        }

        Spacer(modifier.weight(1f))
        Card(
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = Primary,
                contentColor = SecondaryLight,
                disabledContainerColor = Primary,
                disabledContentColor = SecondaryLight
            ),
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .height(LocalDimens.current.commitButton),
            onClick = {
                Log.d("MontecarloFormFields", "onClick ${params.value}")
                onClick( MainScreenEvent.SimulateMontecarloButtonClicked(
                    MontecarloParams(
                        distribution = distribution.value.name,
                        params = params.value,
                        size = size.intValue
                    )
                ))
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