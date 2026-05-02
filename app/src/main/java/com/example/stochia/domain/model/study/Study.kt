package com.example.stochia.domain.model.study

import com.example.stochia.domain.model.interfaces.Params
import com.example.stochia.domain.model.interfaces.Result
import java.util.UUID

data class Study(
    val id: String = UUID.randomUUID().toString(),
    val params: Params,
    val result: Result
)

