package com.popularlibraries.di.repository

import com.popularlibraries.di.repository.module.RepositoryModule
import com.popularlibraries.ui.presenters.RepositoriesPresenter
import com.popularlibraries.ui.presenters.UsersPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class
    ]
)
interface RepositorySubcomponent {
    fun inject(userPresenter: UsersPresenter)
    fun inject(repositoryPresenter: RepositoriesPresenter)
}