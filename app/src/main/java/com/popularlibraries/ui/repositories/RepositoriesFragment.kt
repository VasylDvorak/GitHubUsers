package com.popularlibraries.ui.repositories

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.popularlibraries.domain.api.ApiHolder
import com.popularlibraries.App
import com.popularlibraries.databinding.FragmentRepositoriesBinding
import com.popularlibraries.domain.repo.retrofit.RetrofitGithubUsersRepo
import com.popularlibraries.ui.AndroidScreens
import com.popularlibraries.ui.interfaces.BackButtonListener
import com.popularlibraries.ui.interfaces.UsersView
import com.popularlibraries.ui.presenters.RepositoriesPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

const val URL_REQUEST = "url"

class RepositoriesFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private lateinit var url: String

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


    private val presenter: RepositoriesPresenter by moxyPresenter {
        RepositoriesPresenter(
            AndroidSchedulers.mainThread(),
            RetrofitGithubUsersRepo(ApiHolder().api),
            App.instance.router, AndroidScreens()
        )
    }
    var adapter: RepositoriesRVAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        url = arguments?.getString(URL_REQUEST).toString()

        _vb = FragmentRepositoriesBinding.inflate(inflater, container, false)

        return vb.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    @SuppressLint("SuspiciousIndentation")
    override fun init() {
        vb.apply {
            rvRepositories.layoutManager = LinearLayoutManager(context)
            presenter.loadRepositories(url)
            adapter = RepositoriesRVAdapter(presenter.repositoriesListPresenter)
            vb.rvRepositories?.adapter = adapter
        }
    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

}
