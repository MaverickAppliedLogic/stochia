package com.example.stochia.domain.model.markov

data class MarkovParams (
    val states: List<Int> = emptyList(),
    val probs: List<Double> = emptyList(),
    val init_state: Int = 0,
    val steps: Int = 0
)