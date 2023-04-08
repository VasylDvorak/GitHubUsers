package com.popularlibraries.ui.presenters


import com.github.terrakok.cicerone.Router
import com.popularlibraries.domain.repo.IGithubUsersRepo
import com.popularlibraries.entity.GithubUser
import com.popularlibraries.ui.AndroidScreens
import com.popularlibraries.ui.interfaces.UsersView
import com.popularlibraries.ui.users.UserItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter : MvpPresenter<UsersView>() {
    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var usersRepo: IGithubUsersRepo

    @Inject
    lateinit var router: Router

    inner class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login.let { view.setName(it) }
            user.avatar_url?.let {
                view.loadAvatar(it)
            }
        }
    }

    val usersListPresenter = UsersListPresenter()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { itemView ->
            val currentUser = usersListPresenter.users[itemView.pos]
//  переход на экран пользователя c помощью router.navigateTo
            currentUser.let {
                router.navigateTo(AndroidScreens().repositories(currentUser))
            }
        }
    }

    fun loadData() {
        usersRepo.getUsers()
            .observeOn(mainThreadScheduler)
            .subscribe({ users ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(users)
                viewState.updateList()
            }, {
                println("Error: ${it.message} ")
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }

    fun backPressed(): Boolean {
        router.replaceScreen(AndroidScreens().users())
        return true
    }
}
