package com.example.stochia.ui.screen.montecarlo_form_components.montecarlo_formfields_components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.stochia.ui.screen.common_components.CustomEditText
import com.example.stochia.ui.theme.LocalDimens
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.Typography

@Composable
fun ThreeParamsFieldsWithSize(
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
        modifier = Modifier.height(LocalDimens.current.editTextHeight).fillMaxWidth(0.8f)
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
        modifier = Modifier.height(LocalDimens.current.editTextHeight).fillMaxWidth(0.8f)
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
        modifier = Modifier.height(LocalDimens.current.editTextHeight).fillMaxWidth(0.8f)
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
        modifier = Modifier.height(LocalDimens.current.editTextHeight).fillMaxWidth(0.8f)
    )
}