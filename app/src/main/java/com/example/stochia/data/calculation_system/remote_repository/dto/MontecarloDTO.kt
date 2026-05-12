package com.example.stochia.data.calculation_system.remote_repository.dto

import kotlinx.serialization.Serializable

@Serializable
data class MontecarloDTO(
    val distribution: String,
    val params: DoubleArray,
    val size: Int
)
