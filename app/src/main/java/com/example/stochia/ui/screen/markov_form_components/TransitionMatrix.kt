package com.example.stochia.ui.screen.markov_form_components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stochia.ui.theme.LocalDimens

@Composable
fun TransitionMatrix(
    states: List<String>,
    probs: List<Double>,
    modifier: Modifier,
    onEvent: (List<Double>) -> Unit
){
    val returnData = probs.map { it }.toMutableList()
    Column(modifier = modifier) {
        HeaderMatrixRow(modifier = Modifier.fillMaxWidth())
        MatrixRow(
            state = "Desde ${states[0]}",
            value= probs.subList(0,3).map { it.toString() },
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp),
            onEvent = {
                returnData.subList(0,3).clear()
                returnData.addAll(0, it.map { value -> value.toDouble() })
                onEvent(returnData)
            }
        )
        MatrixRow(
            state = "Desde ${states[1]}",
            value= probs.subList(3,6).map { it.toString() },
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp),
            onEvent = {
                returnData.subList(3,6).clear()
                returnData.addAll(3, it.map { value -> value.toDouble() })
                onEvent(returnData)
            }
        )
        MatrixRow(
            state = "Desde ${states[2]}",
            value= probs.subList(6,9).map { it.toString() },
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp),
            onEvent = {
                returnData.subList(6,9).clear()
                returnData.addAll(6, it.map { value -> value.toDouble() })
                onEvent(returnData)
            }
        )
    }
}