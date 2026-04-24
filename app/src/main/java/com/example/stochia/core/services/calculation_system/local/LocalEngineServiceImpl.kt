package com.example.stochia.core.services.calculation_system.local

import android.util.Log
import com.chaquo.python.PyObject
import com.chaquo.python.Python
import com.example.stochia.core.services.calculation_system.`interface`.EngineService
class LocalEngineServiceImpl: EngineService {
    val py = Python.getInstance()

    override fun genMontecarlo(distribution: String, params: DoubleArray, size: Int): PyObject {
        Log.d("LocalEngineServiceImpl", "genMontecarlo: $distribution, $params, $size")
        val result = py
            .getModule("engine.montecarlo.montecarlo_gen")
            .callAttr("generate_sim_montecarlo", distribution, params, size)
        Log.d("LocalEngineServiceImpl", "genMontecarlo: $result")
        return result

    }

    override fun genMarkov(
        states: IntArray, probs: DoubleArray, initState: Int, steps: Int
    ): PyObject {

        val result = py
            .getModule("engine.markov.markov")
            .callAttr(
                "gen_sim_markov",
                states, probs, initState, steps
            )
        return result
    }

    override fun getDistribution(params: DoubleArray): PyObject {

        val result = py
            .getModule("engine.distribution.distribution")
            .callAttr("get_distribution", params)

        return result

    }



}