package com.example.stochia.data.calculation_system.remote_repository.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarkovDTO (
    val states: List<Int>,
    val probs: List<Double>,
    @SerialName("init_state") val initState: Int,
    val steps: Int
)