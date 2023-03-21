package com.popularlibraries.domain

import com.popularlibraries.App
import com.popularlibraries.R
import com.popularlibraries.data.GithubUser
import com.popularlibraries.data.GithubUsersRepo
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class DataFlow {
    fun exec ()  : List<GithubUser>{
       return Consumer(Producer()).exec()
    }
    class Producer {
        fun fromCallable () = Observable.fromCallable {
            val result = GithubUsersRepo().getUsers()
            return@fromCallable result
        }

    }
    class Consumer (val producer: Producer) {
        fun execConcatMap(): MutableList<GithubUser> {
            var users = mutableListOf<GithubUser>()
            var finish = true
            producer.fromCallable()
                .concatMap{              //Последовательная передача данных
                    val delay = Random.nextInt( 1000).toLong() // Симуляция чтения данных с сервера
                 it.forEach { user -> user.login = App.instance.applicationContext
                     .getString(R.string.users_log)+user.login }
                    return@concatMap Observable.just(it).delay(delay,
                        TimeUnit.MILLISECONDS)
                }
                .subscribe({ s ->
                    users.addAll(s)

                }, {
                    println( "onError: ${it.message} ")
                    finish = false
                },
                    {
                     println("Передача данных завершена")
                        finish = false
                    }
                    )
            while(finish){
                println("Продолжается передача данных")
            }
            return users
        }


                fun exec () : List<GithubUser> {
           return execJust ()
        }


        fun execJust (): List<GithubUser> {
            var users = mutableListOf<GithubUser>()
            producer.fromCallable ()
                .subscribe {
                    users = execConcatMap()
                }


        return users
        }
    }
}
