package com.example.stochia.data.calculation_system.remote_repository

import com.example.stochia.data.calculation_system.`interface`.EngineServiceRepository
import com.example.stochia.data.calculation_system.remote_repository.dto.MontecarloDTO
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.montecarlo.MontecarloResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.contentType
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

class RemoteEngineServiceRepositoryImpl(
    private val httpClient: HttpClient
): EngineServiceRepository {
    override suspend fun genMontecarlo(
        distribution: String,
        params: DoubleArray,
        size: Int
    ): MontecarloResult {
        TODO()
    }

    override suspend fun genMarkov(
        states: IntArray,
        probs: DoubleArray,
        initState: Int,
        steps: Int
    ): MarkovResult {
        TODO("Not yet implemented")
    }

    override suspend fun getDistribution(params: DoubleArray): DistributionResult {
        TODO("Not yet implemented")
    }

}