package com.popularlibraries.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.popularlibraries.databinding.ActivityMainBinding
import com.popularlibraries.domain.CountersModel


class MainActivity : AppCompatActivity(), CounterContract.MainView {
    private var vb: ActivityMainBinding? = null
    private lateinit var presenter: CounterContract.Presenter

    //  val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        presenter = MainPresenter(CountersModel())
        presenter.attach(this)

        vb?.apply {
            with(presenter) {
                btnCounterOne.setOnClickListener { setButtonTextBtnCounterOnePresenter() }
                btnCounterTwo.setOnClickListener { setButtonTextBtnCounterTwoPresenter() }
                btnCounterThree.setOnClickListener { setButtonTextBtnCounterThreePresenter() }
            }
        }

    }
    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index

    override fun setButtonTextBtnCounterOneMainView(text: String) {
        vb?.btnCounterOne?.text = text
    }

    override fun setButtonTextBtnCounterTwoMainView(text: String) {
        vb?.btnCounterTwo?.text = text
    }

    override fun setButtonTextBtnCounterThreeMainView(text: String) {
        vb?.btnCounterThree?.text = text
    }


    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

}
