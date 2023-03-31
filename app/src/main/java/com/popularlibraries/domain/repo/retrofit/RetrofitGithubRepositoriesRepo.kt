package com.popularlibraries.domain.repo.retrofit

import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.cache.room.RoomGithubRepositoriesCache
import com.popularlibraries.domain.network.INetworkStatus
import com.popularlibraries.entity.GithubUser
import com.popularlibraries.entity.room.Database
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource, val networkStatus:
    INetworkStatus, private val  db: Database
) : IGithubRepositoriesRepo {

    private val repositoriesCache = RoomGithubRepositoriesCache(db)

    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                repositoriesCache.newData(user, api)
            } else {
                repositoriesCache.fromDataBaseData(user)
            }
        }.subscribeOn(Schedulers.io())
}
