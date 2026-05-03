package com.example.stochia.ui.screen.markov_form_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

        // Convertimos la matriz en filas SIN remember
        var row1 by remember { mutableStateOf(probs.subList(0, 3).map { it.toString() }) }
        var row2 by remember { mutableStateOf(probs.subList(3, 6).map { it.toString() }) }
        var row3 by remember { mutableStateOf(probs.subList(6, 9).map { it.toString() }) }

        // Cada vez que el ViewModel cambie la matriz, actualizamos las filas
        // Esto evita que se queden congeladas
        LaunchedEffect(probs) {
            row1 = probs.subList(0, 3).map { it.toString() }
            row2 = probs.subList(3, 6).map { it.toString() }
            row3 = probs.subList(6, 9).map { it.toString() }
        }

        HeaderMatrixRow(modifier = Modifier.fillMaxWidth())

        MatrixRow(
            state = "Desde ${states[0]}",
            value = row1,
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp),
            onEvent = { newValues ->
                val formatted = newValues.map {
                    Regex("\\d+(\\.\\d+)?")
                        .find(it)
                        ?.value
                        ?: "0"
                }
                row1 = formatted

                val updated = formatted.map { it.toDouble() } +
                        row2.map { it.toDouble() } +
                        row3.map { it.toDouble() }

                onEvent(updated)
            }
        )

        MatrixRow(
            state = "Desde ${states[1]}",
            value = row2,
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp),
            onEvent = { newValues ->
                val formatted = newValues.map {
                    Regex("\\d+(\\.\\d+)?")
                        .find(it)
                        ?.value
                        ?: "0"
                }
                row2 = formatted

                val updated = row1.map { it.toDouble() } +
                        formatted.map { it.toDouble() } +
                        row3.map { it.toDouble() }

                onEvent(updated)
            }
        )

        MatrixRow(
            state = "Desde ${states[2]}",
            value = row3,
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp),
            onEvent = { newValues ->
                val formatted = newValues.map {
                    Regex("\\d+(\\.\\d+)?")
                        .find(it)
                        ?.value
                        ?: "0"
                }
                row3 = formatted

                val updated = row1.map { it.toDouble() } +
                        row2.map { it.toDouble() } +
                        formatted.map { it.toDouble() }

                onEvent(updated)
            }
        )
    }
}
