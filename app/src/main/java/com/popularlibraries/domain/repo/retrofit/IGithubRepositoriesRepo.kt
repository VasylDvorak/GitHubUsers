package com.popularlibraries.domain.repo.retrofit

import com.popularlibraries.entity.GithubRepository
import com.popularlibraries.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getRepositories (currentUser : GithubUser): Single<List<GithubRepository>>
}
