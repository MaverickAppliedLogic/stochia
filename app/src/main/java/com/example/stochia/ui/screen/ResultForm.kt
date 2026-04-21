package com.example.stochia.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.stochia.domain.model.result.Result
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.ui.theme.Typography

@Composable
fun ResultForm(
    result: Result?
) {
    when (result) {
        is DistributionResult -> {
            Text(
                result.toString(),
                style = Typography.headlineLarge
            )
        }

        is MontecarloResult -> {
            Text(
                result.toString(),
                style = Typography.headlineLarge
            )
        }
        is MarkovResult -> {
            Text(
                result.toString(),
                style = Typography.headlineLarge
            )
        }

    }
}