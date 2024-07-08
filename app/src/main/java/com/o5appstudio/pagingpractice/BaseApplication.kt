package com.o5appstudio.pagingpractice

import android.app.Application
import com.o5appstudio.pagingpractice.di.allModules
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(allModules)
        }
    }
}