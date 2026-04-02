package com.example.stochia.core.calculation_system.`interface`

import com.example.stochia.domain.model.distribution.DistributionResult

interface EngineService {

    fun gen_montecarlo()

    fun gen_markov()

    fun get_distribution(data: Iterable<Int>) : DistributionResult
}
