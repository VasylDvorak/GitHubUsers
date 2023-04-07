package com.popularlibraries.di.modules

import com.popularlibraries.App
import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.cache.IGithubUsersCache
import com.popularlibraries.domain.cache.room.RoomGithubRepositoriesCache
import com.popularlibraries.domain.cache.room.RoomGithubUsersCache
import com.popularlibraries.domain.network.AndroidNetworkStatus
import com.popularlibraries.domain.network.INetworkStatus
import com.popularlibraries.domain.repo.IGithubUsersRepo
import com.popularlibraries.domain.repo.retrofit.IGithubRepositoriesRepo
import com.popularlibraries.domain.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.popularlibraries.domain.repo.retrofit.RetrofitGithubUsersRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus): IGithubUsersRepo =
        RetrofitGithubUsersRepo(api, networkStatus)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus): IGithubRepositoriesRepo =
        RetrofitGithubRepositoriesRepo(api, networkStatus)


}