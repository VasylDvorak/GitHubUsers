package com.popularlibraries.domain.repo.retrofit

import com.popularlibraries.App
import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.cache.room.RoomGithubRepositoriesCache
import com.popularlibraries.domain.network.INetworkStatus
import com.popularlibraries.entity.GithubUser
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource, val networkStatus:
    INetworkStatus
) : IGithubRepositoriesRepo {
@Inject
lateinit var repositoriesCache: RoomGithubRepositoriesCache

    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            App.instance.appComponent.inject(this)
            if (isOnline) {
                repositoriesCache.newData(user, api)
            } else {
                repositoriesCache.fromDataBaseData(user)
            }
        }.subscribeOn(Schedulers.io())
}
