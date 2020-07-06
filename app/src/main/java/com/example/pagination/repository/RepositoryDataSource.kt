package com.example.pagination.repository

import androidx.paging.PageKeyedDataSource
import com.example.pagination.data.KotlinRepositories
import com.example.pagination.data.Repository
import com.example.pagination.network.GitHubApiService
import com.example.pagination.util.FIRST_PAGE
import com.example.pagination.util.NEXT
import com.example.pagination.util.PAGE_SIZE
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.logger.KOIN_TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryDataSource(private val gitHubApiService: GitHubApiService) :
    PageKeyedDataSource<Int, Repository>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Repository>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = gitHubApiService.getRepositoryList(FIRST_PAGE, PAGE_SIZE)
            withContext(Dispatchers.Main) {
                try {
                    result.enqueue(object : Callback<KotlinRepositories> {
                        override fun onFailure(call: Call<KotlinRepositories>, t: Throwable) {
                            Logger.e(KOIN_TAG, "loadInitial onFailure error message: ${t.message}")
                        }

                        override fun onResponse(
                            call: Call<KotlinRepositories>,
                            response: Response<KotlinRepositories>
                        ) {
                            if (!response.isSuccessful) {
                                Logger.d(
                                    KOIN_TAG,
                                    "loadInitial onResponse error, see the error code: ${response.code()}"
                                )
                            }
                            if (response.isSuccessful) {
                                Logger.d(KOIN_TAG, "loadInitial onResponse is successful")
                                callback.onResult(response.body()!!.items, null, FIRST_PAGE + 1)
                            }
                        }
                    })
                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "loadInitial - Something went really wrong!")
                }
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = gitHubApiService.getRepositoryList(params.key, PAGE_SIZE)
            withContext(Dispatchers.Main) {
                try {
                    result.enqueue(object : Callback<KotlinRepositories> {
                        override fun onFailure(call: Call<KotlinRepositories>, t: Throwable) {
                            Logger.e(KOIN_TAG, "loadAfter onFailure error message: ${t.message}")
                        }

                        override fun onResponse(
                            call: Call<KotlinRepositories>,
                            response: Response<KotlinRepositories>
                        ) {
                            if (!response.isSuccessful) {
                                Logger.d(
                                    KOIN_TAG,
                                    "loadAfter onResponse error, see the error code: ${response.code()}"
                                )
                            }
                            if (response.isSuccessful) {
                                Logger.d(KOIN_TAG, "loadAfter onResponse is successful")
                                val nextRel = response.headers()["link"]
                                Logger.d(KOIN_TAG, "loadAfter - see header: $nextRel")
                                nextRel?.let {
                                    callback.onResult(
                                        response.body()!!.items,
                                        if (it.contains(NEXT)) params.key + 1 else null
                                    )
                                }
                            }
                        }
                    })

                } catch (e: java.lang.Exception) {
                    Logger.e(KOIN_TAG, "loadAfter - Something went really wrong!")
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = gitHubApiService.getRepositoryList(params.key, PAGE_SIZE)
            withContext(Dispatchers.Main) {
                try {
                    result.enqueue(object : Callback<KotlinRepositories> {
                        override fun onFailure(call: Call<KotlinRepositories>, t: Throwable) {
                            Logger.e(KOIN_TAG, "loadBefore onFailure error message: ${t.message}")
                        }

                        override fun onResponse(
                            call: Call<KotlinRepositories>,
                            response: Response<KotlinRepositories>
                        ) {
                            if (!response.isSuccessful) {
                                Logger.d(
                                    KOIN_TAG,
                                    "loadBefore onResponse error, see the error code: ${response.code()}"
                                )
                            }
                            if (response.isSuccessful) {
                                Logger.d(KOIN_TAG, "loadBefore onResponse is successful")
                                val key = if (params.key > 1) params.key - 1 else null
                                callback.onResult(response.body()!!.items, key)
                            }
                        }

                    })
                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "loadBefore - Something went really wrong!")
                }
            }

        }
    }
}