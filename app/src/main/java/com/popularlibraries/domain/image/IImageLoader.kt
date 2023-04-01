package com.popularlibraries.domain.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}
