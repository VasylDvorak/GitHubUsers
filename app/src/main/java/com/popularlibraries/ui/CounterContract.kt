package com.popularlibraries.ui

interface CounterContract {

    interface MainView {
        fun setButtonTextBtnCounterOneMainView(text: String)
        fun setButtonTextBtnCounterTwoMainView(text: String)
        fun setButtonTextBtnCounterThreeMainView(text: String)
    }

    interface Presenter {
        fun attach(view: MainView)
        fun detach()
        fun setButtonTextBtnCounterOnePresenter(): Unit?
        fun setButtonTextBtnCounterTwoPresenter(): Unit?
        fun setButtonTextBtnCounterThreePresenter(): Unit?

    }

}