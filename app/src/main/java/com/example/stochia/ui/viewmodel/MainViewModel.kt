package com.example.stochia.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stochia.core.services.CalculationSystemService
import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.interfaces.Params
import com.example.stochia.domain.model.interfaces.Result
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.usecase.GenMarkovUsecase
import com.example.stochia.domain.usecase.GenMontecarloUsecase
import com.example.stochia.domain.usecase.GetDistributionUsecase
import com.example.stochia.domain.usecase.GetEngineModeUsecase
import com.example.stochia.domain.usecase.GetStudyUsecase
import com.example.stochia.domain.usecase.ListAllStudyUsecase
import com.example.stochia.domain.usecase.SaveEngineModeUsecase
import com.example.stochia.domain.usecase.SaveStudyUsecase
import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType
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
    private val getStudyUsecase: GetStudyUsecase,
    private val calculationService: CalculationSystemService,
    private val getEngineModeUsecase: GetEngineModeUsecase,
    private val saveEngineModeUsecase: SaveEngineModeUsecase
) : ViewModel() {

    private var _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    init {
        fetchStudies()
        loadEngineMode()
    }

    private fun loadEngineMode() {
        viewModelScope.launch {
            val savedMode = getEngineModeUsecase()
            calculationService.engineMode = savedMode
            _state.update { it.copy(engineMode = savedMode) }
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
                genMarkov(_state.value.params as? MarkovParams?: MarkovParams())
            }

            is MainScreenEvent.DistributionButtonClicked -> {
                Log.d("MainViewModel", "DistributionButtonClicked ${_state.value.params}")
                analyzeDistribution(
                    _state.value.params as? DistributionParams?: DistributionParams()
                )
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

            is MainScreenEvent.ClearSnackbar -> {
                clearSnackbar()
            }

            is MainScreenEvent.ReuseStudyParamsClicked -> {
                reuseStudyParams(event.id)
            }

            is MainScreenEvent.UseDistributionResultAsMontecarlo -> {
                useDistributionResultAsMontecarlo(event.id)
            }

            is MainScreenEvent.EngineButtonClicked -> {
                _state.update { it.copy(engineMenuVisible = !it.engineMenuVisible) }
            }

            is MainScreenEvent.EngineSelected -> {
                calculationService.engineMode = event.mode
                _state.update { it.copy(engineMode = event.mode, engineMenuVisible = false) }
                viewModelScope.launch { saveEngineModeUsecase(event.mode) }
            }

            is MainScreenEvent.EngineMenuDismissed -> {
                _state.update { it.copy(engineMenuVisible = false) }
            }

        }
    }


    //SCREEN METHODS
    enum class Screen {
        RESULT,
        DISTRIBUTION,
        MONTECARLO,
        MARKOV
    }

    private fun navigateTo(screen: Screen) {
        _state.update {
            val nextScreen = if (_state.value.currentScreen == screen) Screen.RESULT
            else screen
            it.copy(
                currentScreen = nextScreen,
                params = if (nextScreen == Screen.RESULT) it.params else null
            )
        }
    }

    private fun writeSnackbar(message: String) {
        _state.update { it.copy(paramsIsValidate = false to message) }
    }

    private fun clearSnackbar() {
        _state.update { it.copy(paramsIsValidate = true to null) }
    }


    //CALCULATION METHODS
    private fun analyzeDistribution(data: DistributionParams) {
        if (!validateDistributionParams(data).first) {
            writeSnackbar(validateDistributionParams(data).second ?: "Error")
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val result = getDistributionUsecase(data)
                _state.update { it.copy(result = result, isNewResult = true, isLoading = false) }
                Log.d("MainViewModel", "analyzeDistribution: $result")
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun genMontecarlo(data: MontecarloParams) {
        if (!validateMontecarloParams(data).first) {
            writeSnackbar(validateMontecarloParams(data).second ?: "Error")
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val result = genMontecarloUsecase(data)
                _state.update { it.copy(result = result, isNewResult = true, isLoading = false) }
                Log.d("MainViewModel", "genMontecarlo: $result")
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun genMarkov(data: MarkovParams) {
        if (!validateMarkovParams(data).first) {
            writeSnackbar(validateMarkovParams(data).second ?: "Error")
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val result = genMarkovUsecase(data)
                _state.update { it.copy(result = result, isNewResult = true, isLoading = false) }
                Log.d("MainViewModel", "genMarkov: $result")
            } finally {
                _state.update { it.copy(isLoading = false) }
            }
        }
    }



    // STUDY METHODS
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
            _state.update {
                it.copy(
                    result = study!!.result,
                    params = study.params,
                    isNewResult = false
                )
            }
        }
    }

    private fun reuseStudyParams(id: String) {
        viewModelScope.launch {
            val study = getStudyUsecase(id) ?: return@launch
            val params = study.params ?: return@launch
            val targetScreen = when (params) {
                is DistributionParams -> Screen.DISTRIBUTION
                is MontecarloParams -> Screen.MONTECARLO
                is MarkovParams -> Screen.MARKOV
                else -> return@launch
            }
            _state.update { it.copy(currentScreen = targetScreen, params = params) }
        }
    }

    private fun useDistributionResultAsMontecarlo(id: String) {
        viewModelScope.launch {
            val study = getStudyUsecase(id) ?: return@launch
            val result = study.result as? DistributionResult ?: return@launch
            val montecarloParams = MontecarloParams(
                distribution = DistributionType.NORMAL.name,
                params = listOf(result.mean, result.stdDev),
                size = 1000
            )
            _state.update { it.copy(currentScreen = Screen.MONTECARLO, params = montecarloParams) }
        }
    }



    //VALIDATION METHODS
    fun validateMontecarloParams(params: MontecarloParams): Pair<Boolean, String?> {

        val distributionString = params.distribution
        val parameters = params.params
        val size = params.size

        // Validación general
        if (size <= 0) {
            return false to "El tamaño de la simulación debe ser mayor que 0"
        }


        // Validaciones específicas por distribución
        return when (DistributionType.valueOf(distributionString)) {

            DistributionType.NORMAL -> {
                val mean = parameters.getOrNull(0)
                val stdDev = parameters.getOrNull(1)

                when {
                    mean == null || stdDev == null ->
                        false to "Faltan parámetros para la distribución Normal"

                    stdDev <= 0 ->
                        false to "La desviación estándar debe ser mayor que 0"

                    else -> true to null
                }
            }

            DistributionType.UNIFORM -> {
                val low = parameters.getOrNull(0)
                val high = parameters.getOrNull(1)

                when {
                    low == null || high == null ->
                        false to "Faltan parámetros para la distribución Uniforme"

                    low >= high ->
                        false to "El parámetro 'low' debe ser menor que 'high'"

                    else -> true to null
                }
            }

            DistributionType.BETA -> {
                val alpha = parameters.getOrNull(0)
                val beta = parameters.getOrNull(1)

                when {
                    alpha == null || beta == null ->
                        false to "Faltan parámetros para la distribución Beta"

                    alpha <= 0 ->
                        false to "El parámetro α debe ser mayor que 0"

                    beta <= 0 ->
                        false to "El parámetro β debe ser mayor que 0"

                    else -> true to null
                }
            }

            DistributionType.BINOMIAL -> {
                val n = parameters.getOrNull(0)
                val probability = parameters.getOrNull(1)

                when {
                    n == null || probability == null ->
                        false to "Faltan parámetros para la distribución Binomial"

                    n < 1 ->
                        false to "El número de ensayos n debe ser mayor o igual a 1"

                    probability < 0 || probability > 1 ->
                        false to "La probabilidad p debe estar entre 0 y 1"

                    else -> true to null
                }
            }

            DistributionType.EXPONENTIAL -> {
                val scale = parameters.getOrNull(0)

                when {
                    scale == null ->
                        false to "Falta el parámetro para la distribución Exponencial"

                    scale <= 0 ->
                        false to "El parámetro scale debe ser mayor que 0"

                    else -> true to null
                }
            }

            DistributionType.POISSON -> {
                val lambda = parameters.getOrNull(0)

                when {
                    lambda == null ->
                        false to "Falta el parámetro para la distribución Poisson"

                    lambda <= 0 ->
                        false to "El parámetro λ debe ser mayor que 0"

                    else -> true to null
                }
            }

            DistributionType.GEOMETRICAL -> {
                val probability = parameters.getOrNull(0)

                when {
                    probability == null ->
                        false to "Falta el parámetro para la distribución Geométrica"

                    probability <= 0 || probability > 1 ->
                        false to "La probabilidad p debe estar entre 0 y 1"

                    else -> true to null
                }
            }

            DistributionType.MULTINOMIAL -> {
                val n = parameters.getOrNull(0)
                val p1 = parameters.getOrNull(1)
                val p2 = parameters.getOrNull(2)

                when {
                    n == null || p1 == null || p2 == null ->
                        false to "Faltan parámetros para la distribución Multinomial"

                    n < 1 ->
                        false to "El número de ensayos n debe ser mayor o igual a 1"

                    p1 < 0 || p2 < 0 ->
                        false to "Las probabilidades deben ser mayores o iguales a 0"

                    (p1 + p2) > 1 ->
                        false to "La suma p1 + p2 no puede ser mayor que 1"

                    else -> true to null
                }
            }

        }
    }

    fun validateMarkovParams(params: MarkovParams): Pair<Boolean, String?> {

        val states = params.states
        val probabilities = params.probs
        val initialState = params.initState
        val steps = params.steps
        val numberOfStates = states.size


        // 2. Validar estado inicial
        if (initialState !in 0 until numberOfStates) {
            return false to "El estado inicial debe estar entre 0 y ${numberOfStates - 1}"
        }

        // 3. Validar pasos
        if (steps < 1) {
            return false to "La cantidad de pasos debe ser mayor o igual a 1"
        }

        // 4. Validar filas de la matriz
        for (row in 0 until numberOfStates) {
            val rowStart = row * numberOfStates
            val rowValues = probabilities.subList(rowStart, rowStart + numberOfStates)

            // Ningún valor negativo
            if (rowValues.any { it < 0 }) {
                return false to "Las probabilidades no pueden ser negativas (fila ${row + 1})"
            }

            // Ningún valor mayor que 1
            if (rowValues.any { it > 1 }) {
                return false to "Las probabilidades no pueden ser mayores que 1 (fila ${row + 1})"
            }

            // La fila debe sumar 1
            val sum = rowValues.sum()
            if (kotlin.math.abs(sum - 1.0) > 1e-6) {
                return false to "La fila ${row + 1} debe sumar 1 (actual: ${"%.2f".format(sum)})"
            }
        }

        return true to null
    }

    fun validateDistributionParams(params: DistributionParams): Pair<Boolean, String?> {

        val data = params.data

        // 1. No puede estar vacío
        if (data.isEmpty()) {
            return false to "Debes introducir al menos un valor para calcular la distribución"
        }

        // 2. No puede tener NaN o Infinity
        if (data.any { it.isNaN() || it.isInfinite() }) {
            return false to "Los datos contienen valores inválidos (NaN o infinito)"
        }

        // 3. Debe tener al menos 2 valores
        if (data.size < 2) {
            return false to "Debes introducir al menos dos valores para calcular la distribución"
        }

        return true to null
    }



}
