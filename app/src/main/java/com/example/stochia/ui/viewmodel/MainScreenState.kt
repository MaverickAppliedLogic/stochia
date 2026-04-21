package com.example.stochia.ui.viewmodel

import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.result.Result
import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType
import com.example.stochia.ui.viewmodel.MainViewModel.Screen


data class MainScreenState(
    val currentScreen: Screen = Screen.RESULT,
    val result: Result? = null,
    val distributionTypeSelected: DistributionType = DistributionType.NORMAL,
    val markovParams: MarkovParams = MarkovParams(),
    val distributionParams: DistributionParams = DistributionParams(),
    val settingsVisible: Boolean = false,
)


