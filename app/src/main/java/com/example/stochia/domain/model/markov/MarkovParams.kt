package com.example.stochia.domain.model.markov

data class MarkovParams (
    val states: List<String> = listOf("A", "B", "C"),
    val probs: List<Double> = listOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
    val init_state: Int = 0,
    val steps: Int = 0
)