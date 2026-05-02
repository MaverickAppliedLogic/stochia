package com.example.stochia.domain.usecase

import com.example.stochia.data.KstoreRepository
import com.example.stochia.domain.model.study.Study

class UpdateStudyUsecase(
    private val repository: KstoreRepository
) {
    suspend operator fun invoke(study: Study) = repository.updateStudy(study)
}