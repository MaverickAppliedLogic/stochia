package com.example.stochia.domain.usecase

import com.example.stochia.core.services.CalculationSystemService
import com.example.stochia.domain.model.markov.MarkovParams
import com.example.stochia.domain.model.markov.MarkovResult

class GenMarkovUsecase(
    private val service: CalculationSystemService
) {
    operator fun invoke(data: MarkovParams): MarkovResult = service.genMarkov(data)
}
