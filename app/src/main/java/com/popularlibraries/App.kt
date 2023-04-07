package com.popularlibraries

import android.app.Application
import com.popularlibraries.di.AppComponent
import com.popularlibraries.di.DaggerAppComponent
import com.popularlibraries.di.modules.AppModule


class App : Application() {
    lateinit var appComponent: AppComponent
    companion object {
        lateinit var instance: App
        var appInstance: App? = null  // Оставил для вызова App.appInstance?.applicationContext из любого места программы
    }



    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }



}
