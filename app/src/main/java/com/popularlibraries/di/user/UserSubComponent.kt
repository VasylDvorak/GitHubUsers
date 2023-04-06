package com.popularlibraries.di.user

import com.popularlibraries.di.repository.RepositorySubcomponent
import com.popularlibraries.di.user.module.UserModule
import com.popularlibraries.ui.presenters.UsersPresenter
import com.popularlibraries.ui.users.UsersRVAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [
        UserModule::class
    ]
)
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}