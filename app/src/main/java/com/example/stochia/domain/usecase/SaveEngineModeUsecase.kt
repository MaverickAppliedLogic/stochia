package com.example.stochia.domain.usecase

import com.example.stochia.data.kstore.EnginePreferenceRepository
import com.example.stochia.domain.model.engineMode.EngineMode

class SaveEngineModeUsecase(
    private val repository: EnginePreferenceRepository
) {
    suspend operator fun invoke(mode: EngineMode) = repository.save(mode)
}
