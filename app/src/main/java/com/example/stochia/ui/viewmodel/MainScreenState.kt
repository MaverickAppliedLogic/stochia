package com.example.stochia.ui.viewmodel

import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType
import com.example.stochia.ui.viewmodel.MainViewModel.Screen


data class MainScreenState(
    val currentScreen: Screen = Screen.RESULT,
    val distributionTypeSelected: DistributionType = DistributionType.NORMAL,
    val montecarloResult: MontecarloResult? = null,
    val settingsVisible: Boolean = false,
)
