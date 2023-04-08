package com.popularlibraries.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.popularlibraries.App
import com.popularlibraries.databinding.FragmentUsersBinding
import com.popularlibraries.di.user.UserSubcomponent
import com.popularlibraries.ui.interfaces.BackButtonListener
import com.popularlibraries.ui.interfaces.UsersView
import com.popularlibraries.ui.presenters.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {


    companion object {
        fun newInstance() = UsersFragment()
    }

    private var userSubcomponent: UserSubcomponent? = null

    private val presenter: UsersPresenter by moxyPresenter {
        userSubcomponent = App.instance.initUserSubcomponent()
        UsersPresenter().apply {
            userSubcomponent?.inject(this)
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
            adapter = UsersRVAdapter(presenter.usersListPresenter).apply {
                userSubcomponent?.inject(this)
            }
            vb?.rvUsers?.adapter = adapter
        }
    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun release() {
        userSubcomponent = null
        App.instance.releaseRepositorySubComponent()
    }

    override fun backPressed() = presenter.backPressed()

}
