package com.popularlibraries.domain.cache.room

import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.cache.IGithubUsersCache
import com.popularlibraries.entity.GithubUser
import com.popularlibraries.entity.room.Database
import com.popularlibraries.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Single

class RoomGithubUsersCache(private val db: Database) :IGithubUsersCache {

    override fun newData(
        api: IDataSource
    ): Single<List<GithubUser>> {
        return api.getUsers()
            .flatMap { users ->
                Single.fromCallable {
                    val roomUsers = users.map { user ->
                        RoomGithubUser(user.id ?: "" ,
                            user.login ?: "" , user.avatar_url ?: "" ,
                            user.repos_url ?: "" ) }
                    db.userDao.insert(roomUsers)
                    users
                }
            }

    }

    override fun fromDataBaseData(): Single<List<GithubUser>> {
      return  Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatar_url,
                    roomUser.repos_url)
            }
        }
    }


}