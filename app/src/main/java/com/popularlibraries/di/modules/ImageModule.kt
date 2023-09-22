package com.popularlibraries.di.modules

import android.widget.ImageView
import com.popularlibraries.domain.image.IImageLoader
import com.popularlibraries.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {
    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}