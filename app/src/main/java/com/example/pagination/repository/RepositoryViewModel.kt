package com.example.pagination.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.pagination.data.Repository
import com.example.pagination.util.PAGE_SIZE
import com.example.pagination.util.REPOSITORY_LIST_SAVED_STATE

class RepositoryViewModel(
    val handle: SavedStateHandle,
    val repositoryDataSourceFactory: RepositoryDataSourceFactory
) : ViewModel() {

    val repositoryList: LiveData<PagedList<Repository>>

    init {

        val config =
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build()
        repositoryList = LivePagedListBuilder(repositoryDataSourceFactory, config).build()
    }

    fun saveRepositoryListDueConfChanges(pagedList: MutableList<Repository>) {
        handle.set(REPOSITORY_LIST_SAVED_STATE, pagedList)
    }

    fun getRepositoryListAfterConfChanges(): LiveData<MutableList<Repository>> {
        return handle.getLiveData(REPOSITORY_LIST_SAVED_STATE)
    }
}