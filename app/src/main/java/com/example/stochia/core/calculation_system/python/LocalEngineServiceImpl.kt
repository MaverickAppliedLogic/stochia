package com.example.stochia.core.calculation_system.python

import com.chaquo.python.Python
import com.example.stochia.core.calculation_system.`interface`.EngineService
import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.distribution.toDistributionResult
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.markov.toMarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.domain.model.montecarlo.toMontecarloResult
import kotlin.collections.toDoubleArray

object LocalEngineServiceImpl : EngineService {
    val py = Python.getInstance()

    override fun gen_montecarlo(data: MontecarloParams): MontecarloResult {
        val params = data.params.filterNotNull().toDoubleArray()
        val size = data.size
        val distribution = data.distribution

        val result = py
            .getModule("engine.montecarlo.montecarlo_gen")
            .callAttr("generate_sim_montecarlo", distribution, params, size)

        return result.toMontecarloResult()

    }

    override fun gen_markov(data: MarkovParams): MarkovResult {
        val states = data.states.toIntArray()
        val probs = data.probs.toDoubleArray()

        val result = py
            .getModule("engine.markov.markov")
            .callAttr(
                "gen_sim_markov",
                states, probs, data.init_state, data.steps
            )
        return result.toMarkovResult()

    }

    override fun get_distribution(params: DistributionParams): DistributionResult {
        val dataPy = params.data

        val result = py
            .getModule("engine.distribution")
            .callAttr("get_distribution", dataPy)

        return result.toDistributionResult()

    }



}