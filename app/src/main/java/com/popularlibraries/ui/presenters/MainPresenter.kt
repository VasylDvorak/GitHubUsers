package com.popularlibraries.ui.presenters

import com.github.terrakok.cicerone.Router
import com.popularlibraries.ui.AndroidScreens
import com.popularlibraries.ui.interfaces.MainView
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter () :
    MvpPresenter<MainView>() {
    @Inject
    lateinit var router: Router
    override fun onFirstViewAttach () {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens().users())
    }

    fun backClicked () {
        router.exit()
    }
}
