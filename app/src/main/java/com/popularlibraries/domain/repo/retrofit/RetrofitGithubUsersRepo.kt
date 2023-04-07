package com.popularlibraries.domain.repo.retrofit

import com.popularlibraries.domain.api.IDataSource
import com.popularlibraries.domain.repo.IGithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo (val api: IDataSource): IGithubUsersRepo {

    override fun getUsers () = api.getUsers().subscribeOn(Schedulers.io())
    override fun getRepositories(url:String)= api.getRepositories(url).subscribeOn(Schedulers.io())

}
