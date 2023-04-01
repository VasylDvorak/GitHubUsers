package com.popularlibraries

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.popularlibraries.entity.room.Database

class App : Application() {

    companion object {
        lateinit var instance: App
        var appInstance: App? = null  // Оставил для вызова App.appInstance?.applicationContext из любого места программы
    }

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }

    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router
    override fun onCreate() {
        super.onCreate()
        instance = this
        Database.create(this)
    }
}
