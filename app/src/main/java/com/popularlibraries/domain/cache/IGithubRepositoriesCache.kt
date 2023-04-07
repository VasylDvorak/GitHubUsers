package com.popularlibraries.domain.cache

import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.entity.GithubRepository
import com.popularlibraries.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {
    fun newData(user: GithubUser, api: IDataSource): Single<List<GithubRepository>>
    fun fromDataBaseData(user: GithubUser): Single<List<GithubRepository>>
}