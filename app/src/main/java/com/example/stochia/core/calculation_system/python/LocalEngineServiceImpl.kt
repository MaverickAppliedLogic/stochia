package com.example.stochia.core.calculation_system.python

import com.chaquo.python.Python
import com.example.stochia.core.calculation_system.`interface`.EngineService
import com.example.stochia.domain.model.distribution.DistributionParams
import com.example.stochia.domain.model.distribution.DistributionResult
import com.example.stochia.domain.model.distribution.toDomain

object LocalEngineServiceImpl : EngineService {
    val py = Python.getInstance()

    override fun gen_montecarlo() {
        TODO("Not yet implemented")
    }

    override fun gen_markov() {
        TODO("Not yet implemented")
    }

    override fun get_distribution(params: DistributionParams): DistributionResult {
        val dataPy = params.data

        val result = py
            .getModule("engine.distribution")
            .callAttr("get_distribution", dataPy)

        return result.toDomain(result)

    }



}