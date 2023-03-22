package com.popularlibraries.ui.presenters


import android.annotation.SuppressLint
import android.os.Handler
import com.github.terrakok.cicerone.Router
import com.popularlibraries.data.GithubUser
import com.popularlibraries.domain.DataFlowConcatMap
import com.popularlibraries.domain.DataFlowSwitchMap
import com.popularlibraries.ui.UserItemView
import com.popularlibraries.ui.interfaces.IScreens
import com.popularlibraries.ui.interfaces.UsersView
import moxy.MvpPresenter

class UsersPresenter(var router: Router, var screens: IScreens) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    @SuppressLint("SuspiciousIndentation")
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        usersListPresenter.itemClickListener = { itemView ->
            val login = itemView.getLogin()

//  переход на экран пользователя c помощью router.navigateTo
            router.navigateTo(screens.aboutUser(login))
        }
    }

    fun loadData(switch_map: Boolean) {

        val handler = Handler()
        Thread {
            var users: List<GithubUser>
            if (switch_map) {
                users = DataFlowSwitchMap().exec()
            } else {
                users = DataFlowConcatMap().exec()
            }
            handler.post { updateViewList(users) }
        }.start()


    }

    private fun updateViewList(users: List<GithubUser>) {
        usersListPresenter.users.clear()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.replaceScreen(screens.users())
        return true
    }
}
