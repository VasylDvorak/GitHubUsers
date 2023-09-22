package com.popularlibraries.di.user.module

import com.popularlibraries.App
import com.popularlibraries.di.user.IUserScopeContainer
import com.popularlibraries.di.user.UserScope
import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.cache.IGithubUsersCache
import com.popularlibraries.domain.cache.room.RoomGithubUsersCache
import com.popularlibraries.domain.network.INetworkStatus
import com.popularlibraries.domain.repo.IGithubUsersRepo
import com.popularlibraries.domain.repo.retrofit.RetrofitGithubUsersRepo
import com.popularlibraries.entity.room.Database
import dagger.Module
import dagger.Provides

@Module
open class UserModule {
    @Provides
    fun usersCache(): IGithubUsersCache {
        return RoomGithubUsersCache()
    }
    @UserScope
    @Provides
    open fun usersRepo(api: IDataSource, networkStatus: INetworkStatus): IGithubUsersRepo {
        return RetrofitGithubUsersRepo(api, networkStatus)
    }
    @UserScope
    @Provides
    open fun scopeContainer(app: App): IUserScopeContainer = app
}