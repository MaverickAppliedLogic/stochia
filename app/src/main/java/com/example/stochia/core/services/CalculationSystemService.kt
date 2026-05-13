package com.example.stochia.core.services

import android.util.Log
import com.example.stochia.data.calculation_system.`interface`.EngineServiceRepository
import com.example.stochia.domain.model.engineMode.EngineMode
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
        val params = data.params.filterNotNull().toDoubleArray()
        val distribution = data.distribution.lowercase()
        val size = data.size
        return when (engineMode) {
            EngineMode.LOCAL -> {
                Log.i(TAG, "genMontecarlo → LOCAL")
                localRepository.genMontecarlo(distribution, params, size)
            }
            EngineMode.REMOTE -> {
                Log.i(TAG, "genMontecarlo → REMOTE")
                remoteRepository.genMontecarlo(distribution, params, size)
            }
            EngineMode.DYNAMIC -> try {
                Log.i(TAG, "genMontecarlo → REMOTE (dynamic)")
                remoteRepository.genMontecarlo(distribution, params, size)
            } catch (e: Exception) {
                Log.w(TAG, "genMontecarlo → LOCAL (fallback, remote failed: ${e.message})")
                localRepository.genMontecarlo(distribution, params, size)
            }
        }
    }

    suspend fun genMarkov(data: MarkovParams): MarkovResult {
        val states = intArrayOf(0, 1, 2)
        val probs = data.probs.toDoubleArray()
        return when (engineMode) {
            EngineMode.LOCAL -> {
                Log.i(TAG, "genMarkov → LOCAL")
                localRepository.genMarkov(states, probs, data.initState, data.steps)
            }
            EngineMode.REMOTE -> {
                Log.i(TAG, "genMarkov → REMOTE")
                remoteRepository.genMarkov(states, probs, data.initState, data.steps)
            }
            EngineMode.DYNAMIC -> try {
                Log.i(TAG, "genMarkov → REMOTE (dynamic)")
                remoteRepository.genMarkov(states, probs, data.initState, data.steps)
            } catch (e: Exception) {
                Log.w(TAG, "genMarkov → LOCAL (fallback, remote failed: ${e.message})")
                localRepository.genMarkov(states, probs, data.initState, data.steps)
            }
        }
    }

    suspend fun getDistribution(data: DistributionParams): DistributionResult {
        return when (engineMode) {
            EngineMode.LOCAL -> {
                Log.i(TAG, "getDistribution → LOCAL")
                localRepository.getDistribution(data.data.toDoubleArray())
            }
            EngineMode.REMOTE -> {
                Log.i(TAG, "getDistribution → REMOTE")
                remoteRepository.getDistribution(data.data.toDoubleArray())
            }
            EngineMode.DYNAMIC -> try {
                Log.i(TAG, "getDistribution → REMOTE (dynamic)")
                remoteRepository.getDistribution(data.data.toDoubleArray())
            } catch (e: Exception) {
                Log.w(TAG, "getDistribution → LOCAL (fallback, remote failed: ${e.message})")
                localRepository.getDistribution(data.data.toDoubleArray())
            }
        }
    }

    companion object {
        private const val TAG = "CalcEngine"
    }
}
