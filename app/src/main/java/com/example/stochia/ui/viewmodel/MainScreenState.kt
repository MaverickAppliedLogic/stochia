package com.example.stochia.ui.viewmodel

import com.example.stochia.domain.model.engineMode.EngineMode
import com.example.stochia.domain.model.interfaces.Params
import com.example.stochia.domain.model.interfaces.Result
import com.example.stochia.domain.model.study.Study
import com.example.stochia.ui.viewmodel.MainViewModel.Screen


data class MainScreenState(
    val studyList: List<Study> = emptyList(),
    val currentScreen: Screen = Screen.RESULT,
    val params: Params? = null,
    val result: Result? = null,
    val paramsIsValidate: Pair<Boolean, String?> = true to null,
    val isNewResult: Boolean = true,
    val settingsVisible: Boolean = false,
    val engineMode: EngineMode = EngineMode.DYNAMIC,
    val engineMenuVisible: Boolean = false,
    val isLoading: Boolean = false,
)
