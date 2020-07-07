package com.example.pagination.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.pagination.data.Repository

class RepositoryDataSourceFactory(
    val repositoryDataSource: RepositoryDataSource
) : DataSource.Factory<Int, Repository>() {

    private val repositoryDataSourceLiveData = MutableLiveData<RepositoryDataSource>()

    override fun create(): DataSource<Int, Repository> {
        repositoryDataSourceLiveData.postValue(repositoryDataSource)
        return repositoryDataSource
    }
}