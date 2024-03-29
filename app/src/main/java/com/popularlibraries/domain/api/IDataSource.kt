package com.popularlibraries.domain.api

import com.popularlibraries.entity.GithubRepository
import com.popularlibraries.entity.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {

    @GET( "/users" )
    fun getUsers (): Single<List<GithubUser>>

    @GET
    fun getRepositories (@Url url:String): Single<List<GithubRepository>>

}
