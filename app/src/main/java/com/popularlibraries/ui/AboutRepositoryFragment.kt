package com.popularlibraries.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.popularlibraries.R
import com.popularlibraries.entity.GithubRepository
import com.popularlibraries.databinding.FragmentAboutRepositoryBinding


const val REPOSITORY = "repository"

class AboutRepositoryFragment : Fragment() {


    private var binding: FragmentAboutRepositoryBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentAboutRepositoryBinding.inflate(inflater, container, false).also {
            binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val repository = arguments?.getParcelable(REPOSITORY) as GithubRepository?

        repository?.apply {
            binding?.apply {

                repositoryId.text = getString(R.string.id) + id
                repositoryName.text = getString(R.string.repos_information) + name
                forks.text = getString(R.string.fork_count) + forksCount
            }
        }
    }


    companion object {
        fun newInstance(bundle: Bundle): AboutRepositoryFragment {
            val fragment = AboutRepositoryFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}