package com.example.stochia.domain.model.markov

import com.chaquo.python.PyObject

data class MarkovResult(
    val path: List<Int>
)

fun PyObject.toMarkovResult()=
    MarkovResult(path=asList().map { it.toInt() })



