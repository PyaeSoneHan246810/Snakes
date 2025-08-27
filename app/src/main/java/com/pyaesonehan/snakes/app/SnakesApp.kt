package com.pyaesonehan.snakes.app

import android.app.Application
import com.pyaesonehan.snakes.di.AppContainer
import com.pyaesonehan.snakes.di.DefaultAppContainer

class SnakesApp: Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
    }
}