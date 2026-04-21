package com.example.stochia.domain.model.markov

import com.chaquo.python.PyObject
import com.example.stochia.domain.model.result.Result

class MarkovResult(
    val path: List<String>
): Result {
    override fun toString(): String {
        return path.joinToString(separator = " -> ")
    }
}

fun PyObject.toMarkovResult(): MarkovResult {
    val result = asList().map { it.toInt() }
    val path = result.let {
        it.map { value ->
            when (value) {
                0 -> "A"
                1 -> "B"
                else -> "C"
            }
        }
    }
    return MarkovResult(path = path)
}



