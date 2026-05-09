package com.example.stochia.domain.usecase

import com.example.stochia.data.kstore.KstoreRepository
import com.example.stochia.domain.model.interfaces.Params
import com.example.stochia.domain.model.interfaces.Result
import com.example.stochia.domain.model.study.Study

class SaveStudyUsecase(
    private val repository: KstoreRepository
) {
    suspend operator fun invoke(params: Params, result: Result) =
        repository.saveNewStudy(Study(params = params, result = result ))
}
