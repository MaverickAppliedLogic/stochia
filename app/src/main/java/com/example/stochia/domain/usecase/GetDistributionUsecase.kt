package com.example.stochia.domain.usecase

import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.distribution.toDistributionResult
import com.example.stochia.repository.CalculationSystemRepository

class GetDistributionUsecase(
    private val repository: CalculationSystemRepository
) {
    operator fun invoke(data: DistributionParams): DistributionResult {
        return repository.getDistribution(data.data.toIntArray()).toDistributionResult()
    }
}
