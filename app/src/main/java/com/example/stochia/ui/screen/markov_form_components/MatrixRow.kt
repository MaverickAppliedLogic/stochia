package com.example.stochia.ui.screen.markov_form_components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.stochia.ui.screen.common_components.CustomEditText
import com.example.stochia.ui.theme.SecondaryLight
import com.example.stochia.ui.theme.Typography

@Composable
fun MatrixRow(
    state: String,
    value:  List<String>,
    modifier: Modifier = Modifier,
    onEvent: (List<String>) -> Unit
) {
    val data = value.toMutableList()
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(0.05f))
        Text(
            state,
            color = SecondaryLight,
            textAlign = TextAlign.Center,
            style = Typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth(0.20f)
        )
        Spacer(modifier = Modifier.weight(0.025f))
        CustomEditText(
            value = value[0],
            onValueChange = {
                data[0] = it
                onEvent(data.toList())
            },
            modifier = Modifier.weight(0.15f)
        )
        Spacer(modifier = Modifier.weight(0.025f))
        CustomEditText(
            value = value[1],
            onValueChange = {
                data[1] = it
                onEvent(data.toList())
            },
            modifier = Modifier.weight(0.15f)
        )
        Spacer(modifier = Modifier.weight(0.025f))
        CustomEditText(
            value = value[2],
            onValueChange = {
                data[2] = it
                onEvent(data.toList())
            },
            modifier = Modifier.weight(0.15f)
        )
        Spacer(modifier = Modifier.weight(0.05f))
    }
}