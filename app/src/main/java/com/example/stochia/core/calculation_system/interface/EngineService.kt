package com.example.stochia.core.calculation_system.`interface`

import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.model.montecarlo.MontecarloResult

interface EngineService {

    fun gen_montecarlo(data: MontecarloParams): MontecarloResult


    fun gen_markov()

    fun get_distribution(params: DistributionParams): DistributionResult
}
