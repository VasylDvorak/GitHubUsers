package com.popularlibraries.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.popularlibraries.databinding.ActivityMainBinding
import com.popularlibraries.presenter.MainPresenter


class MainActivity : AppCompatActivity(), MainView {
    private var vb: ActivityMainBinding? = null
    val presenter = MainPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.apply {
            with(presenter) {
                btnCounterOne.setOnClickListener { presenterSetButtonTextBtnCounterOne() }
                btnCounterTwo.setOnClickListener { presenterSetButtonTextBtnCounterTwo() }
                btnCounterThree.setOnClickListener { presenterSetButtonTextBtnCounterThree() }
            }


        }

    }
    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index

    override fun setButtonTextBtnCounterOne(text: String) {
        vb?.btnCounterOne?.text = text
    }

    override fun setButtonTextBtnCounterTwo(text: String) {
        vb?.btnCounterTwo?.text = text
    }

    override fun setButtonTextBtnCounterThree(text: String) {
        vb?.btnCounterThree?.text = text
    }

}
