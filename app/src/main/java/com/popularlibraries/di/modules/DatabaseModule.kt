package com.popularlibraries.di.modules

import androidx.room.Room
import com.popularlibraries.App
import com.popularlibraries.domain.cache.IGithubPictureCache
import com.popularlibraries.domain.cache.IGithubRepositoriesCache
import com.popularlibraries.domain.cache.IGithubUsersCache
import com.popularlibraries.domain.cache.room.RoomGithubPictureCache
import com.popularlibraries.domain.cache.room.RoomGithubRepositoriesCache
import com.popularlibraries.domain.cache.room.RoomGithubUsersCache
import com.popularlibraries.entity.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App): Database= Room.databaseBuilder(app, Database::class.java,
        Database.DB_NAME).build()
    @Singleton
    @Provides
    fun roomGithubUsersCache(): RoomGithubUsersCache = RoomGithubUsersCache()
    @Singleton
    @Provides
    fun roomGithubRepositoriesCache(): RoomGithubRepositoriesCache = RoomGithubRepositoriesCache()

    @Singleton
    @Provides
    fun roomGithubPictureCache(): RoomGithubPictureCache = RoomGithubPictureCache()
    @Singleton
    @Provides
    fun repositoriesCache(): IGithubRepositoriesCache =  RoomGithubRepositoriesCache()


    @Singleton
    @Provides
    fun usersCache(): IGithubUsersCache= RoomGithubUsersCache()

    @Singleton
    @Provides
    fun pictureCache(): IGithubPictureCache = RoomGithubPictureCache()



}