package com.popularlibraries.domain.cache

import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {
    fun newData(api: IDataSource): Single<List<GithubUser>>
    fun fromDataBaseData(): Single<List<GithubUser>>
}