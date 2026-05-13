package com.example.stochia.data.calculation_system.local_repository

import android.util.Log
import com.chaquo.python.Python
import com.example.stochia.data.calculation_system.`interface`.EngineServiceRepository
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.distribution.toDistributionResult
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.markov.toMarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.domain.model.montecarlo.toMontecarloResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalEngineServiceRepositoryImpl : EngineServiceRepository {

    private val py = Python.getInstance()

    override suspend fun genMontecarlo(
        distribution: String, params: DoubleArray, size: Int
    ): MontecarloResult = withContext(Dispatchers.IO) {
        Log.i("CalcEngine", "[LOCAL] genMontecarlo executing")
        py.getModule("engine.montecarlo.montecarlo_gen")
            .callAttr("generate_sim_montecarlo", distribution, params, size)
            .toMontecarloResult()
    }

    override suspend fun genMarkov(
        states: IntArray, probs: DoubleArray, initState: Int, steps: Int
    ): MarkovResult = withContext(Dispatchers.IO) {
        Log.i("CalcEngine", "[LOCAL] genMarkov executing")
        py.getModule("engine.markov.markov")
            .callAttr("gen_sim_markov", states, probs, initState, steps)
            .toMarkovResult()
    }

    override suspend fun getDistribution(params: DoubleArray): DistributionResult =
        withContext(Dispatchers.IO) {
            Log.i("CalcEngine", "[LOCAL] getDistribution executing")
            py.getModule("engine.distribution.distribution")
                .callAttr("get_distribution", params)
                .toDistributionResult()
        }

    override suspend fun IsReacheable(): Boolean {
        return true
    }
}
