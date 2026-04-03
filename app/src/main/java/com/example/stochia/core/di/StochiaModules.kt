package com.example.stochia.core.di

import com.example.stochia.core.services.calculation_system.local.LocalEngineServiceImpl
import com.example.stochia.domain.usecase.GenMarkovUsecase
import com.example.stochia.domain.usecase.GenMontecarloUsecase
import com.example.stochia.domain.usecase.GetDistributionUsecase
import com.example.stochia.repository.CalculationSystemRepository
import org.koin.dsl.module

val appModule = module {

    single { LocalEngineServiceImpl() }

    single { CalculationSystemRepository(get()) }

    factory { GenMarkovUsecase(get()) }
    factory { GenMontecarloUsecase(get()) }
    factory { GetDistributionUsecase(get()) }
}