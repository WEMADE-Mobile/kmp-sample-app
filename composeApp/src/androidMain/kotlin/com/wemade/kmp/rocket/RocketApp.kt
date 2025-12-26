package com.wemade.kmp.rocket

import android.app.Application
import com.wemade.kmp.rocket.di.initKoin

class RocketApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}