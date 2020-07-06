package com.example.pagination.di

import androidx.lifecycle.SavedStateHandle
import androidx.paging.PageKeyedDataSource
import com.example.pagination.data.Repository
import com.example.pagination.helper.DataManager
import com.example.pagination.network.GitHubApiService
import com.example.pagination.repository.RepositoryDataSource
import com.example.pagination.repository.RepositoryDataSourceFactory
import com.example.pagination.repository.RepositoryViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { GitHubApiService.getGitHubAPI() }
}

val repositoryViewModelModule = module {

    single { RepositoryDataSource(gitHubApiService = get()) }

    single { RepositoryDataSourceFactory(repositoryDataSource = get()) }

    viewModel { (handle: SavedStateHandle) ->
        RepositoryViewModel(
            handle = handle,
            repositoryDataSourceFactory = get()
        )
    }
}

val dataManagerModule = module {
    single { DataManager(context = androidApplication().applicationContext) }
}