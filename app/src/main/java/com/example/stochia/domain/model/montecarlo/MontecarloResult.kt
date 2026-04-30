package com.example.stochia.domain.model.montecarlo

import com.chaquo.python.PyObject
import com.example.stochia.domain.model.result.Result

class MontecarloResult(
    val distribution: MontecarloType?,
    val values: List<Double>?,
    val mean: Double?,
    val stdDev: Double?,
    val p5: Double?,
    val p50: Double? = null,
    val p95: Double?,
    val tries: Int?,
    val results: List<MontecarloResult>?
): Result {
    override fun toString(): String {
        return "Distribution: $distribution\n" +
                "Values: $values\n" +
                "Mean: $mean\n" +
                "Standard Deviation: $stdDev\n" +
                "P5: $p5\n" +
                "P95: $p95\n" +
                "Tries: $tries\n" +
                "Results: $results"
    }
}

fun PyObject.toMontecarloResult(): MontecarloResult{
    val mapPy = asMap().mapKeys { it.key.toString() }
     return when (mapPy["distribution"]!!.toString()) {
        "multinomial" -> MontecarloResultFactory.fromMultinomial(mapPy)
        "geometrical" -> MontecarloResultFactory.fromGeometrical(mapPy)
        "binomial" -> MontecarloResultFactory.withoutValues(mapPy)
        "poisson" -> MontecarloResultFactory.withoutValues(mapPy)
        else -> MontecarloResultFactory.withValues(mapPy)
    }
}