package com.example.stochia.data.kstore

import android.content.Context
import com.example.stochia.domain.model.engineMode.EngineMode
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.storeOf
import okio.Path.Companion.toPath
import java.io.File

class EnginePreferenceRepository(context: Context) {

    private val file = File(context.filesDir, "preferences/engine_mode.json")
        .also { it.parentFile?.mkdirs() }

    private val store: KStore<EngineMode> =
        storeOf(file = file.absolutePath.toPath(), default = EngineMode.DYNAMIC)

    suspend fun get(): EngineMode = store.get() ?: EngineMode.DYNAMIC

    suspend fun save(mode: EngineMode) = store.set(mode)
}
