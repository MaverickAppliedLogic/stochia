package com.example.stochia.ui.viewmodel

data class MainScreenState(
    val currentScreen: Screen = Screen.RESULT,
    val settingsVisible: Boolean = false,
)
