package com.example.stochia.core.services

import android.util.Log
import com.example.stochia.data.calculation_system.`interface`.EngineServiceRepository
import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.distribution.toDistributionResult
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.markov.toMarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.domain.model.montecarlo.toMontecarloResult

class CalculationSystemService(
    private val localRepository: EngineServiceRepository
) {

    fun genMontecarlo(data: MontecarloParams): MontecarloResult {
        Log.d("CalculationSystemService", "genMontecarlo: ${data.params}")
        val params = data.params.filterNotNull().toDoubleArray()
        val distribution = data.distribution.lowercase()
        return localRepository.genMontecarlo(distribution, params, data.size).toMontecarloResult()
    }

    fun genMarkov(data: MarkovParams): MarkovResult {
        val mapStates = mapOf(
            data.states[0] to 0,
            data.states[1] to 1,
            data.states[2] to 2
        )
        val states = mapStates.values.toIntArray()
        val probs = data.probs.toDoubleArray()
        return localRepository.genMarkov(states, probs, data.initState, data.steps).toMarkovResult()
    }

    fun getDistribution(data: DistributionParams): DistributionResult {
        return localRepository.getDistribution(data.data.toDoubleArray()).toDistributionResult()
    }
}
