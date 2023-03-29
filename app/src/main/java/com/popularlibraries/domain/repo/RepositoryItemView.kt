package com.popularlibraries.domain.repo


interface RepositoryItemView {
    var pos: Int
}
interface ReposItemView : RepositoryItemView  {
    fun setName (text: String? )
}