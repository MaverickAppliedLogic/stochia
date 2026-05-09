package com.example.stochia.domain.usecase

import com.example.stochia.data.kstore.KstoreRepository
import com.example.stochia.domain.model.study.Study

class GetStudyUsecase(
    private val repository: KstoreRepository
) {
    suspend operator fun invoke(id: String): Study? = repository.getStudy(id)
}
