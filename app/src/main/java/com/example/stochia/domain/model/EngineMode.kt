package com.example.stochia.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class EngineMode {
    LOCAL,
    REMOTE,
    DYNAMIC
}
