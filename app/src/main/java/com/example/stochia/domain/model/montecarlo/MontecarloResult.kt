package com.example.stochia.domain.model.montecarlo

import com.chaquo.python.PyObject
import com.example.stochia.domain.model.interfaces.Result
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.double
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive

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

private fun PyObject.toGenericMap(): Map<String, Any?> =
    asMap().mapKeys { it.key.toString() }.mapValues { (_, v) ->
        val pyVal = v as PyObject
        runCatching { pyVal.asList().map { (it as PyObject).toDouble() } }.getOrNull()
            ?: runCatching { pyVal.toDouble() }.getOrNull()
            ?: runCatching { pyVal.toInt() }.getOrNull()
            ?: pyVal.toString()
    }

private fun JsonObject.toGenericMap(): Map<String, Any?> =
    entries.associate { (k, v) ->
        k to when {
            v is JsonArray     -> v.map { it.jsonPrimitive.double }
            v is JsonPrimitive -> runCatching { v.int }.getOrNull()
                ?: runCatching { v.double }.getOrNull()
                ?: v.content
            else               -> null
        }
    }

fun PyObject.toMontecarloResult(): MontecarloResult {
    val map = toGenericMap()
    return when (map["distribution"]!!.toString()) {
        "multinomial" -> MontecarloResultFactory.fromMultinomial(map)
        "geometrical" -> MontecarloResultFactory.fromGeometrical(map)
        "binomial"    -> MontecarloResultFactory.withoutValues(map)
        "poisson"     -> MontecarloResultFactory.withoutValues(map)
        else          -> MontecarloResultFactory.withValues(map)
    }
}

fun JsonObject.toMontecarloResult(): MontecarloResult {
    val map = toGenericMap()
    return when (map["distribution"]!!.toString()) {
        "multinomial" -> MontecarloResultFactory.fromMultinomial(map)
        "geometrical" -> MontecarloResultFactory.fromGeometrical(map)
        "binomial"    -> MontecarloResultFactory.withoutValues(map)
        "poisson"     -> MontecarloResultFactory.withoutValues(map)
        else          -> MontecarloResultFactory.withValues(map)
    }
}