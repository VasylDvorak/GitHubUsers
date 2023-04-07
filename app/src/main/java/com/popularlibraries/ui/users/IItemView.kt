package com.popularlibraries.ui.users

interface IItemView {
    var pos: Int
}
interface UserItemView : IItemView {
    fun setName (text: String )
    fun loadAvatar(url:String)
}
