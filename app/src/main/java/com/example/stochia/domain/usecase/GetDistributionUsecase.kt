package com.example.stochia.domain.usecase

import com.example.stochia.core.services.CalculationSystemService
import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.distribution.DistributionResult

class GetDistributionUsecase(
    private val service: CalculationSystemService
) {
    operator fun invoke(data: DistributionParams): DistributionResult = service.getDistribution(data)
}
