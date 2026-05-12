package com.example.stochia.data.calculation_system.`interface`

import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult

interface EngineServiceRepository {

    suspend fun genMontecarlo(distribution: String, params: DoubleArray, size: Int): MontecarloResult

    suspend fun genMarkov(states: IntArray, probs: DoubleArray, initState: Int, steps: Int): MarkovResult

    suspend fun getDistribution(params: DoubleArray): DistributionResult

    suspend fun IsReacheable(): Boolean
}
