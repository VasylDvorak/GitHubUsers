package com.popularlibraries.di.modules

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.popularlibraries.ui.AndroidScreens
import com.popularlibraries.ui.interfaces.IScreens
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    var cicerone: Cicerone<Router> = Cicerone.create()
    @Provides
    fun cicerone(): Cicerone<Router> = cicerone
    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()
    @Singleton
    @Provides
    fun router(): Router = cicerone.router
    @Singleton
    @Provides
    fun screens(): IScreens = AndroidScreens()
}