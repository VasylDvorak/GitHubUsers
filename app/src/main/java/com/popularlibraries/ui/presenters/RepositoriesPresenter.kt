package com.popularlibraries.ui.presenters


import com.github.terrakok.cicerone.Router
import com.popularlibraries.domain.repo.ReposItemView
import com.popularlibraries.domain.repo.retrofit.IGithubRepositoriesRepo
import com.popularlibraries.entity.GithubRepository
import com.popularlibraries.entity.GithubUser
import com.popularlibraries.ui.AndroidScreens
import com.popularlibraries.ui.interfaces.UsersView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoriesPresenter(
   // val mainThreadScheduler: Scheduler
) : MvpPresenter<UsersView>() {

    @Inject
    lateinit var mainThreadScheduler: Scheduler
    @Inject
    lateinit var repositoriesRepo: IGithubRepositoriesRepo
    @Inject
    lateinit var router:Router


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


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

    }

    fun loadRepositories(currentUser: GithubUser) {

        repositoriesRepo.getRepositories(currentUser)
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


            router.navigateTo(AndroidScreens().aboutRepository(infoRepository))
        }
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
