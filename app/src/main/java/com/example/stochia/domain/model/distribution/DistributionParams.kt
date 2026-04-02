package com.example.stochia.domain.model.distribution

data class DistributionParams(
    val data: IntArray
)

fun List<Int>.toPy() = DistributionParams(data = this.toList().toIntArray())

