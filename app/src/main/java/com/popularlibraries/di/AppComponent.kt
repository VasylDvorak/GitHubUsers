package com.popularlibraries.di

import com.popularlibraries.di.modules.*
import com.popularlibraries.domain.cache.room.RoomGithubUsersCache
import com.popularlibraries.ui.MainActivity
import com.popularlibraries.ui.presenters.MainPresenter
import com.popularlibraries.ui.presenters.UsersPresenter
import com.popularlibraries.ui.repositories.RepositoriesFragment
import com.popularlibraries.ui.users.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)


    //ДЗ избавиться от зависимостей ниже
    fun inject(usersFragment: UsersFragment)

    fun inject(repositoriesFragment: RepositoriesFragment)
}