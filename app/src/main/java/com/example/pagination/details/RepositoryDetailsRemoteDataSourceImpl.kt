package com.example.pagination.details

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.pagination.R
import com.example.pagination.data.LastYearStats
import com.example.pagination.data.RepositoryDetails
import com.example.pagination.data.RepositoryIssuesCounter
import com.example.pagination.helper.DataManager
import com.example.pagination.helper.SingleLiveData
import com.example.pagination.network.GitHubApiService
import com.example.pagination.util.QUERY_ISSUE
import com.example.pagination.util.QUERY_TYPE
import com.orhanobut.logger.Logger
import kotlinx.coroutines.*
import org.koin.core.logger.KOIN_TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryDetailsRemoteDataSourceImpl(
    private val gitHubApiService: GitHubApiService,
    private val dataManager: DataManager,
    private val application: Application
) : RepositoryDetailsRemoteDataSource {

    private val failureError = SingleLiveData<Boolean>()

    override fun getRemoteRepositoryDetails(): LiveData<RepositoryDetails> {
        val repositoryDetails = MutableLiveData<RepositoryDetails>()
        CoroutineScope(Dispatchers.IO).launch {
            val result = gitHubApiService.getRepositoryDetails(dataManager.getRepositoryId())
            withContext(Dispatchers.Main) {
                try {
                    result.enqueue(object : Callback<RepositoryDetails> {
                        override fun onFailure(call: Call<RepositoryDetails>, t: Throwable) {
                            Logger.e(KOIN_TAG, "onFailure message: ${t.message}")
                        }

                        override fun onResponse(
                            call: Call<RepositoryDetails>,
                            response: Response<RepositoryDetails>
                        ) {
                            if (!response.isSuccessful) {
                                Logger.e(KOIN_TAG, "onResponse failure code: ${response.code()}")
                                failureError.call()
                            }

                            if (response.isSuccessful) {
                                Logger.d(KOIN_TAG, "onResponse is success!")
                                repositoryDetails.value = response.body()!!
                            }
                        }
                    })
                    if (!isActive) {
                        return@withContext
                    }
                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "Something went wrong, see message: ${e.message}")
                }
            }
        }
        return repositoryDetails
    }

    override fun getRemoteRepositoryIssueCounter(): LiveData<RepositoryIssuesCounter> {
        val repositoryIssuesCounter = MutableLiveData<RepositoryIssuesCounter>()
        CoroutineScope(Dispatchers.IO).launch {
            val queryParam =
                application.getString(R.string.query_param, dataManager.getRepositoryFullName())
            val result = gitHubApiService.getRepositoryIssues(queryParam, QUERY_TYPE, QUERY_ISSUE)
            withContext(Dispatchers.Main) {
                try {
                    result.enqueue(object : Callback<RepositoryIssuesCounter> {
                        override fun onFailure(call: Call<RepositoryIssuesCounter>, t: Throwable) {
                            Logger.e(KOIN_TAG, "onFailure error message: ${t.message}")
                        }

                        override fun onResponse(
                            call: Call<RepositoryIssuesCounter>,
                            response: Response<RepositoryIssuesCounter>
                        ) {
                            if (!response.isSuccessful) {
                                Logger.e(KOIN_TAG, "onResponse failure code: ${response.code()}")
                                failureError.call()
                            }

                            if (response.isSuccessful) {
                                Logger.d(KOIN_TAG, "onResponse successful")
                                repositoryIssuesCounter.value = response.body()
                            }
                        }
                    })
                    if (!isActive) {
                        return@withContext
                    }

                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "Something went wrong!")
                }
            }
        }
        return repositoryIssuesCounter
    }

    override fun getRemoteRepositoryLastYearStats(): LiveData<MutableList<LastYearStats>> {
        val lastYearStatsList = MutableLiveData<MutableList<LastYearStats>>()
        CoroutineScope(Dispatchers.IO).launch {
            val fullName = dataManager.getRepositoryFullName().split("/")
            val result =
                gitHubApiService.getRepositoryLastYearStats(fullName[0], fullName[1])
            withContext(Dispatchers.Main) {
                try {
                    result.enqueue(object : Callback<MutableList<LastYearStats>> {
                        override fun onFailure(
                            call: Call<MutableList<LastYearStats>>,
                            t: Throwable
                        ) {
                            Logger.e(KOIN_TAG, "onFailure message: ${t.message}")
                        }

                        override fun onResponse(
                            call: Call<MutableList<LastYearStats>>,
                            response: Response<MutableList<LastYearStats>>
                        ) {
                            if (!response.isSuccessful) {
                                Logger.e(KOIN_TAG, "onFailure code: ${response.code()}")
                                failureError.call()
                            }

                            if (response.isSuccessful) {
                                Logger.d(KOIN_TAG, "onResponse success!")
                                lastYearStatsList.value = response.body()
                            }
                        }
                    })

                    if (!isActive) {
                        return@withContext
                    }

                } catch (e: Exception) {
                    Logger.e(KOIN_TAG, "Something went wrong!")
                }
            }
        }
        return lastYearStatsList
    }

    override val mutableFailureError: SingleLiveData<Boolean>
        get() = failureError
}