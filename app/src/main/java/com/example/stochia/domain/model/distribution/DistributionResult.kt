package com.example.stochia.domain.model.distribution

import com.chaquo.python.PyObject

data class DistributionResult(
    val frequencies: List<Double>,
    val probabilities: List<Double>,
    val mean: Double,
    val stdDev: Double,
    val p5: Double,
    val p95: Double,
    val min: Double,
    val max: Double,
    val total: Double
)

fun PyObject.toDistributionResult(): DistributionResult {
    val mapPy = asMap().mapKeys { it.key.toString() }

    // FREQUENCIES
    val frequencies = mapPy["frequencies"]!!
        .asMap()
        .map { (_, v) ->  v.toDouble() }
        .toList()

    // PROBABILITIES
    val probabilities = mapPy["probabilities"]!!
        .asMap()
        .map { (k, v) -> k.toInt() to v.toDouble() }
        .toList()
        .map { it.second }

    // STATISTICS
    val mean = mapPy["mean"]!!.toDouble()
    val stdDev = mapPy["std_dev"]!!.toDouble()
    val p5 = mapPy["p5"]!!.toDouble()
    val p95 = mapPy["p95"]!!.toDouble()
    val min = mapPy["min"]!!.toDouble()
    val max = mapPy["max"]!!.toDouble()
    val total = mapPy["total"]!!.toDouble()

    return DistributionResult(
        frequencies = frequencies,
        probabilities = probabilities,
        mean = mean,
        stdDev = stdDev,
        p5 = p5,
        p95 = p95,
        min = min,
        max = max,
        total = total
    )
}
