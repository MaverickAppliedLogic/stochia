package com.example.stochia.domain.model.distribution

data class DistributionParams(
    val data: List<Double> = emptyList()
)

fun List<Double>.toPy() = DistributionParams(data = this.toList())

