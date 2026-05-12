package com.example.stochia.domain.model.markov

import com.chaquo.python.PyObject
import com.example.stochia.domain.model.interfaces.Result
import kotlinx.serialization.json.JsonArray
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

fun PyObject.toMarkovResult(): MarkovResult {
    val mapPy = asMap().mapKeys { it.key.toString() }

    val flatPath = mapPy["path"]!!.asList().map { it.toInt() }
    val path = flatPath.let {
        it.map { value ->
            when (value) {
                0 -> "A"
                1 -> "B"
                else -> "C"
            }
        }
    }

    val probsPy = mapPy["probs"]!!.asMap()
    val probs: Map<String, List<Double>> = probsPy.map { (keyPy, valuePy) ->

            val key = when(keyPy.toString()){
                "0" -> "A"
                "1" -> "B"
                else -> "C"
            }

            val values = (valuePy as PyObject)
                .asMap()
                .values
                .map { v ->
                    (v as PyObject)
                        .toJava(Number::class.java)
                        .toDouble()
                }
            key to values
        }.toMap()

    val conv = mapPy["conv"]!!.asList().map { BigDecimal(it.toString())
        .setScale(2, RoundingMode.HALF_UP).toDouble()  }


    return MarkovResult(path = path, probs = probs, conv = conv)
}

fun JsonObject.toMarkovResult(): MarkovResult {
    // path: array of ints  →  map 0→A, 1→B, 2→C
    val path = this["path"]!!.jsonArray.map { element ->
        when (element.jsonPrimitive.int) {
            0    -> "A"
            1    -> "B"
            else -> "C"
        }
    }

    // probs: object whose keys are "0","1","2" and values are objects of doubles
    val probs: Map<String, List<Double>> = this["probs"]!!.jsonObject.entries.associate { (stateKey, stateVal) ->
        val label = when (stateKey) {
            "0"  -> "A"
            "1"  -> "B"
            else -> "C"
        }
        val values = stateVal.jsonObject.values.map { it.jsonPrimitive.double }
        label to values
    }

    // conv: array of doubles rounded to 2 decimals
    val conv = this["conv"]!!.jsonArray.map {
        BigDecimal(it.jsonPrimitive.double).setScale(2, RoundingMode.HALF_UP).toDouble()
    }

    return MarkovResult(path = path, probs = probs, conv = conv)
}

