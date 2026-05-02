package com.example.stochia.domain.model.dto

import kotlinx.serialization.Serializable
import com.example.stochia.domain.model.study.Study

@Serializable
data class SerializableStudy(
    val id: String,
    val name: String,
    val params: SerializableParams?,   // ← polimórfico y nullable
    val result: SerializableResult?    // ← polimórfico
)
fun Study.toSerializable(): SerializableStudy =
    SerializableStudy(
        id = id,
        name = name,
        params = params?.toSerializable(),
        result = result?.toSerializable()
    )
