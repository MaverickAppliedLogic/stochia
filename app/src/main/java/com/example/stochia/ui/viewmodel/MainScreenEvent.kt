package com.example.stochia.ui.viewmodel

import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType

sealed class MainScreenEvent{
    data object SettingsButtonClicked: MainScreenEvent()
    data class ScreenButtonClicked(val screen: Screen): MainScreenEvent()
    data class ChangedDistributionType(val type: DistributionType): MainScreenEvent()
}