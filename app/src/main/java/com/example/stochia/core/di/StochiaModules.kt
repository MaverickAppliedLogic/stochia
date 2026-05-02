package com.example.stochia.core.di

import com.example.stochia.core.services.calculation_system.local.LocalEngineServiceImpl
import com.example.stochia.data.KstoreRepository
import com.example.stochia.domain.usecase.GenMarkovUsecase
import com.example.stochia.domain.usecase.GenMontecarloUsecase
import com.example.stochia.domain.usecase.GetDistributionUsecase
import com.example.stochia.domain.usecase.GetStudyUsecase
import com.example.stochia.domain.usecase.ListAllStudyUsecase
import com.example.stochia.domain.usecase.SaveStudyUsecase
import com.example.stochia.domain.usecase.UpdateStudyUsecase
import com.example.stochia.repository.CalculationSystemRepository
import com.example.stochia.ui.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { LocalEngineServiceImpl() }

    single { KstoreRepository(androidContext()) }
    single { CalculationSystemRepository(get()) }

    factory { GenMarkovUsecase(get()) }
    factory { GenMontecarloUsecase(get()) }
    factory { GetDistributionUsecase(get()) }
    factory { GetStudyUsecase(get()) }
    factory { ListAllStudyUsecase(get()) }
    factory { SaveStudyUsecase(get()) }
    factory{ UpdateStudyUsecase(get()) }
}

val uiModule = module {
    viewModel { MainViewModel(
        get(),
        get(),
        get(),
        get(),
        get(),
        get()
        )
    }
}
