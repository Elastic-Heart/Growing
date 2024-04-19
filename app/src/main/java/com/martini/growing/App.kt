package com.martini.growing

import android.app.Application
import com.martini.featuretoggle.di.FeatureToggleModule
import com.martini.growing.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                AppModule,
                FeatureToggleModule
            )
        }
    }
}