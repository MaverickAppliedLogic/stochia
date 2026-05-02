package com.example.stochia.data

import android.content.Context
import okio.Path.Companion.toPath
import com.example.stochia.domain.model.study.Study
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import java.io.File

class KstoreRepository(
    private val context: Context
) {
    val PATH = context.filesDir.absolutePath + "/studies"

    suspend fun saveNewStudy(study: Study) {
        File("$PATH/study_${study.id}.json").mkdirs()
        val store: KStore<Study> = storeOf(file = "$PATH/study_${study.id}.json".toPath())
        store.set(study)
    }

    suspend fun getStudy(id: String): Study? {
        val store: KStore<Study> = storeOf(file = "$PATH/study_${id}.json".toPath())
        return store.get()
    }

    suspend fun updateStudy(study: Study) {
        val store: KStore<Study> = storeOf(file = "$PATH/study_${study.id}.json".toPath())
        store.update {
            it?.copy(
                params = study.params,
                result = study.result
            )
        }
    }

    suspend fun deleteStudy(id: String) {
        val store: KStore<Study> = storeOf(file = "$PATH/study_${id}.json".toPath())
        store.delete()
    }
}