package com.example.stochia.ui.viewmodel

sealed class MainScreenEvent{
    data object SettingsButtonClicked: MainScreenEvent()
    data class ScreenButtonClicked(val screen: Screen): MainScreenEvent()
}