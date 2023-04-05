package com.popularlibraries.domain.cache.room

import com.popularlibraries.App
import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.cache.IGithubRepositoriesCache
import com.popularlibraries.entity.GithubRepository
import com.popularlibraries.entity.GithubUser
import com.popularlibraries.entity.room.Database
import com.popularlibraries.entity.room.RoomGithubRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class RoomGithubRepositoriesCache : IGithubRepositoriesCache {
    @Inject
    lateinit var database: Database
    init{
        App.instance.appComponent.inject(this)
    }
    override fun newData(user: GithubUser, api: IDataSource): Single<List<GithubRepository>> {
        return user.repos_url?.let { url ->
            api.getRepositories(url)
                .flatMap { repositories ->
                    Single.fromCallable {
                        val roomUser = user.login.let {
                            database.userDao.findByLogin(it)
                        }
                        val roomRepos = repositories.map {
                            RoomGithubRepository(
                                it.id, it.name ?: "", it.forksCount ?: 0,
                                roomUser.id
                            )
                        }

                        database.repositoryDao.insert(roomRepos)
                        repositories

                    }
                }
        }
            ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url"))
                .subscribeOn(Schedulers.io())
    }


    override fun fromDataBaseData(user: GithubUser): Single<List<GithubRepository>> {
        return Single.fromCallable {
            val roomUser = user.login.let { database.userDao.findByLogin(it) }

            database.repositoryDao.findForUser(roomUser.id).map {
                GithubRepository(it.id, it.name, it.forksCount)
            }
        }
    }
}