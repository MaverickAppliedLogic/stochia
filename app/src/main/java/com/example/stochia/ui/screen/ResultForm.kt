package com.example.stochia.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.ui.theme.Typography

@Composable
fun ResultForm(
    montecarloResult: MontecarloResult?,
    markovResult: MarkovResult?,
    distributionResult: DistributionResult?
) {
    Text(text = distributionResult?.toString()?:"No hay resultados",
        style = Typography.bodyLarge)

}