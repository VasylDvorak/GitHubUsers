package com.popularlibraries.ui.presenters

import com.popularlibraries.ui.IItemView
import com.popularlibraries.ui.UserItemView

interface IListPresenter <V : IItemView> {
    var itemClickListener: ((V) -> Unit )?
    fun bindView (view: V)
    fun getCount (): Int }

interface IUserListPresenter : IListPresenter<UserItemView>
