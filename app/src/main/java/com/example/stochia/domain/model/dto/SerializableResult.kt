package com.example.stochia.domain.model.dto
import kotlinx.serialization.Serializable
import com.example.stochia.domain.model.interfaces.Result
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.domain.model.montecarlo.MontecarloType
import com.example.stochia.domain.model.distribution.DistributionResult



@Serializable
sealed class SerializableResult {
    abstract fun toDomain(): Result
}


@Serializable
data class SerializableMarkovResult(
    val path: List<String>,
    val probs: Map<String, List<Double>>,
    val conv: List<Double>
) : SerializableResult() {

    override fun toDomain(): Result =
        MarkovResult(
            path = path,
            probs = probs,
            conv = conv
        )
}

@Serializable
data class SerializableMontecarloResult(
    val distribution: MontecarloType?,
    val values: List<Double>?,
    val mean: Double?,
    val stdDev: Double?,
    val p5: Double?,
    val p50: Double?,
    val p95: Double?,
    val tries: Int?,
    val results: List<SerializableMontecarloResult>?
) : SerializableResult() {

    override fun toDomain(): Result =
        MontecarloResult(
            distribution = distribution,
            values = values,
            mean = mean,
            stdDev = stdDev,
            p5 = p5,
            p50 = p50,
            p95 = p95,
            tries = tries,
            results = results?.map { it.toDomain() as MontecarloResult }
        )
}


@Serializable
data class SerializableDistributionResult(
    val frequencies: Map<String, String>,
    val probabilities: Map<String, String>,
    val mean: Double,
    val stdDev: Double,
    val p5: Double,
    val p95: Double,
    val min: Double,
    val max: Double,
    val total: Double
) : SerializableResult() {

    override fun toDomain(): Result =
        DistributionResult(
            frequencies = frequencies,
            probabilities = probabilities,
            mean = mean,
            stdDev = stdDev,
            p5 = p5,
            p95 = p95,
            min = min,
            max = max,
            total = total
        )
}


fun Result.toSerializable(): SerializableResult =
    when (this) {
        is MarkovResult -> SerializableMarkovResult(
            path = path,
            probs = probs,
            conv = conv
        )

        is MontecarloResult -> SerializableMontecarloResult(
            distribution = distribution,
            values = values,
            mean = mean,
            stdDev = stdDev,
            p5 = p5,
            p50 = p50,
            p95 = p95,
            tries = tries,
            results = results?.map { it.toSerializable() as SerializableMontecarloResult }
        )

        is DistributionResult -> SerializableDistributionResult(
            frequencies = frequencies,
            probabilities = probabilities,
            mean = mean,
            stdDev = stdDev,
            p5 = p5,
            p95 = p95,
            min = min,
            max = max,
            total = total
        )

        else -> error("Unknown Result type: $this")
    }


