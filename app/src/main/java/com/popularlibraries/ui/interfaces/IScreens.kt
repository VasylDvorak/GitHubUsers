package com.popularlibraries.ui.interfaces

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun aboutUser(login: String): Screen
}
