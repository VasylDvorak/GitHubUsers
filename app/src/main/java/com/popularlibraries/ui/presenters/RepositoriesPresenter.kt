package com.popularlibraries.ui.presenters

import android.annotation.SuppressLint
import com.github.terrakok.cicerone.Router
import com.popularlibraries.entity.GithubRepository
import com.popularlibraries.domain.repo.IGithubUsersRepo
import com.popularlibraries.domain.repo.ReposItemView
import com.popularlibraries.ui.interfaces.IScreens
import com.popularlibraries.ui.interfaces.UsersView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class RepositoriesPresenter(
    val mainThreadScheduler: Scheduler,
    val usersRepo: IGithubUsersRepo,
    var router: Router,
    var screens: IScreens
) :
    MvpPresenter<UsersView>() {

    class RepositoriesListPresenter : RepositListPresenter {
        val repositories = mutableListOf<GithubRepository>()
        override var itemClickListener: ((ReposItemView) -> Unit)? = null
        override fun getCount() = repositories.size
        override fun bindView(view: ReposItemView) {
            val repository = repositories[view.pos]
            repository.let { view.setName(it.name) }
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    @SuppressLint("SuspiciousIndentation")
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }


    fun loadRepositories(url: String) {
        usersRepo.getRepositories(url)
            .observeOn(mainThreadScheduler)
            .subscribe({ repos ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repos)
                viewState.updateList()
            }, {
                println("Error: ${it.message} ")
            })

        repositoriesListPresenter.itemClickListener = { itemView ->
            val infoRepository = repositoriesListPresenter.repositories[itemView.pos]


            router.navigateTo(screens.aboutRepository(infoRepository))
        }
    }


    fun backPressed(): Boolean {
        router.replaceScreen(screens.users())
        return true
    }
}
