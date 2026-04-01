package com.example.stochia.core.calculation_system.`interface`

interface EngineService {

    fun gen_montecarlo()

    fun gen_markov()

    fun get_distribution(data: Iterable<Int>) : Map<String, Any>
}
