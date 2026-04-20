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
    modifier: Modifier,
    onEvent: (List<Int>) -> Unit
){
    Column(modifier = modifier) {
        HeaderMatrixRow(modifier = Modifier.fillMaxWidth())
        MatrixRow(
            state = "Desde A",
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp)
        )
        MatrixRow(
            state = "Desde B",
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp)
        )
        MatrixRow(
            state = "Desde C",
            modifier = Modifier
                .height(LocalDimens.current.editTextHeight)
                .padding(vertical = 3.dp)

        )
    }
}