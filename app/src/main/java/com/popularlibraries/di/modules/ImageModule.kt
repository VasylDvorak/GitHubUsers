package com.popularlibraries.di.modules

import android.widget.ImageView
import com.popularlibraries.App
import com.popularlibraries.domain.image.IImageLoader
import com.popularlibraries.domain.network.INetworkStatus
import com.popularlibraries.entity.room.Database
import com.popularlibraries.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {
    @Named("cacheDir")
    @Singleton
    @Provides
    fun cacheDir(app: App): File = app.cacheDir
    @Singleton
    @Provides
    fun imageCache(database: Database, @Named("cacheDir") cacheDir: File):
            IImageCache = RoomImageCache(database, cacheDir)
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}