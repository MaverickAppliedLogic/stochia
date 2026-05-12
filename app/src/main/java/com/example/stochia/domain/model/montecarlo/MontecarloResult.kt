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

private fun buildMontecarloResult(map: Map<String, Any?>): MontecarloResult =
    when (map["distribution"]!!.toString()) {
        "multinomial" -> MontecarloResultFactory.fromMultinomial(map)
        "geometrical" -> MontecarloResultFactory.fromGeometrical(map)
        "binomial"    -> MontecarloResultFactory.withoutValues(map)
        "poisson"     -> MontecarloResultFactory.withoutValues(map)
        else          -> MontecarloResultFactory.withValues(map)
    }

fun PyObject.toMontecarloResult(): MontecarloResult {
    val map = asMap().mapKeys { it.key.toString() }.mapValues { (_, value) ->
        val pyVal = value as PyObject
        runCatching { pyVal.asList().map { (it as PyObject).toDouble() } }.getOrNull()
            ?: runCatching { pyVal.toDouble() }.getOrNull()
            ?: runCatching { pyVal.toInt() }.getOrNull()
            ?: pyVal.toString()
    }
    return buildMontecarloResult(map)
}

fun JsonObject.toMontecarloResult(): MontecarloResult {
    val map = entries.associate { (key, value) ->
        key to when (value) {
            is JsonArray -> value.map { it.jsonPrimitive.double }
            is JsonPrimitive -> runCatching { value.int }.getOrNull()
                ?: runCatching { value.double }.getOrNull()
                ?: value.content

            else -> null
        }
    }
    return buildMontecarloResult(map)
}