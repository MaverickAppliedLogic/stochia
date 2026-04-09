package com.example.stochia.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    var currentScreen by mutableStateOf(value = Screen.RESULT)
        private set

    fun navigateTo(screen: Screen) {
        currentScreen = if (currentScreen == screen) Screen.RESULT
        else screen
    }
}

enum class Screen{
    RESULT,
    DISTRIBUTION,
    MONTECARLO,
    MARKOV
}
