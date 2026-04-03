package com.example.stochia.domain.model.markov

class MarkovParams (
    val states: List<Int>,
    val probs: List<Double>,
    val init_state: Int,
    val steps: Int
)