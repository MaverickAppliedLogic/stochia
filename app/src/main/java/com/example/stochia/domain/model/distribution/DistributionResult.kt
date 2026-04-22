package com.example.stochia.domain.model.distribution

import com.chaquo.python.PyObject
import com.example.stochia.domain.model.result.Result

class DistributionResult(
    val frequencies: Map<String, String>,
    val probabilities: Map<String, String>,
    val mean: Double,
    val stdDev: Double,
    val p5: Double,
    val p95: Double,
    val min: Double,
    val max: Double,
    val total: Double
): Result {
    override fun toString(): String {
        return "Frequencies: $frequencies\n" +
                "Probabilities: $probabilities\n"+
                "Mean: $mean\n" +
                "Standard Deviation: $stdDev\n" +
                "P5: $p5\n" +
                "P95: $p95\n" +
                "Min: $min\n" +
                "Max: $max\n" +
                "Total: $total"
    }
}


fun PyObject.toDistributionResult(): DistributionResult {
    val mapPy = asMap().mapKeys { it.key.toString() }

    // FREQUENCIES
    val frequencies = mapPy["frequencies"]!!
        .asMap()
        .map { (k, v) -> k.toString() to  v.toString() }
        .toMap()

    // PROBABILITIES
    val probabilities = mapPy["probabilities"]!!
        .asMap()
        .map { (k, v) -> k.toString() to v.toString() }
        .toMap()

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
