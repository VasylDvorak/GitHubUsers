package com.popularlibraries.domain.repo.retrofit

import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.cache.room.RoomGithubUsersCache
import com.popularlibraries.domain.network.INetworkStatus
import com.popularlibraries.domain.repo.IGithubUsersRepo
import com.popularlibraries.entity.room.Database
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(
    val api: IDataSource, val networkStatus:
    INetworkStatus, val userCache: RoomGithubUsersCache
) : IGithubUsersRepo {


    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            userCache.newData(api)
        } else {
            userCache.fromDataBaseData()
        }
    }.subscribeOn(Schedulers.io())
}
