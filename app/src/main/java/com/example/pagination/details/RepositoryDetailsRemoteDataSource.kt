package com.example.pagination.details

import androidx.lifecycle.LiveData
import com.example.pagination.data.LastYearStats
import com.example.pagination.data.RepositoryDetails
import com.example.pagination.data.RepositoryIssuesCounter
import com.example.pagination.helper.SingleLiveData

interface RepositoryDetailsRemoteDataSource {

    fun getRemoteRepositoryDetails(): LiveData<RepositoryDetails>

    fun getRemoteRepositoryIssueCounter(): LiveData<RepositoryIssuesCounter>

    fun getRemoteRepositoryLastYearStats(): LiveData<MutableList<LastYearStats>>

    val mutableFailureError: SingleLiveData<Boolean>
}