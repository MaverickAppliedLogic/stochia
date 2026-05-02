package com.example.stochia.domain.model.montecarlo

import com.example.stochia.domain.model.interfaces.Params

class MontecarloParams(
    val distribution: String,
    val params: List<Double?>,
    val size: Int
): Params
