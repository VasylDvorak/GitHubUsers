package com.popularlibraries.ui

interface IItemView {
    var pos: Int
}
interface UserItemView : IItemView {
    fun setLogin (text: String )
    fun getLogin() : String
}
