package com.example.stochia.ui.viewmodel

import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType
import com.example.stochia.ui.viewmodel.MainViewModel.Screen


sealed class MainScreenEvent{
    data object SimulateMarkovButtonClicked: MainScreenEvent()
    data object SettingsButtonClicked: MainScreenEvent()
    data class ScreenButtonClicked(val screen: Screen): MainScreenEvent()
    data class ChangedDistributionType(val type: DistributionType): MainScreenEvent()
    data class SimulateMontecarloButtonClicked(val data: MontecarloParams): MainScreenEvent()

    data class ChangeMarkovStates(val states: List<String>): MainScreenEvent()
    data class ChangeMarkovProbs(val probs: List<Double>): MainScreenEvent()
    data class ChangeMarkovInitState(val state: Int): MainScreenEvent()
    data class ChangeMarkovSteps(val steps: Int): MainScreenEvent()

    data class ChangeDistributionData(val data: List<Int>): MainScreenEvent()
    data object DistributionButtonClicked: MainScreenEvent()
}




