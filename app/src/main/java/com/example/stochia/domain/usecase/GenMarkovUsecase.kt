package com.example.stochia.domain.usecase

import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.markov.toMarkovResult
import com.example.stochia.repository.CalculationSystemRepository

class GenMarkovUsecase(
    private val repository: CalculationSystemRepository
) {
    operator fun invoke(data: MarkovParams): MarkovResult{
        val mapStates = mapOf(
            data.states[0] to 0,
            data.states[1] to 1,
            data.states[2] to 2
        )
        val states = mapStates.values.toIntArray()
        val probs = data.probs.toDoubleArray()

        return repository
            .genMarkov(states,probs,data.init_state, data.steps)
            .toMarkovResult()

    }
}