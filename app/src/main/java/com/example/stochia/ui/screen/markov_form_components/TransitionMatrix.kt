package com.example.stochia.ui.screen.markov_form_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.LocalDimens

@Composable
fun TransitionMatrix(
    states: List<String>,
    probs: List<Double>,
    modifier: Modifier,
    onEvent: (List<Double>) -> Unit
) {
    Column(modifier = modifier) {
        var probs1 by remember { mutableStateOf(probs.subList(0, 3).map { it.toString() }) }
        var probs2 by remember { mutableStateOf(probs.subList(3, 6).map { it.toString() }) }
        var probs3 by remember { mutableStateOf(probs.subList(6, 9).map { it.toString() }) }


        val returnData = remember(probs1, probs2, probs3) {
            probs1.map { it.toDouble() } +
                    probs2.map { it.toDouble() } +
                    probs3.map { it.toDouble() }
        }


        HeaderMatrixRow(modifier = Modifier.fillMaxWidth())
        MatrixRow(
            state = "Desde ${states[0]}",
            value = probs1,
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp),
            onEvent = {
                val formatted = Regex("\\d+(\\.\\d+)?")
                    .findAll(it.toString())
                    .map { it.value }
                    .toList()
                probs1 = formatted
                onEvent(returnData)

            }
        )
        MatrixRow(
            state = "Desde ${states[1]}",
            value = probs2,
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp),
            onEvent = {

                val formatted = Regex("\\d+(\\.\\d+)?")
                    .findAll(it.toString())
                    .map { it.value }
                    .toList()
                probs2 = formatted
                onEvent(returnData)
            }
        )
        MatrixRow(
            state = "Desde ${states[2]}",
            value = probs3,
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp),
            onEvent = {
                val formatted = Regex("\\d+(\\.\\d+)?")
                        .findAll(it.toString())
                        .map { it.value }
                        .toList()

                probs3 = formatted
                onEvent(returnData)
            }
        )
    }
}