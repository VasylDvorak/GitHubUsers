package com.popularlibraries.di.repository

import com.popularlibraries.di.repository.module.RepositoryModule
import com.popularlibraries.ui.presenters.RepositoriesPresenter
import dagger.Subcomponent

@RepositoryScope
@Subcomponent(
    modules = [
        RepositoryModule::class])
interface RepositorySubcomponent {
    fun inject(repositoryPresenter: RepositoriesPresenter)
}