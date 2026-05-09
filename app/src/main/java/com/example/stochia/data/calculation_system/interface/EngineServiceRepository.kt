package com.example.stochia.data.calculation_system.`interface`

import com.chaquo.python.PyObject

interface EngineServiceRepository {

    fun genMontecarlo(distribution: String, params: DoubleArray, size: Int): PyObject

    fun genMarkov(states: IntArray, probs: DoubleArray, initState: Int, steps: Int): PyObject

    fun getDistribution(params: DoubleArray): PyObject
}
