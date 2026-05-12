package com.example.stochia.core.di

import com.example.stochia.core.network.createHttpClient
import com.example.stochia.core.services.CalculationSystemService
import com.example.stochia.data.calculation_system.`interface`.EngineServiceRepository
import com.example.stochia.data.calculation_system.local_repository.LocalEngineServiceRepositoryImpl
import com.example.stochia.data.calculation_system.remote_repository.RemoteEngineServiceRepositoryImpl
import com.example.stochia.data.kstore.KstoreRepository
import com.example.stochia.domain.usecase.GenMarkovUsecase
import com.example.stochia.domain.usecase.GenMontecarloUsecase
import com.example.stochia.domain.usecase.GetDistributionUsecase
import com.example.stochia.domain.usecase.GetStudyUsecase
import com.example.stochia.domain.usecase.ListAllStudyUsecase
import com.example.stochia.domain.usecase.SaveStudyUsecase
import com.example.stochia.domain.usecase.UpdateStudyUsecase
import com.example.stochia.ui.viewmodel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single { LocalEngineServiceRepositoryImpl() } bind EngineServiceRepository::class
    single { RemoteEngineServiceRepositoryImpl(get()) }

    single { KstoreRepository(androidContext()) }
    single { CalculationSystemService(get(), get()) }

    factory { GenMarkovUsecase(get()) }
    factory { GenMontecarloUsecase(get()) }
    factory { GetDistributionUsecase(get()) }
    factory { GetStudyUsecase(get()) }
    factory { ListAllStudyUsecase(get()) }
    factory { SaveStudyUsecase(get()) }
    factory { UpdateStudyUsecase(get()) }
}

val uiModule = module {
    viewModel {
        MainViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
}

val networkModule = module {
    single {
        createHttpClient()
    }
}
