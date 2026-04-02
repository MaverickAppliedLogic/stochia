package com.example.stochia.domain.model.montecarlo

import com.chaquo.python.PyObject

object MontecarloResultFactory {

    fun fromBernoulli(mapPy: Map<String, PyObject>): MontecarloResult {

        val resList = mapPy["res"]!!.asList()

        val results = resList.map { pyObj ->
            val entry = pyObj.asMap().mapKeys { it.key.toString() }
            MontecarloResult(
                distribution = MontecarloType.BERNOULLI,
                values = null,
                mean = entry["mean"]!!.toDouble(),
                stdDev = entry["std"]!!.toDouble(),
                p5 = null,
                p95 = null,
                tries = entry["tries"]!!.toDouble().toInt(),
                results = null
            )
        }
        return MontecarloResult(
            distribution = MontecarloType.BERNOULLI,
            values = null,
            mean = null,
            stdDev = null,
            p5 = null,
            p95 = null,
            tries = null,
            results = results
        )
    }
}
