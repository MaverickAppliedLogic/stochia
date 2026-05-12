package com.example.stochia.domain.model.montecarlo

object MontecarloResultFactory {

    fun fromGeometrical(map: Map<String, Any?>): MontecarloResult {
        return MontecarloResult(
            distribution = MontecarloType.GEOMETRICAL,
            values  = null,
            mean    = null,
            stdDev  = null,
            p5      = null,
            p50     = null,
            p95     = null,
            tries   = (map["tries"] as Number).toInt(),
            results = null
        )
    }

    fun fromMultinomial(map: Map<String, Any?>): MontecarloResult {
        @Suppress("UNCHECKED_CAST")
        val meanList = map["mean"] as List<Double>
        @Suppress("UNCHECKED_CAST")
        val stdList  = map["std"]  as List<Double>
        @Suppress("UNCHECKED_CAST")
        val p5List   = map["p5"]   as List<Double>
        @Suppress("UNCHECKED_CAST")
        val p95List  = map["p95"]  as List<Double>

        val results = meanList.indices.map { i ->
            MontecarloResult(
                distribution = MontecarloType.MULTINOMIAL,
                values  = null,
                mean    = meanList[i],
                stdDev  = stdList[i],
                p5      = p5List[i],
                p50     = null,
                p95     = p95List[i],
                tries   = null,
                results = null
            )
        }

        return MontecarloResult(
            distribution = MontecarloType.MULTINOMIAL,
            values  = null,
            mean    = null,
            stdDev  = null,
            p5      = null,
            p50     = null,
            p95     = null,
            tries   = null,
            results = results
        )
    }

    fun withoutValues(map: Map<String, Any?>): MontecarloResult {
        val distribution = when (map["distribution"] as String) {
            "binomial"   -> MontecarloType.BINOMIAL
            "poisson"    -> MontecarloType.POISSON
            "multinomial"-> MontecarloType.MULTINOMIAL
            else         -> MontecarloType.NORMAL
        }
        return MontecarloResult(
            distribution = distribution,
            values  = null,
            mean    = (map["mean"] as Number).toDouble(),
            stdDev  = (map["std"]  as Number).toDouble(),
            p5      = (map["p5"]   as Number).toDouble(),
            p50     = (map["p50"]  as? Number)?.toDouble(),
            p95     = (map["p95"]  as Number).toDouble(),
            tries   = null,
            results = null
        )
    }

    fun withValues(map: Map<String, Any?>): MontecarloResult {
        val distribution = when (map["distribution"] as String) {
            "beta"        -> MontecarloType.BETA
            "exponential" -> MontecarloType.EXPONENTIAL
            "uniform"     -> MontecarloType.UNIFORM
            else          -> MontecarloType.NORMAL
        }
        @Suppress("UNCHECKED_CAST")
        val values = map["values"] as List<Double>
        return MontecarloResult(
            distribution = distribution,
            values  = values,
            mean    = (map["mean"] as Number).toDouble(),
            stdDev  = (map["std"]  as Number).toDouble(),
            p5      = (map["p5"]   as Number).toDouble(),
            p50     = null,
            p95     = (map["p95"]  as Number).toDouble(),
            tries   = null,
            results = null
        )
    }
}
