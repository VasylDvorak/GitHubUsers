package com.popularlibraries.di

import com.popularlibraries.di.modules.*
import com.popularlibraries.ui.MainActivity
import com.popularlibraries.ui.presenters.MainPresenter
import com.popularlibraries.ui.presenters.RepositoriesPresenter
import com.popularlibraries.ui.presenters.UsersPresenter
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
    fun inject(repositoriesPresenter: RepositoriesPresenter)

}