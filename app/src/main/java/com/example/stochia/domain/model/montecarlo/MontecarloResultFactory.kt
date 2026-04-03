package com.example.stochia.domain.model.montecarlo

import com.chaquo.python.PyObject

object MontecarloResultFactory {

    fun fromBernoulli(mapPy: Map<String, PyObject>): MontecarloResult {

        val resList = mapPy["res"]!!.asList()

        val results = resList.map { pyObj ->
            val entry = pyObj.asMap().mapKeys { it.key.toString() }
            MontecarloResult(
                distribution = MontecarloType.BERNOULLI,
                values = null,
                mean = entry["mean"]!!.toDouble(),
                stdDev = entry["std"]!!.toDouble(),
                p5 = null,
                p95 = null,
                tries = entry["tries"]!!.toDouble().toInt(),
                results = null
            )
        }
        return MontecarloResult(
            distribution = MontecarloType.BERNOULLI,
            values = null,
            mean = null,
            stdDev = null,
            p5 = null,
            p95 = null,
            tries = null,
            results = results
        )
    }

    fun fromGeometrical(mapPy: Map<String, PyObject>): MontecarloResult{
        val triesValue = mapPy["tries"]!!.toInt()

        return MontecarloResult(
            distribution = MontecarloType.GEOMETRICAL,
            values = null,
            mean = null,
            stdDev = null,
            p5 = null,
            p95 = null,
            tries = triesValue,
            results = null
        )
    }

    fun fromMultinomial(mapPy: Map<String, PyObject>): MontecarloResult {

        val meanList = mapPy["mean"]!!.asList().map { it.toDouble() }
        val stdList = mapPy["std"]!!.asList().map { it.toDouble() }
        val p5List = mapPy["p5"]!!.asList().map { it.toDouble() }
        val p95List = mapPy["p95"]!!.asList().map { it.toDouble() }

        val results = meanList.indices.map { i ->
            MontecarloResult(
                distribution = MontecarloType.MULTINOMIAL,
                values = null,
                mean = meanList[i],
                stdDev = stdList[i],
                p5 = p5List[i],
                p95 = p95List[i],
                tries = null,
                results = null
            )
        }

        return MontecarloResult(
            distribution = MontecarloType.MULTINOMIAL,
            values = null,
            mean = null,
            stdDev = null,
            p5 = null,
            p95 = null,
            tries = null,
            results = results
        )
    }

    fun withoutValues(mapPy: Map<String, PyObject>): MontecarloResult{
        val distPy = mapPy["distribution"]!!.toString()
        val distribution = when(distPy){
            "binomial" -> MontecarloType.BINOMIAL
            "poisson" -> MontecarloType.POISSON
            "multinomial" -> MontecarloType.MULTINOMIAL
            else -> MontecarloType.NORMAL
        }

        val mean = mapPy["mean"]!!.toDouble()
        val stdDev = mapPy["std"]!!.toDouble()
        val p5 = mapPy["p5"]!!.toDouble()
        val p95 = mapPy["p95"]!!.toDouble()

        return MontecarloResult(
            distribution = distribution,
            values = null,
            mean = mean,
            stdDev = stdDev,
            p5 = p5,
            p95 = p95,
            tries = null,
            results = null
        )
    }

    fun withValues(mapPy: Map<String, PyObject>): MontecarloResult{
        val distPy = mapPy["distribution"]!!.toString()
        val distribution = when(distPy){
            "beta" -> MontecarloType.BETA
            "exponential" -> MontecarloType.EXPONENTIAL
            "normal" -> MontecarloType.NORMAL
            "uniform" -> MontecarloType.UNIFORM
            else -> MontecarloType.NORMAL
        }
        val values = mapPy["values"]!!
            .asList()
            .map { it.toDouble() }
        val mean = mapPy["mean"]!!.toDouble()
        val stdDev = mapPy["std"]!!.toDouble()
        val p5 = mapPy["p5"]!!.toDouble()
        val p95 = mapPy["p95"]!!.toDouble()

        return MontecarloResult(
            distribution = distribution,
            values = values,
            mean = mean,
            stdDev = stdDev,
            p5 = p5,
            p95 = p95,
            tries = null,
            results = null
        )
    }
}
