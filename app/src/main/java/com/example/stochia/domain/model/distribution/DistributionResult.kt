package com.example.stochia.domain.model.distribution

import com.chaquo.python.PyObject
import com.example.stochia.domain.model.interfaces.Result
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

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


private fun buildDistributionResult(
    frequencies: Map<String, String>,
    probabilities: Map<String, Double>,
    mean: Double, stdDev: Double,
    p5: Double, p95: Double,
    min: Double, max: Double, total: Double
): DistributionResult = DistributionResult(
    frequencies   = frequencies,
    probabilities = probabilities.mapValues { (_, v) -> (v * 100).toString() },
    mean          = mean,
    stdDev        = stdDev,
    p5            = p5,
    p95           = p95,
    min           = min,
    max           = max,
    total         = total
)

fun PyObject.toDistributionResult(): DistributionResult {
    val mapPy = asMap().mapKeys { it.key.toString() }
    return buildDistributionResult(
        frequencies   = mapPy["frequencies"]!!.asMap().map { (key, value) -> key.toString() to value.toString() }.toMap(),
        probabilities = mapPy["probabilities"]!!.asMap().map { (key, value) -> key.toString() to value.toDouble() }.toMap(),
        mean          = mapPy["mean"]!!.toDouble(),
        stdDev        = mapPy["std_dev"]!!.toDouble(),
        p5            = mapPy["p5"]!!.toDouble(),
        p95           = mapPy["p95"]!!.toDouble(),
        min           = mapPy["min"]!!.toDouble(),
        max           = mapPy["max"]!!.toDouble(),
        total         = mapPy["total"]!!.toDouble()
    )
}

fun JsonObject.toDistributionResult(): DistributionResult {
    return buildDistributionResult(
        frequencies   = this["frequencies"]!!.jsonObject
            .map { (key, value) -> key to value.jsonPrimitive.content }
            .toMap(),
        probabilities = this["probabilities"]!!.jsonObject
            .map { (key, value) -> key to value.jsonPrimitive.double }
            .toMap(),
        mean          = this["mean"]!!.jsonPrimitive.double,
        stdDev        = this["std_dev"]!!.jsonPrimitive.double,
        p5            = this["p5"]!!.jsonPrimitive.double,
        p95           = this["p95"]!!.jsonPrimitive.double,
        min           = this["min"]!!.jsonPrimitive.double,
        max           = this["max"]!!.jsonPrimitive.double,
        total         = this["total"]!!.jsonPrimitive.double
    )
}
