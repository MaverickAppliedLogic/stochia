package com.example.stochia.core.calculation_system.python

import com.chaquo.python.Python
import com.example.stochia.core.calculation_system.`interface`.EngineService

object LocalEngineServiceImpl : EngineService {
    val py = Python.getInstance()

    override fun gen_montecarlo() {
        TODO("Not yet implemented")
    }

    override fun gen_markov() {
        TODO("Not yet implemented")
    }

    override fun get_distribution(data: Iterable<Int>): Map<String, List<Double>> {
        val dataPy = data.toList().toIntArray()

        val result = py
            .getModule("engine.distribution")
            .callAttr("get_distribution", dataPy)

        // Convertir dict principal
        val mapPy = result.asMap().mapKeys { it.key.toString() }

        // FRECUENCIAS
        val frecuencias = mapPy["frecuencias"]!!
            .asMap()
            .map { (k, v) -> k.toInt() to v.toInt() }
            .toList()
            .map {  it.second.toDouble() }

        // PROBABILIDADES
        val probabilidades = mapPy["probabilidades"]!!
            .asMap()
            .map { (k, v) -> k.toInt() to v.toDouble() }
            .toList()
            .map {  it.second }

        // ESTADISTICAS (ya no hay dict, son claves sueltas)
        val media = mapPy["media"]!!.toDouble()
        val desviacion = mapPy["desviacion"]!!.toDouble()
        val p5 = mapPy["p5"]!!.toDouble()
        val p95 = mapPy["p95"]!!.toDouble()
        val min = mapPy["min"]!!.toDouble()
        val max = mapPy["max"]!!.toDouble()
        val total = mapPy["total_datos"]!!.toDouble()

        return mapOf(
            "frecuencias" to frecuencias,
            "probabilidades" to probabilidades,
            "media" to listOf(media),
            "desviacion" to listOf(desviacion),
            "p5" to listOf(p5),
            "p95" to listOf(p95),
            "min" to listOf(min),
            "max" to listOf(max),
            "total_datos" to listOf(total)
        )
    }



}