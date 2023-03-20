package com.popularlibraries.ui

import android.os.Bundle
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.popularlibraries.ui.interfaces.IScreens

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance()}
    override fun aboutUser(login: String): Screen = FragmentScreen {
        AboutUserFragment.newInstance(Bundle().apply { putString(LOGIN, login) })}

}
