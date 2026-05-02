package com.example.stochia.ui.viewmodel

import com.example.stochia.domain.model.interfaces.Params
import com.example.stochia.domain.model.interfaces.Result
import com.example.stochia.domain.model.study.Study
import com.example.stochia.ui.screen.montecarlo_form_components.DistributionType
import com.example.stochia.ui.viewmodel.MainViewModel.Screen


data class MainScreenState(
    val studyList: List<Study> = emptyList(),
    val currentScreen: Screen = Screen.RESULT,
    val params: Params? = null,
    val result: Result? = null,
    val distributionTypeSelected: DistributionType = DistributionType.NORMAL,
    val settingsVisible: Boolean = false,
)


