package com.example.stochia.core.services.calculation_system.`interface`

import com.chaquo.python.PyObject

interface EngineService {

    fun genMontecarlo(distribution: String, params: DoubleArray, size: Int): PyObject

    fun genMarkov(states: IntArray, probs: DoubleArray, initState: Int, steps: Int): PyObject

    fun getDistribution(params: IntArray): PyObject
}
