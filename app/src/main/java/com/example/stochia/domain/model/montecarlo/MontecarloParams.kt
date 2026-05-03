package com.example.stochia.domain.model.montecarlo

import com.example.stochia.domain.model.interfaces.Params
import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType

data class MontecarloParams(
    val distribution: String = DistributionType.NORMAL.name,
    val params: List<Double> = listOf(0.0,0.0,0.0),
    val size: Int = 0
): Params
