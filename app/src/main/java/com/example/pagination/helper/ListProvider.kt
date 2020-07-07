package com.example.pagination.helper

import android.os.AsyncTask
import androidx.paging.PagedList
import com.example.pagination.data.Repository
import com.example.pagination.util.PAGE_SIZE

class ListProvider(private val listDataSource: ListDataSource) {

    fun getPagedList(): PagedList<Repository> {
        val config = PagedList.Config.Builder().setPageSize(PAGE_SIZE).build()
        return PagedList.Builder(listDataSource, config)
            .setInitialKey(0)
            .setNotifyExecutor(UIThreadExecutor())
            .setFetchExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            .build()
    }
}