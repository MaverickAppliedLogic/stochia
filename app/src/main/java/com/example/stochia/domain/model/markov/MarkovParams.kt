package com.example.stochia.domain.model.markov

import com.example.stochia.domain.model.interfaces.Params

data class MarkovParams (
    val states: List<String> = listOf("A", "B", "C"),
    val probs: List<Double> = listOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
    val initState: Int = 0,
    val steps: Int = 0
): Params