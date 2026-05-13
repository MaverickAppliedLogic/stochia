package com.example.stochia.domain.model.engineMode

import kotlinx.serialization.Serializable

@Serializable
enum class EngineMode {
    LOCAL,
    REMOTE,
    DYNAMIC
}
