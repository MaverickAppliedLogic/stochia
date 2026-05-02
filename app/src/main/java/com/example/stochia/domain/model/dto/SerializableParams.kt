package com.example.stochia.domain.model.dto

import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.interfaces.Params
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.montecarlo.MontecarloParams
import kotlinx.serialization.Serializable

@Serializable
sealed class SerializableParams {
    abstract fun toDomain(): Params
}


@Serializable
class SerializableMontecarloParams(
    val distribution: String,
    val params: List<Double?>,
    val size: Int
): SerializableParams() {
    override fun toDomain() = MontecarloParams(distribution, params, size)
}

@Serializable
data class SerializableDistributionParams(
    val data: List<Double>
) : SerializableParams() {
    override fun toDomain() = DistributionParams(data)
}


@Serializable
data class SerializableMarkovParams(
    val states: List<String>,
    val probs: List<Double>,
    val initState: Int,
    val steps: Int
) : SerializableParams() {
    override fun toDomain() = MarkovParams(states, probs, initState, steps)
}

fun Params.toSerializable(): SerializableParams =
    when (this) {
        is MarkovParams -> SerializableMarkovParams(states, probs, initState, steps)
        is DistributionParams -> SerializableDistributionParams(data)
        is MontecarloParams -> SerializableMontecarloParams(distribution, params, size)
        else -> throw IllegalArgumentException("Unknown Params type")
    }