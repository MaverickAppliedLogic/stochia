package com.example.stochia.domain.model.distribution

import com.example.stochia.domain.model.interfaces.Params
import kotlinx.serialization.Serializable

@Serializable
data class DistributionParams(
    val data: List<Double> = emptyList()
): Params

fun List<Double>.toPy() = DistributionParams(data = this.toList())

