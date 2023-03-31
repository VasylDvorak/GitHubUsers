package com.popularlibraries.domain.cache.room

import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.cache.IGithubRepositoriesCache
import com.popularlibraries.entity.GithubRepository
import com.popularlibraries.entity.GithubUser
import com.popularlibraries.entity.room.Database
import com.popularlibraries.entity.room.RoomGithubRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class RoomGithubRepositoriesCache(private val db: Database) : IGithubRepositoriesCache {

    override fun newData(user: GithubUser, api: IDataSource): Single<List<GithubRepository>> {
        return user.repos_url?.let { url ->
            api.getRepositories(url)
                .flatMap { repositories ->
                    Single.fromCallable {
                        val roomUser = user.login.let {
                            db.userDao.findByLogin(it)
                        }
                        val roomRepos = repositories.map {
                            RoomGithubRepository(
                                it.id, it.name ?: "", it.forksCount ?: 0,
                                roomUser.id
                            )
                        }

                        db.repositoryDao.insert(roomRepos)
                        repositories

                    }
                }
        }
            ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                .subscribeOn(Schedulers.io())
    }


    override fun fromDataBaseData(user: GithubUser): Single<List<GithubRepository>> {
        return Single.fromCallable {
            val roomUser = user.login.let { db.userDao.findByLogin(it) }

            db.repositoryDao.findForUser(roomUser.id).map {
                GithubRepository(it.id, it.name, it.forksCount)
            }
        }
    }
}