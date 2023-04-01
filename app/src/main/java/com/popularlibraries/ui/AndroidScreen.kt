package com.popularlibraries.ui

import android.os.Bundle
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.popularlibraries.entity.GithubRepository
import com.popularlibraries.entity.GithubUser
import com.popularlibraries.ui.interfaces.IScreens
import com.popularlibraries.ui.repositories.CURRENT_USER
import com.popularlibraries.ui.repositories.RepositoriesFragment
import com.popularlibraries.ui.users.UsersFragment

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }


    override fun repositories(currentUser: GithubUser): Screen = FragmentScreen {
        RepositoriesFragment.newInstance(Bundle().apply {
            putParcelable(
                CURRENT_USER,
                currentUser
            )
        })
    }

    override fun aboutRepository(infoRepository: GithubRepository): Screen = FragmentScreen {
        AboutRepositoryFragment.newInstance(Bundle().apply {
            putParcelable(
                REPOSITORY,
                infoRepository
            )
        })
    }


}



