package com.popularlibraries.di

import com.popularlibraries.di.modules.*
import com.popularlibraries.domain.cache.room.RoomGithubPictureCache
import com.popularlibraries.domain.cache.room.RoomGithubRepositoriesCache
import com.popularlibraries.domain.cache.room.RoomGithubUsersCache
import com.popularlibraries.domain.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.popularlibraries.domain.repo.retrofit.RetrofitGithubUsersRepo
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
    fun inject(retrofitGithubUsersRepo: RetrofitGithubUsersRepo)
    fun inject(retrofitGithubRepositoriesRepo: RetrofitGithubRepositoriesRepo)
    fun inject(roomGithubUsersCache: RoomGithubUsersCache)
    fun inject(roomGithubRepositoriesCache: RoomGithubRepositoriesCache)
    fun inject(roomGithubPictureCache: RoomGithubPictureCache)
}