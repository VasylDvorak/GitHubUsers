package com.popularlibraries.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.popularlibraries.R
import com.popularlibraries.data.GithubUsersRepo
import com.popularlibraries.databinding.FragmentAboutUserBinding
import com.popularlibraries.databinding.FragmentUsersBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

const val LOGIN = "login"

class AboutUserFragment : Fragment(){

    private var binding: FragmentAboutUserBinding? = null
    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?,
                               savedInstanceState: Bundle?) =
        FragmentAboutUserBinding.inflate(inflater, container, false ).also {
            binding = it
        }.root
    override fun onDestroyView () {
        super.onDestroyView()
        binding= null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val login: String = (arguments?.getString(LOGIN) ?: String) as String
        binding?.usersLogin?.text=login
    }
    companion object {
        fun newInstance(bundle: Bundle): AboutUserFragment {
            val fragment = AboutUserFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}