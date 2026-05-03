package com.example.stochia.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.interfaces.Params
import com.example.stochia.domain.model.interfaces.Result
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.usecase.GenMarkovUsecase
import com.example.stochia.domain.usecase.GenMontecarloUsecase
import com.example.stochia.domain.usecase.GetDistributionUsecase
import com.example.stochia.domain.usecase.GetStudyUsecase
import com.example.stochia.domain.usecase.ListAllStudyUsecase
import com.example.stochia.domain.usecase.SaveStudyUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val genMontecarloUsecase: GenMontecarloUsecase,
    private val getDistributionUsecase: GetDistributionUsecase,
    private val genMarkovUsecase: GenMarkovUsecase,
    private val listAllStudyUsecase: ListAllStudyUsecase,
    private val saveStudyUsecase: SaveStudyUsecase,
    private val getStudyUsecase: GetStudyUsecase
) : ViewModel() {

    private var _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    init {
        fetchStudies()
    }

    private fun navigateTo(screen: Screen) {
        _state.update {
            val nextScreen = if (_state.value.currentScreen == screen) Screen.RESULT
            else screen
            it.copy(
                currentScreen = nextScreen,
                params = if (nextScreen == Screen.RESULT) it.params else null)
        }
    }

    private fun analyzeDistribution(data: DistributionParams) {
        _state.update { it.copy(result = getDistributionUsecase(data), isNewResult = true) }
        Log.d("MainViewModel", "analyzeDistribution: ${_state.value.result}")
    }

    private fun genMontecarlo(data: MontecarloParams) {
        _state.update {
            it.copy(result = genMontecarloUsecase(data), isNewResult = true)
        }
        Log.d("MainViewModel", "genMontecarlo: ${_state.value.result}")

    }

    private fun genMarkov(data: MarkovParams) {
        _state.update {
            it.copy(result = genMarkovUsecase(data), isNewResult = true)
        }
        Log.d("MainViewModel", "genMarkov: ${_state.value.result}")
    }

    private fun fetchStudies() {
        viewModelScope.launch {
            _state.update { it.copy(studyList = listAllStudyUsecase()) }
        }
    }

    private fun saveStudy(params: Params, result: Result) {
        viewModelScope.launch {
            saveStudyUsecase(params = params, result = result)
            fetchStudies()
        }
    }

    private fun getStudy(id: String) {
        viewModelScope.launch {
            val study = getStudyUsecase(id)
            _state.update { it.copy(
                result = study!!.result,
                params = study.params,
                isNewResult = false
            )}
        }
    }


    fun onEvent(event: MainScreenEvent) {
        Log.d("MainViewModel", "onEvent: $event")
        when (event) {
            is MainScreenEvent.SettingsButtonClicked -> {
                _state.update { it.copy(settingsVisible = !it.settingsVisible) }
            }

            is MainScreenEvent.ScreenButtonClicked -> {
                navigateTo(event.screen)
            }

            is MainScreenEvent.SimulateMontecarloButtonClicked -> {
                Log.d("MainViewModel", "SimulateMontecarloButtonClicked")
                genMontecarlo(event.data)
            }

            is MainScreenEvent.SimulateMarkovButtonClicked -> {
                Log.d("MainViewModel", "SimulateMarkovButtonClicked")
                genMarkov(_state.value.params as MarkovParams)
            }

            is MainScreenEvent.DistributionButtonClicked -> {
                Log.d("MainViewModel", "DistributionButtonClicked ${_state.value.params}")
                analyzeDistribution(_state.value.params as DistributionParams)
            }

            is MainScreenEvent.ChangeMontecarloDistribution -> {
                Log.d("MainViewModel", "ChangeMontecarloDistribution: ${event.distribution}")
                val oldParams = _state.value.params as MontecarloParams?
                _state.update {
                    it.copy(
                        params = oldParams?.copy(distribution = event.distribution.name)
                            ?: MontecarloParams(
                                distribution = event.distribution.name,
                            )
                    )
                }
            }

            is MainScreenEvent.ChangeMontecarloParams -> {
                Log.d("MainViewModel", "ChangeMontecarloParams: ${event.params}")
                val oldParams = _state.value.params as MontecarloParams?
                _state.update {
                    it.copy(
                        params = oldParams?.copy(params = event.params)
                            ?: MontecarloParams(
                                params = event.params
                            )
                    )
                }
            }

            is MainScreenEvent.ChangeMontecarloSize -> {
                Log.d("MainViewModel", "ChangeMontecarloSize: ${event.size}")
                val oldParams = _state.value.params as MontecarloParams?
                _state.update {
                    it.copy(
                        params = oldParams?.copy(size = event.size)
                            ?: MontecarloParams(
                                size = event.size
                            )
                    )
                }
            }

            is MainScreenEvent.ChangeMarkovStates -> {
                Log.d("MainViewModel", "ChangeMarkovStates: ${event.states}")
                val oldParams = _state.value.params as MarkovParams?
                _state.update {
                    it.copy(
                        params = oldParams?.copy(states = event.states)
                            ?: MarkovParams(states = event.states)
                    )
                }
            }

            is MainScreenEvent.ChangeMarkovProbs -> {
                Log.d("MainViewModel", "ChangeMarkovProbs: ${event.probs}")
                val oldParams = _state.value.params as MarkovParams?
                _state.update {
                    it.copy(
                        params = oldParams?.copy(probs = event.probs)
                            ?: MarkovParams(probs = event.probs)
                    )
                }
            }

            is MainScreenEvent.ChangeMarkovInitState -> {
                Log.d("MainViewModel", "ChangeMarkovInitState: ${event.state}")
                val oldParams = state.value.params as MarkovParams?
                _state.update {
                    it.copy(
                        params = oldParams?.copy(initState = event.state)
                            ?: MarkovParams(initState = event.state)
                    )
                }
            }

            is MainScreenEvent.ChangeMarkovSteps -> {
                Log.d("MainViewModel", "ChangeMarkovSteps: ${event.steps}")
                val oldParams = state.value.params as MarkovParams?
                _state.update {
                    it.copy(
                        params = oldParams?.copy(steps = event.steps)
                            ?: MarkovParams(steps = event.steps)
                    )
                }
            }

            is MainScreenEvent.ChangeDistributionData -> {
                val oldParams = state.value.params as DistributionParams?
                Log.d("MainViewModel", "ChangeDistributionData: $oldParams")
                _state.update {
                    it.copy(
                        params = oldParams?.copy(data = event.data)
                            ?: DistributionParams(event.data)
                    )
                }
            }

            is MainScreenEvent.SaveStudyButtonClicked -> {
                Log.d("MainViewModel", "SaveStudyButtonClicked ${state.value.params}")
                viewModelScope.launch {
                    saveStudy(params = state.value.params!!, result = state.value.result!!)
                }

            }

            is MainScreenEvent.StudyCardClicked -> {
                getStudy(event.id)
            }

        }
    }

    enum class Screen {
        RESULT,
        DISTRIBUTION,
        MONTECARLO,
        MARKOV
    }
}
