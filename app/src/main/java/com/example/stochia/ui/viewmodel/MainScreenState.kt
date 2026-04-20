package com.example.stochia.ui.viewmodel

import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType
import com.example.stochia.ui.viewmodel.MainViewModel.Screen


data class MainScreenState(
    val currentScreen: Screen = Screen.RESULT,
    val distributionTypeSelected: DistributionType = DistributionType.NORMAL,
    val montecarloResult: MontecarloResult? = null,
    val markovParams: MarkovParamsState = MarkovParamsState(),
    val markovStates: MarkovResult? = null,
    val settingsVisible: Boolean = false,
)


data class MarkovParamsState(
    val states: List<Int> = emptyList(),
    val probs: List<Double> = emptyList(),
    val init_state: Int = 0,
    val steps: Int = 0
)