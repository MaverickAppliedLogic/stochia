package com.example.stochia.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.stochia.domain.model.markov.MarkovParams
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
       _state.update {
           it.copy(montecarloResult = genMontecarloUsecase(data))
       }
        Log.d("MainViewModel", "genMontecarlo: ${_state.value.montecarloResult}")

    }

    private fun genMarkov(data: MarkovParams){
        _state.update {
            it.copy(markovResult = genMarkovUsecase(data))
        }
    }

    fun onEvent(event: MainScreenEvent) {
        Log.d("MainViewModel", "onEvent: $event")
        when (event) {
            is MainScreenEvent.SettingsButtonClicked ->
                _state.update { it.copy(settingsVisible = !it.settingsVisible) }

            is MainScreenEvent.ScreenButtonClicked ->
                navigateTo(event.screen)

            is MainScreenEvent.ChangedDistributionType ->
                _state.update { it.copy(distributionTypeSelected = event.type) }

            is MainScreenEvent.SimulateMontecarloButtonClicked ->{
                Log.d("MainViewModel", "SimulateMontecarloButtonClicked")
                genMontecarlo(event.data)
            }
            is MainScreenEvent.SimulateMarkovButtonClicked ->{
                Log.d("MainViewModel", "SimulateMarkovButtonClicked")
                genMarkov(_state.value.markovParams)
            }
            is MainScreenEvent.ChangeMarkovStates ->
                _state.update { it.copy(
                    markovParams = it.markovParams.copy(states = event.states)
                ) }
            is MainScreenEvent.ChangeMarkovProbs ->
                _state.update { it.copy(
                    markovParams = it.markovParams.copy(probs = event.probs)
                )}
            is MainScreenEvent.ChangeMarkovInitState ->
                _state.update { it.copy(
                    markovParams = it.markovParams.copy(init_state = event.state)
                )}
            is MainScreenEvent.ChangeMarkovSteps ->
                _state.update { it.copy(
                    markovParams = it.markovParams.copy(steps = event.steps)
                )}


        }
    }

    enum class Screen {
        RESULT,
        DISTRIBUTION,
        MONTECARLO,
        MARKOV
    }
}
