package com.popularlibraries.ui.presenters

import com.popularlibraries.domain.repo.ReposItemView
import com.popularlibraries.domain.repo.RepositoryItemView


interface RepositoriesListPresenter <V : RepositoryItemView> {

    var itemClickListener: ((V) -> Unit )?
    fun bindView (view: V)
    fun getCount (): Int }


interface RepositListPresenter : RepositoriesListPresenter<ReposItemView>
