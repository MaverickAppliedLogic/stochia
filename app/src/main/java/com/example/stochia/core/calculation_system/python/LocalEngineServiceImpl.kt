package com.example.stochia.core.calculation_system.python

import com.chaquo.python.Python
import com.example.stochia.core.calculation_system.`interface`.EngineService

class LocalEngineServiceImpl : EngineService {
    val py = Python.getInstance()
    val module = py.getModule("engine")

    override fun gen_montecarlo() {
        TODO("Not yet implemented")
    }

    override fun gen_markov() {
        TODO("Not yet implemented")
    }

    override fun get_distribution(): Map<String, Any> {
        val result= module.callAttr("get_distribution")
        val map: Map<String, Any> = result

        return mapOf(
            "frecuencias" to map["frecuencias"]!!,
            "probabilidades" to map["probabilidades"]!!,
            "estadisticas" to map["estadisticas"]!!,
            "total_datos" to map["total_datos"]!!
        )

    }


}