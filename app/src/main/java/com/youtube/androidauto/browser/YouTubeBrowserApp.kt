package com.youtube.androidauto.browser

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class YouTubeBrowserApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val isDebug =
            try {
                val cls = Class.forName("$packageName.BuildConfig")
                val field = cls.getField("DEBUG")
                field.getBoolean(null)
            } catch (t: Throwable) {
                false
            }
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
