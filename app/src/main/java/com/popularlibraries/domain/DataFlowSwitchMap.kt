package com.popularlibraries.domain

import com.popularlibraries.App
import com.popularlibraries.R
import com.popularlibraries.data.GithubUser
import com.popularlibraries.data.GithubUsersRepo
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class DataFlowSwitchMap {
    fun exec(): List<GithubUser> {
        return Consumer(Producer()).exec()
    }

    class Producer {
        fun fromIterable(): Observable<GithubUser> {
            return Observable.fromIterable(GithubUsersRepo().getUsers())
        }
    }

    class Consumer(val producer: Producer) {
        fun execSwitchMap(): MutableList<GithubUser> {
            var users = mutableListOf<GithubUser>()
            var finish = true


            producer.fromIterable()
                .switchMap {   //Взять последнее значение
                    val delay = Random.nextInt(1000).toLong() // Симуляция чтения данных с сервера
                    it.login =
                        App.instance.applicationContext.getString(R.string.users_log) + it.login

                    return@switchMap Observable.just(it)
                        .delay(delay, TimeUnit.MILLISECONDS)
                }
                .subscribe({ s ->
                    users.apply {
                        clear()
                        add(s) }

                }, {
                    println("onError: ${it.message} ")
                    finish = false
                },
                    {
                        println("Передача данных завершена")
                        finish = false
                    }
                )


            while (finish) {
                println("Продолжается передача данных")
            }

            return users
        }

        fun exec(): List<GithubUser> {
            return execJust()
        }

        fun execJust(): List<GithubUser> {
            var users = mutableListOf<GithubUser>()
            producer.fromIterable()
                .subscribe {
                    users = execSwitchMap()
                }
            return users
        }

    }
}
