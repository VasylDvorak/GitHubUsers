package com.popularlibraries.di.repository.module

import com.popularlibraries.App
import com.popularlibraries.di.repository.IRepositoryScopeContainer
import com.popularlibraries.di.repository.RepositoryScope
import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.cache.IGithubRepositoriesCache
import com.popularlibraries.domain.cache.room.RoomGithubRepositoriesCache
import com.popularlibraries.domain.network.INetworkStatus
import com.popularlibraries.domain.repo.retrofit.IGithubRepositoriesRepo
import com.popularlibraries.domain.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.popularlibraries.entity.room.Database
import dagger.Module
import dagger.Provides

@Module
open class RepositoryModule {
    @Provides
    fun repositoriesCache(): IGithubRepositoriesCache {
        return RoomGithubRepositoriesCache()
    }
    @RepositoryScope
    @Provides
    fun repositoriesRepo(api: IDataSource, networkStatus: INetworkStatus): IGithubRepositoriesRepo {
        return RetrofitGithubRepositoriesRepo(api, networkStatus)
    }
    @RepositoryScope
    @Provides
    open fun scopeContainer(app: App): IRepositoryScopeContainer = app
}