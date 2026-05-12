package com.example.stochia.domain.model.markov

import com.chaquo.python.PyObject
import com.example.stochia.domain.model.interfaces.Result
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.math.BigDecimal
import java.math.RoundingMode

class MarkovResult(
    val path: List<String>,
    val probs: Map<String, List<Double>>,
    val conv: List<Double>
): Result {
    override fun toString(): String {
        return path.joinToString(separator = " -> ") + probs
    }
}

private fun buildMarkovResult(
    pathInts: List<Int>,
    probsRaw: Map<String, List<Double>>,   // claves "0","1","2"
    convRaw: List<Double>
): MarkovResult {
    fun stateLabel(key: String) = when (key) { "0" -> "A"; "1" -> "B"; else -> "C" }

    val path  = pathInts.map { stateLabel(it.toString()) }
    val probs = probsRaw.entries.associate { (k, v) -> stateLabel(k) to v }
    val conv  = convRaw.map { BigDecimal(it).setScale(2, RoundingMode.HALF_UP).toDouble() }

    return MarkovResult(path = path, probs = probs, conv = conv)
}

fun PyObject.toMarkovResult(): MarkovResult {
    val mapPy    = asMap().mapKeys { it.key.toString() }
    val pathInts = mapPy["path"]!!.asList().map { it.toInt() }
    val probsRaw = mapPy["probs"]!!.asMap().entries.associate { (k, v) ->
        k.toString() to (v as PyObject).asMap().values
            .map { (it as PyObject).toJava(Number::class.java).toDouble() }
    }
    val convRaw  = mapPy["conv"]!!.asList().map { it.toString().toDouble() }
    return buildMarkovResult(pathInts, probsRaw, convRaw)
}

fun JsonObject.toMarkovResult(): MarkovResult {
    val pathInts = this["path"]!!.jsonArray.map { it.jsonPrimitive.int }
    val probsRaw = this["probs"]!!.jsonObject.entries.associate { (k, v) ->
        k to v.jsonObject.values.map { it.jsonPrimitive.double }
    }
    val convRaw  = this["conv"]!!.jsonArray.map { it.jsonPrimitive.double }
    return buildMarkovResult(pathInts, probsRaw, convRaw)
}

