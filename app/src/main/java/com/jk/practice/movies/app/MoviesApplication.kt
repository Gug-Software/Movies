package com.jk.practice.movies.app

import android.app.Application
import com.jk.practice.movies.di.MoviesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {

            // Koin Android logger
            androidLogger()

            //inject Android context
            androidContext(this@MoviesApplication)

            // use modules
            modules(MoviesModule.appModule)

        }

    }

}