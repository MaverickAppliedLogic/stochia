package com.example.stochia.domain.model.montecarlo

import com.chaquo.python.PyObject

data class MontecarloResult(
    val distribution: MontecarloType?,
    val values: List<Double>?,
    val mean: Double?,
    val stdDev: Double?,
    val p5: Double?,
    val p95: Double?,
    val tries: Int?,
    val results: List<MontecarloResult>?
)

fun PyObject.toMontecarloResult(result: PyObject): MontecarloResult {
    val mapPy = result.asMap().mapKeys { it.key.toString() }
    when(mapPy["distribution"]!!.toString()) {
        "bernoulli" -> return MontecarloResultFactory.fromBernoulli(mapPy)
        "binomial" -> TODO()
        "geometrical" -> TODO()
        "poisson" -> TODO()
        "multinomial" -> TODO()
        else -> TODO()
    }
}