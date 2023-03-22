package com.popularlibraries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.popularlibraries.App
import com.popularlibraries.databinding.FragmentUsersBinding
import com.popularlibraries.ui.interfaces.BackButtonListener
import com.popularlibraries.ui.interfaces.UsersView
import com.popularlibraries.ui.presenters.UsersPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }


    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(App.instance.router, AndroidScreens())
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
        rvUsers?.layoutManager = LinearLayoutManager(context)
        switchMap?.setOnClickListener {
            switchMapDescription?.visibility=View.VISIBLE
            initAdapter(true)
        }

        concatMap?.setOnClickListener {
            switchMapDescription?.visibility=View.GONE
            initAdapter(false)
        }
    }
    }

    private fun initAdapter(switch_map: Boolean) {
        presenter.loadData(switch_map)
        adapter = UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

}
