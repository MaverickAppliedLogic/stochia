package com.example.stochia.core.di

import android.content.Context
import com.example.stochia.core.services.calculation_system.local.LocalEngineServiceImpl
import com.example.stochia.data.KstoreRepository
import com.example.stochia.domain.usecase.GenMarkovUsecase
import com.example.stochia.domain.usecase.GenMontecarloUsecase
import com.example.stochia.domain.usecase.GetDistributionUsecase
import com.example.stochia.repository.CalculationSystemRepository
import com.example.stochia.ui.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<Context> {androidContext()}

    single { LocalEngineServiceImpl() }

    single { KstoreRepository(get()) }
    single { CalculationSystemRepository(get()) }

    factory { GenMarkovUsecase(get()) }
    factory { GenMontecarloUsecase(get()) }
    factory { GetDistributionUsecase(get()) }
}

val uiModule = module {
    viewModel { MainViewModel(
        get(),
        get(),
        get()) }
}
