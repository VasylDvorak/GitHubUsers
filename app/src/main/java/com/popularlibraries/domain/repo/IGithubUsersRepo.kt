package com.popularlibraries.domain.repo

import com.popularlibraries.entity.GithubRepository
import com.popularlibraries.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUsersRepo {

    fun getUsers (): Single<List<GithubUser>>
    fun getRepositories (url:String): Single<List<GithubRepository>>

}
