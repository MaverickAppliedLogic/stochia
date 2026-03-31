package com.example.stochia

import android.util.Log
import com.chaquo.python.Python
import com.chaquo.python.android.PyApplication
import com.example.stochia.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Stochia : PyApplication() {
    override fun onCreate() {
        super.onCreate()
        Log.d("Python started?: ", "${Python.isStarted()}") //Check Python Started
        startKoin { //Koin DI initiation
            androidLogger()
            androidContext(this@Stochia)
            modules(appModule)
        }
    }
}