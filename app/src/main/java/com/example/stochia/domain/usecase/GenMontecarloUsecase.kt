package com.example.stochia.domain.usecase

import com.example.stochia.core.services.CalculationSystemService
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.model.montecarlo.MontecarloResult

class GenMontecarloUsecase(
    private val service: CalculationSystemService
) {
    operator fun invoke(data: MontecarloParams): MontecarloResult = service.genMontecarlo(data)
}
