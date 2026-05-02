package com.example.stochia.ui.screen.result_form_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.domain.model.montecarlo.MontecarloType
import com.example.stochia.ui.screen.result_form_components.montecarlo_result_components.GeometricalMontecarloResult
import com.example.stochia.ui.screen.result_form_components.montecarlo_result_components.MultinomialMontecarloResult
import com.example.stochia.ui.screen.result_form_components.montecarlo_result_components.WithValuesMontecarloResult
import com.example.stochia.ui.screen.result_form_components.montecarlo_result_components.WithoutValuesMontecarloResult
import com.example.stochia.ui.theme.NeutralDarker
import com.example.stochia.ui.viewmodel.MainScreenEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MontecarloResultScreen(
    result: MontecarloResult,
    onEvent: (MainScreenEvent) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(NeutralDarker)
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        when (result.distribution) {
            MontecarloType.GEOMETRICAL -> GeometricalMontecarloResult(result = result, modifier = modifier)
            MontecarloType.MULTINOMIAL -> MultinomialMontecarloResult(result = result, modifier = modifier)
            MontecarloType.BINOMIAL -> WithoutValuesMontecarloResult(result = result, modifier = modifier)
            MontecarloType.POISSON -> WithoutValuesMontecarloResult(result = result, modifier = modifier)
            else -> WithValuesMontecarloResult(result = result, modifier = modifier)
        }
        Spacer(modifier.height(30.dp))
        Button(onClick = {onEvent(MainScreenEvent.SaveStudyButtonClicked)}) {
            Text("Guardar Estudio")
        }
        Spacer(modifier.height(30.dp))
    }
}