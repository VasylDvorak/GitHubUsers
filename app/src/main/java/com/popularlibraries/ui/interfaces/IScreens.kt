package com.popularlibraries.ui.interfaces

import com.github.terrakok.cicerone.Screen
import com.popularlibraries.entity.GithubRepository

interface IScreens {

    fun users(): Screen
    fun aboutRepository(infoRepository: GithubRepository): Screen
    fun repositories(url: String): Screen

}
