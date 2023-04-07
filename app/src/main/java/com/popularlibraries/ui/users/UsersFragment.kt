package com.popularlibraries.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.popularlibraries.App
import com.popularlibraries.databinding.FragmentUsersBinding
import com.popularlibraries.ui.image.GlideImageLoader
import com.popularlibraries.ui.interfaces.BackButtonListener
import com.popularlibraries.ui.interfaces.UsersView
import com.popularlibraries.ui.presenters.UsersPresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {


    companion object {
        fun newInstance() = UsersFragment()
    }


    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
            AndroidSchedulers.mainThread(),
            ).apply {
            App.instance.appComponent.inject(this)
        }

    }
    var adapter: UsersRVAdapter? = null
    private var vb: FragmentUsersBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentUsersBinding.inflate(inflater, container, false).also {
            vb = it
        }.root


    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
        vb?.apply {
            rvUsers.layoutManager = LinearLayoutManager(context)
            adapter = UsersRVAdapter(presenter.usersListPresenter)
            vb?.rvUsers?.adapter = adapter
        }
    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

}
