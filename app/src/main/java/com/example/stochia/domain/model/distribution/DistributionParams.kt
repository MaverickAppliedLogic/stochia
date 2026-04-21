package com.example.stochia.domain.model.distribution

data class DistributionParams(
    val data: List<Int> = emptyList()
)

fun List<Int>.toPy() = DistributionParams(data = this.toList())

