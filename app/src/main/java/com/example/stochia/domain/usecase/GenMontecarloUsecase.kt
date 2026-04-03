package com.example.stochia.domain.usecase

import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.domain.model.montecarlo.toMontecarloResult
import com.example.stochia.repository.CalculationSystemRepository

class GenMontecarloUsecase(
    private val repository: CalculationSystemRepository
) {
    operator fun invoke(data: MontecarloParams): MontecarloResult {
        val params = data.params.filterNotNull().toDoubleArray()
        val size = data.size
        val distribution = data.distribution

        return repository.genMontecarlo(distribution, params, size).toMontecarloResult()
    }
}