package com.popularlibraries.domain.cache

import com.popularlibraries.entity.GithubPicture
import com.popularlibraries.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubPictureCache {
    fun newData(users: List<GithubUser>)
    fun fromDataBaseData(): Single<List<GithubPicture>>
}