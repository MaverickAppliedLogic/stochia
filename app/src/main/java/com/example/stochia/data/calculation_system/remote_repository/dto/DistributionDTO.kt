package com.example.stochia.data.calculation_system.remote_repository.dto

import kotlinx.serialization.Serializable

@Serializable
data class DistributionDTO(
    val data : List<Double>
)