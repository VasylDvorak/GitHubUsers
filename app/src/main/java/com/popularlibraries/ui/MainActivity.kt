package com.popularlibraries.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.terrakok.cicerone.NavigatorHolder
//import com.arellomobile.mvp.MvpAppCompatActivity
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.popularlibraries.App
import com.popularlibraries.R
import com.popularlibraries.databinding.ActivityMainBinding

import com.popularlibraries.ui.interfaces.BackButtonListener
import com.popularlibraries.ui.interfaces.MainView
import com.popularlibraries.ui.presenters.MainPresenter
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

private const val REQUEST_CODE = 100

class MainActivity : MvpAppCompatActivity(), MainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        } }

    private var vb: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
 App.instance.appComponent.inject(this)
        if (!(ContextCompat.checkSelfPermission(
                this@MainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
                    )
        ) {
            askPermission()
        }

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
       navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }

        presenter.backClicked()
    }


    private fun askPermission() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            if (!(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(
                    this@MainActivity,
                    "Please provide the required permissions",
                    Toast.LENGTH_SHORT
                ).show()
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}
