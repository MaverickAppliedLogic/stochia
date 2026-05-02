package com.example.stochia.domain.model.montecarlo

import com.example.stochia.domain.model.interfaces.Params
import kotlinx.serialization.Serializable

@Serializable
class MontecarloParams(
    val distribution: String,
    val params: List<Double?>,
    val size: Int
): Params

