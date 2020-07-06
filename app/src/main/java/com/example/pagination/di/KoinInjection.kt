package com.example.pagination.di

import org.koin.core.module.Module

class KoinInjection {

    companion object {

        fun getKoinModules(): MutableList<Module> {

            fun getNetworkModule() = listOf(networkModule)
            fun getRepositoryViewModelModule() = listOf(repositoryViewModelModule)
            fun getDataManagerModule() = listOf(dataManagerModule)

            return mutableListOf<Module>().apply {
                addAll(getNetworkModule())
                addAll(getRepositoryViewModelModule())
                addAll(getDataManagerModule())
            }
        }
    }
}