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
    private val localRepository: EngineServiceRepository,
    private val remoteRepository: EngineServiceRepository
)
{

    suspend fun genMontecarlo(data: MontecarloParams): MontecarloResult {
        Log.d("CalculationSystemService", "genMontecarlo: ${data.params}")
        val params = data.params.filterNotNull().toDoubleArray()
        val distribution = data.distribution.lowercase()
        val size = data.size
        return try {
            Log.d(
                "CalculationSystemService",
                "Is reacheable?: ${remoteRepository.IsReacheable()}"
            )
            remoteRepository.genMontecarlo(distribution,params,size)
        }catch (e: Exception){
            Log.e(
                "CalculationSystemService",
                "Remote genMarkov failed, falling back to local",
                e
            )
           localRepository.genMontecarlo(distribution,params,size)
        }
    }

    suspend fun genMarkov(data: MarkovParams): MarkovResult {
        val states = intArrayOf(0, 1, 2)
        val probs = data.probs.toDoubleArray()
        return try {
            Log.d(
                "CalculationSystemService",
                "Is reacheable?: ${remoteRepository.IsReacheable()}"
            )
            remoteRepository.genMarkov(states, probs, data.initState, data.steps)
        } catch (e: Exception) {
            Log.w(
                "CalculationSystemService",
                "Remote genMarkov failed, falling back to local",
                e
            )
           localRepository.genMarkov(states, probs, data.initState, data.steps)
        }
    }

    suspend fun getDistribution(data: DistributionParams): DistributionResult {
        return try {
            Log.d(
                "CalculationSystemService",
                "Is reacheable?: ${remoteRepository.IsReacheable()}"
            )
            remoteRepository.getDistribution(data.data.toDoubleArray())
        } catch (e: Exception) {
            Log.w(
                "CalculationSystemService",
                "Remote genMarkov failed, falling back to local",
                e
            )
            localRepository.getDistribution(data.data.toDoubleArray())
        }
    }
}
