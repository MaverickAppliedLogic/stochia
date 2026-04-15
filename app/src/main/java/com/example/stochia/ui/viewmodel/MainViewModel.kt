package com.example.stochia.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.usecase.GenMarkovUsecase
import com.example.stochia.domain.usecase.GenMontecarloUsecase
import com.example.stochia.domain.usecase.GetDistributionUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel(
    private val genMontecarloUsecase: GenMontecarloUsecase,
    private val getDistributionUsecase: GetDistributionUsecase,
    private val genMarkovUsecase: GenMarkovUsecase
    ) : ViewModel() {

    private var _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()


    private fun navigateTo(screen: Screen) {
        _state.update {
            val nextScreen = if (_state.value.currentScreen == screen) Screen.RESULT
            else screen
            it.copy(currentScreen = nextScreen)
        }
    }

    private fun genMontecarlo(data: MontecarloParams) {
        genMontecarloUsecase(data)
    }

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.SettingsButtonClicked ->
                _state.update { it.copy(settingsVisible = !it.settingsVisible) }

            is MainScreenEvent.ScreenButtonClicked ->
                navigateTo(event.screen)

            is MainScreenEvent.ChangedDistributionType ->
                _state.update { it.copy(distributionTypeSelected = event.type) }

            is MainScreenEvent.SimulateMontecarloButtonClicked ->
                genMontecarlo(event.data)

        }
    }

    enum class Screen {
        RESULT,
        DISTRIBUTION,
        MONTECARLO,
        MARKOV
    }
}
