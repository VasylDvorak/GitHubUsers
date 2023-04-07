package com.popularlibraries.domain.repo.retrofit

import com.popularlibraries.App
import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.cache.room.RoomGithubUsersCache
import com.popularlibraries.domain.network.INetworkStatus
import com.popularlibraries.domain.repo.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RetrofitGithubUsersRepo(
    val api: IDataSource, val networkStatus: INetworkStatus
) : IGithubUsersRepo {
@Inject
lateinit var userCache: RoomGithubUsersCache


    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        App.instance.appComponent.inject(this)
        if (isOnline) {
            userCache.newData(api)
        } else {
            userCache.fromDataBaseData()
        }
    }.subscribeOn(Schedulers.io())
}
