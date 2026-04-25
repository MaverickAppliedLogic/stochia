package com.example.stochia.domain.model.markov

import com.chaquo.python.PyObject
import com.example.stochia.domain.model.result.Result

class MarkovResult(
    val path: List<String>,
    val probs: List<Double>
): Result {
    override fun toString(): String {
        return path.joinToString(separator = " -> ") + probs.joinToString(separator = " ")
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
    val probs = mapPy["probs"]!!.asList().map { it.toDouble() }
    return MarkovResult(path = path, probs = probs)
}



