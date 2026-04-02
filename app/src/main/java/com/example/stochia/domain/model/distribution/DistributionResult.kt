package com.example.stochia.domain.model.distribution

import com.chaquo.python.PyObject

data class DistributionResult(
    val frecuencias: List<Double>,
    val probabilidades: List<Double>,
    val media: Double,
    val desviacion: Double,
    val p5: Double,
    val p95: Double,
    val min: Double,
    val max: Double,
    val total: Double
)

fun PyObject.toDomain(result: PyObject): DistributionResult {
    val mapPy = result.asMap().mapKeys { it.key.toString() }

    val frecuencias = mapPy["frecuencias"]!!
        .asMap()
        .map { (k, v) -> k.toInt() to v.toInt() }
        .toList()
        .map {  it.second.toDouble() }

    val probabilidades = mapPy["probabilidades"]!!
        .asMap()
        .map { (k, v) -> k.toInt() to v.toDouble() }
        .toList()
        .map {  it.second }

    val media = mapPy["media"]!!.toDouble()
    val desviacion = mapPy["desviacion"]!!.toDouble()
    val p5 = mapPy["p5"]!!.toDouble()
    val p95 = mapPy["p95"]!!.toDouble()
    val min = mapPy["min"]!!.toDouble()
    val max = mapPy["max"]!!.toDouble()
    val total = mapPy["total_datos"]!!.toDouble()

    return DistributionResult(
        frecuencias = frecuencias,
        probabilidades = probabilidades,
        media = media,
        desviacion = desviacion,
        p5 = p5,
        p95 = p95,
        min = min,
        max = max,
        total = total
    )

}