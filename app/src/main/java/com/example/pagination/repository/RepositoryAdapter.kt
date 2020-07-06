package com.example.pagination.repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.example.pagination.R
import com.example.pagination.data.Repository
import com.example.pagination.databinding.ItemRepositoryBinding

class RepositoryAdapter(private val repositoryViewModel: RepositoryViewModel) :
    PagedListAdapter<Repository, RepositoryVH>(RepositoryDiffCallback()) {

    var clickListener: RepositoryClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryVH {
        val binding: ItemRepositoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_repository,
            parent, false
        )
        return RepositoryVH(binding, repositoryViewModel, clickListener)
    }

    override fun onBindViewHolder(holder: RepositoryVH, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    fun removeListener() {
        clickListener == null
    }
}