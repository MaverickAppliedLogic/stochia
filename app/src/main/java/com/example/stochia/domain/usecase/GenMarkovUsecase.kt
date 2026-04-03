package com.example.stochia.domain.usecase

import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.markov.MarkovResult
import com.example.stochia.domain.model.markov.toMarkovResult
import com.example.stochia.repository.CalculationSystemRepository

class GenMarkovUsecase(
    private val repository: CalculationSystemRepository
) {
    operator fun invoke(data: MarkovParams): MarkovResult{
        val states = data.states.toIntArray()
        val probs = data.probs.toDoubleArray()

        return repository
            .genMarkov(states,probs,data.init_state, data.steps)
            .toMarkovResult()

    }
}