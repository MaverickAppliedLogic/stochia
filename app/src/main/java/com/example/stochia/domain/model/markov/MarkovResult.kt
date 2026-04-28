package com.example.stochia.domain.model.markov

import com.chaquo.python.PyObject
import com.example.stochia.domain.model.result.Result
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



