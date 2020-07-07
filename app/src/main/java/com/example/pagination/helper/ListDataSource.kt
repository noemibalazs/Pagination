package com.example.pagination.helper

import androidx.paging.PositionalDataSource
import com.example.pagination.data.Repository

class ListDataSource(private val repositoryList: MutableList<Repository>) :
    PositionalDataSource<Repository>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Repository>) {
        callback.onResult(repositoryList, 0, repositoryList.size)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Repository>) {
        val start = params.startPosition
        val end = start + params.loadSize
        callback.onResult(repositoryList.subList(start, end))
    }
}