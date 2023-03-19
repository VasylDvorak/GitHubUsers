package com.popularlibraries.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.popularlibraries.databinding.ItemUserBinding
import com.popularlibraries.ui.presenters.IUserListPresenter

class UsersRVAdapter (val presenter: IUserListPresenter) :

    RecyclerView.Adapter<UsersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int ) =
        ViewHolder(
            ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false )).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke( this ) }
        }

    override fun getItemCount () = presenter.getCount()

    override fun onBindViewHolder (holder: ViewHolder , position: Int ) =
        presenter.bindView(holder.apply { pos = position })
    inner class ViewHolder (val vb: ItemUserBinding) :
        RecyclerView.ViewHolder(vb.root), UserItemView {
        override var pos = -1
        override fun setLogin (text: String ) = with(vb) {
            tvLogin.text = text
        }

        override fun getLogin(): String {
          return vb.tvLogin.text.toString()
        }
    }
}