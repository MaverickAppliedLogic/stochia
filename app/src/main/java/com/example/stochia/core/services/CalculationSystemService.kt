package com.example.stochia.core.services

import android.util.Log
import com.example.stochia.data.calculation_system.`interface`.EngineServiceRepository
import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.model.montecarlo.MontecarloResult

class CalculationSystemService(
    private val localRepository: EngineServiceRepository
) {

    suspend fun genMontecarlo(data: MontecarloParams): MontecarloResult {
        Log.d("CalculationSystemService", "genMontecarlo: ${data.params}")
        val params = data.params.filterNotNull().toDoubleArray()
        val distribution = data.distribution.lowercase()
        return localRepository.genMontecarlo(distribution, params, data.size)
    }

    suspend fun genMarkov(data: MarkovParams): MarkovResult {
        val states = intArrayOf(0, 1, 2)
        val probs = data.probs.toDoubleArray()
        return localRepository.genMarkov(states, probs, data.initState, data.steps)
    }

    suspend fun getDistribution(data: DistributionParams): DistributionResult {
        return localRepository.getDistribution(data.data.toDoubleArray())
    }
}
