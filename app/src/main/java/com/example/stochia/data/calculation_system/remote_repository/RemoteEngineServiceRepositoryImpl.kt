package com.example.stochia.data.calculation_system.remote_repository

import com.example.stochia.core.network.NetworkConfig
import com.example.stochia.data.calculation_system.`interface`.EngineServiceRepository
import com.example.stochia.data.calculation_system.remote_repository.dto.DistributionDTO
import com.example.stochia.data.calculation_system.remote_repository.dto.MarkovDTO
import com.example.stochia.data.calculation_system.remote_repository.dto.MontecarloDTO
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.distribution.toDistributionResult
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.markov.toMarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import com.example.stochia.domain.model.montecarlo.toMontecarloResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.JsonObject

class RemoteEngineServiceRepositoryImpl(
    private val httpClient: HttpClient
) : EngineServiceRepository {

    override suspend fun genMontecarlo(
        distribution: String,
        params: DoubleArray,
        size: Int
    ): MontecarloResult {
        val response: JsonObject = httpClient.post("${NetworkConfig.BASE_URL}montecarlo/generate") {
            contentType(ContentType.Application.Json)
            header("X-API-Key", NetworkConfig.CALC_SYS_API_KEY)
            setBody(MontecarloDTO(distribution = distribution, params = params, size = size))
        }.body()
        return response.toMontecarloResult()
    }

    override suspend fun genMarkov(
        states: IntArray,
        probs: DoubleArray,
        initState: Int,
        steps: Int
    ): MarkovResult {
        val response: JsonObject = httpClient.post("${NetworkConfig.BASE_URL}markov/generate") {
            contentType(ContentType.Application.Json)
            header("X-API-Key", NetworkConfig.CALC_SYS_API_KEY)
            setBody(MarkovDTO(states = states.toList(), probs = probs.toList(), initState = initState, steps = steps))
        }.body()
        return response.toMarkovResult()
    }

    override suspend fun getDistribution(params: DoubleArray): DistributionResult {
        val response: JsonObject = httpClient.post("${NetworkConfig.BASE_URL}distribution/get") {
            contentType(ContentType.Application.Json)
            header("X-API-Key", NetworkConfig.CALC_SYS_API_KEY)
            setBody(DistributionDTO(data = params.toList()))
        }.body()
        return response.toDistributionResult()
    }
}