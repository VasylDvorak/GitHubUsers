package com.popularlibraries.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.popularlibraries.App
import com.popularlibraries.databinding.FragmentRepositoriesBinding
import com.popularlibraries.di.repository.RepositorySubcomponent

import com.popularlibraries.entity.GithubUser
import com.popularlibraries.ui.interfaces.BackButtonListener
import com.popularlibraries.ui.interfaces.UsersView
import com.popularlibraries.ui.presenters.RepositoriesPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

const val CURRENT_USER = "current_user"

class RepositoriesFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private lateinit var currentUser: GithubUser

    companion object {
        fun newInstance(bundle: Bundle): RepositoriesFragment {
            val fragment = RepositoriesFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _vb: FragmentRepositoriesBinding? = null
    private val vb
        get() = _vb!!

    private var repositorySubcomponent: RepositorySubcomponent? = null
    private val presenter: RepositoriesPresenter by moxyPresenter {

        repositorySubcomponent = App.instance.initRepositorySubcomponent()
        RepositoriesPresenter().apply {
            repositorySubcomponent?.inject(this)
        }
    }

    var adapter: RepositoriesRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        currentUser = (arguments?.getParcelable(CURRENT_USER) as GithubUser?)!!
        _vb = FragmentRepositoriesBinding.inflate(inflater, container, false)

        return vb.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }


    override fun init() {
        vb.apply {
            rvRepositories.layoutManager = LinearLayoutManager(context)
            currentUser.let { presenter.loadRepositories(it) }
            adapter = RepositoriesRVAdapter(presenter.repositoriesListPresenter)
            vb.rvRepositories.adapter = adapter
        }
    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        repositorySubcomponent = null
        App.instance.releaseRepositorySubComponent()
    }

    override fun backPressed() = presenter.backPressed()

}
