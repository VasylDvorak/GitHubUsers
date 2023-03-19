package com.popularlibraries.ui.presenters


import com.github.terrakok.cicerone.Router
import com.popularlibraries.data.GithubUser
import com.popularlibraries.data.GithubUsersRepo
import com.popularlibraries.ui.UserItemView
import com.popularlibraries.ui.interfaces.IScreens
import com.popularlibraries.ui.interfaces.UsersView
import moxy.MvpPresenter

class UsersPresenter (val usersRepo: GithubUsersRepo, val router: Router, val screens: IScreens) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit )? = null
        override fun getCount () = users.size
        override fun bindView (view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }
    val usersListPresenter = UsersListPresenter()
    override fun onFirstViewAttach () {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            var login = itemView.getLogin()
//  переход на экран пользователя c помощью router.navigateTo
            router.navigateTo(screens.aboutUser(login))
       }
    }
    fun loadData () {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
    fun backPressed (): Boolean {
        router.exit()
        return true
    }
}
