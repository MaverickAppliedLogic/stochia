package com.example.stochia.data.kstore

import android.content.Context
import com.example.stochia.domain.model.dto.SerializableStudy
import com.example.stochia.domain.model.dto.toSerializable
import com.example.stochia.domain.model.study.Study
import com.example.stochia.domain.model.study.toDomain
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import okio.Path.Companion.toPath
import java.io.File

class KstoreRepository(
    private val context: Context
) {
    private val PATH = context.filesDir.absolutePath + "/studies"

    init {
        File(PATH).mkdirs()
    }

    private fun fileFor(id: String): File =
        File(PATH, "study_${id}.json")

    // ---------------------------------------------------------
    // SAVE
    // ---------------------------------------------------------
    suspend fun saveNewStudy(study: Study) {
        val file = fileFor(study.id)
        val store: KStore<SerializableStudy> =
            storeOf(file = file.absolutePath.toPath())

        store.set(study.toSerializable())
    }

    // ---------------------------------------------------------
    // GET ALL
    // ---------------------------------------------------------
    suspend fun getAllStudy(): List<Study> {
        val dir = File(PATH)
        val files = dir.listFiles()
            ?.filter { it.isFile && it.extension == "json" }
            ?: emptyList()

        val list = mutableListOf<Study>()

        for (file in files) {
            val store = storeOf<SerializableStudy>(
                file = file.absolutePath.toPath()
            )

            val serializable = store.get()
            if (serializable != null) {
                list.add(serializable.toDomain())
            }
        }

        return list.sortedByDescending { it.id }
    }

    // ---------------------------------------------------------
    // GET ONE
    // ---------------------------------------------------------
    suspend fun getStudy(id: String): Study? {
        val file = fileFor(id)
        val store: KStore<SerializableStudy> =
            storeOf(file = file.absolutePath.toPath())

        return store.get()?.toDomain()
    }

    // ---------------------------------------------------------
    // UPDATE
    // ---------------------------------------------------------
    suspend fun updateStudy(study: Study) {
        val file = fileFor(study.id)
        val store: KStore<SerializableStudy> =
            storeOf(file = file.absolutePath.toPath())

        store.update {
            study.toSerializable()
        }
    }

    // ---------------------------------------------------------
    // DELETE
    // ---------------------------------------------------------
    suspend fun deleteStudy(id: String) {
        val file = fileFor(id)
        val store: KStore<SerializableStudy> =
            storeOf(file = file.absolutePath.toPath())

        store.delete()
    }
}