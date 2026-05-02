package com.example.stochia.domain.model.study

import com.example.stochia.domain.model.dto.SerializableStudy
import com.example.stochia.domain.model.interfaces.Params
import com.example.stochia.domain.model.interfaces.Result

data class Study(
    val id: String = System.currentTimeMillis().toString(),
    val name: String = "",
    val params: Params?= null,
    val result: Result?= null
)

fun SerializableStudy.toDomain(): Study =
    Study(
        id = id,
        name = name,
        params = params?.toDomain(),
        result = result?.toDomain()
    )

