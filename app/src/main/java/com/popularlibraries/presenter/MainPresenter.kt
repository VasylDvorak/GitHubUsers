package com.popularlibraries.presenter

import com.popularlibraries.model.CountersModel
import com.popularlibraries.ui.MainView

class MainPresenter(val view: MainView) {
    val model = CountersModel()
    //Архитектурная ошибка. В качестве практического задания -- исправить

    fun presenterSetButtonTextBtnCounterOne() =
        view.setButtonTextBtnCounterOne(model.next(0).toString())

    fun presenterSetButtonTextBtnCounterTwo() =
        view.setButtonTextBtnCounterTwo(model.next(1).toString())

    fun presenterSetButtonTextBtnCounterThree() =
        view.setButtonTextBtnCounterThree(model.next(2).toString())

}
