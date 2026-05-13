package com.example.stochia.core.services

import android.util.Log
import com.example.stochia.data.calculation_system.`interface`.EngineServiceRepository
import com.example.stochia.domain.model.EngineMode
import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.model.montecarlo.MontecarloResult

class CalculationSystemService(
    private val localRepository: EngineServiceRepository,
    private val remoteRepository: EngineServiceRepository
) {
    var engineMode: EngineMode = EngineMode.DYNAMIC

    suspend fun genMontecarlo(data: MontecarloParams): MontecarloResult {
        Log.d("CalculationSystemService", "genMontecarlo: ${data.params} mode=$engineMode")
        val params = data.params.filterNotNull().toDoubleArray()
        val distribution = data.distribution.lowercase()
        val size = data.size
        return when (engineMode) {
            EngineMode.LOCAL -> localRepository.genMontecarlo(distribution, params, size)
            EngineMode.REMOTE -> remoteRepository.genMontecarlo(distribution, params, size)
            EngineMode.DYNAMIC -> try {
                remoteRepository.genMontecarlo(distribution, params, size)
            } catch (e: Exception) {
                Log.e("CalculationSystemService", "Remote genMontecarlo failed, falling back to local", e)
                localRepository.genMontecarlo(distribution, params, size)
            }
        }
    }

    suspend fun genMarkov(data: MarkovParams): MarkovResult {
        Log.d("CalculationSystemService", "genMarkov mode=$engineMode")
        val states = intArrayOf(0, 1, 2)
        val probs = data.probs.toDoubleArray()
        return when (engineMode) {
            EngineMode.LOCAL -> localRepository.genMarkov(states, probs, data.initState, data.steps)
            EngineMode.REMOTE -> remoteRepository.genMarkov(states, probs, data.initState, data.steps)
            EngineMode.DYNAMIC -> try {
                remoteRepository.genMarkov(states, probs, data.initState, data.steps)
            } catch (e: Exception) {
                Log.w("CalculationSystemService", "Remote genMarkov failed, falling back to local", e)
                localRepository.genMarkov(states, probs, data.initState, data.steps)
            }
        }
    }

    suspend fun getDistribution(data: DistributionParams): DistributionResult {
        Log.d("CalculationSystemService", "getDistribution mode=$engineMode")
        return when (engineMode) {
            EngineMode.LOCAL -> localRepository.getDistribution(data.data.toDoubleArray())
            EngineMode.REMOTE -> remoteRepository.getDistribution(data.data.toDoubleArray())
            EngineMode.DYNAMIC -> try {
                remoteRepository.getDistribution(data.data.toDoubleArray())
            } catch (e: Exception) {
                Log.w("CalculationSystemService", "Remote getDistribution failed, falling back to local", e)
                localRepository.getDistribution(data.data.toDoubleArray())
            }
        }
    }
}
