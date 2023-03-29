package com.popularlibraries.ui.presenters

import com.popularlibraries.ui.users.IItemView
import com.popularlibraries.ui.users.UserItemView

interface IListPresenter <V : IItemView> {

    var itemClickListener: ((V) -> Unit )?
    fun bindView (view: V)
    fun getCount (): Int }

interface IUserListPresenter : IListPresenter<UserItemView>

