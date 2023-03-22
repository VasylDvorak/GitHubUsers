package com.popularlibraries.ui.presenters

import com.github.terrakok.cicerone.Router
import com.popularlibraries.ui.interfaces.IScreens
import com.popularlibraries.ui.interfaces.MainView
import moxy.MvpPresenter

class MainPresenter (val router: Router, val screens: IScreens) :
    MvpPresenter<MainView>() {
    override fun onFirstViewAttach () {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked () {
        router.exit()
    }
}
