package com.example.stochia.ui.viewmodel

import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType
import com.example.stochia.ui.viewmodel.MainViewModel.Screen


sealed class MainScreenEvent{
    data object SimulateMarkovButtonClicked: MainScreenEvent()
    data object SettingsButtonClicked: MainScreenEvent()
    data class ScreenButtonClicked(val screen: Screen): MainScreenEvent()
    data class SimulateMontecarloButtonClicked(val data: MontecarloParams): MainScreenEvent()

    data class ChangeMontecarloDistribution(val distribution: DistributionType) : MainScreenEvent()
    data class ChangeMontecarloParams(val params: List<Double>) : MainScreenEvent()
    data class ChangeMontecarloSize(val size: Int) : MainScreenEvent()


    data class ChangeMarkovStates(val states: List<String>): MainScreenEvent()
    data class ChangeMarkovProbs(val probs: List<Double>): MainScreenEvent()
    data class ChangeMarkovInitState(val state: Int): MainScreenEvent()
    data class ChangeMarkovSteps(val steps: Int): MainScreenEvent()

    data class ChangeDistributionData(val data: List<Double>): MainScreenEvent()
    data object DistributionButtonClicked: MainScreenEvent()

    data object SaveStudyButtonClicked: MainScreenEvent()
    data class StudyCardClicked(val id: String): MainScreenEvent()
    data object ClearSnackbar: MainScreenEvent()
}




