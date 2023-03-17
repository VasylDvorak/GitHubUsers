package com.popularlibraries.ui

import com.popularlibraries.domain.CountersModel

class MainPresenter(val model: CountersModel) : CounterContract.Presenter {
    private var view: CounterContract.MainView? = null
    override fun attach(view: CounterContract.MainView) {
        this.view = view
    }

    override fun detach() {
        view = null
    }
    //Архитектурная ошибка. В качестве практического задания -- исправить

    override fun setButtonTextBtnCounterOnePresenter() =
        view?.setButtonTextBtnCounterOneMainView(model.next(0).toString())

    override fun setButtonTextBtnCounterTwoPresenter() =
        view?.setButtonTextBtnCounterTwoMainView(model.next(1).toString())

    override fun setButtonTextBtnCounterThreePresenter() =
        view?.setButtonTextBtnCounterThreeMainView(model.next(2).toString())

}
