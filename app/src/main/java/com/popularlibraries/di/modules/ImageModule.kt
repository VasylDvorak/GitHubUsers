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

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}