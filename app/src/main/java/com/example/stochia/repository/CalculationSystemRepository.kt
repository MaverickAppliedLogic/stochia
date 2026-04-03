package com.example.stochia.repository

import com.chaquo.python.PyObject
import com.example.stochia.core.services.calculation_system.local.LocalEngineServiceImpl

class CalculationSystemRepository(private val engineService: LocalEngineServiceImpl) {

    fun getDistribution(data: IntArray): PyObject {
        return engineService.getDistribution(data)
    }

    fun genMontecarlo(distribution: String, params: DoubleArray, size: Int): PyObject {
        return engineService.genMontecarlo(distribution, params, size)
    }

    fun genMarkov(states: IntArray, probs: DoubleArray, initState: Int, steps: Int): PyObject {
        return engineService.genMarkov(states, probs, initState, steps)
    }

}
