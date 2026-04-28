package com.example.stochia.ui.screen


import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.stochia.domain.model.result.Result
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.ui.screen.result_form_components.DistributionResultScreen
import com.example.stochia.ui.screen.result_form_components.MarkovResultScreen
import com.example.stochia.ui.screen.result_form_components.MontecarloResultScreen

@Composable
fun ResultScreen(
    result: Result?
) {
    val scrollState = rememberScrollState()

    when (result) {
        is DistributionResult -> {
            DistributionResultScreen(
                result = result,
                scrollState = scrollState,
                modifier = Modifier
            )
        }


        is MontecarloResult -> {
            MontecarloResultScreen(
                result = result,
                scrollState = scrollState,
                modifier = Modifier
            )
        }

        is MarkovResult -> {
            MarkovResultScreen(
                result = result,
                scrollState = scrollState,
            )
        }

    }
}