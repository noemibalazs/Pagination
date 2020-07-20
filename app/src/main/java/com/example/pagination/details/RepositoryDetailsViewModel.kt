package com.example.pagination.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.pagination.data.LastYearStats
import com.example.pagination.data.RepositoryDetails
import com.example.pagination.data.RepositoryIssuesCounter
import com.example.pagination.util.REPOSITORY_DETAILS_SAVED_STATE
import com.example.pagination.util.REPOSITORY_ISSUES_COUNTER_SAVED_STATE
import com.example.pagination.util.REPOSITORY_LAST_YEAR_STATS_SAVED_STATE

class RepositoryDetailsViewModel(
    val handle: SavedStateHandle,
    private val repositoryDetailsRemoteDataSource: RepositoryDetailsRemoteDataSource
) : ViewModel() {

    val mutableFailureError = repositoryDetailsRemoteDataSource.mutableFailureError

    fun getRepositoryDetails() =
        repositoryDetailsRemoteDataSource.getRemoteRepositoryDetails()

    fun getRepositoryIssuesCount() =
        repositoryDetailsRemoteDataSource.getRemoteRepositoryIssueCounter()

    fun getRepositoryStats() =
        repositoryDetailsRemoteDataSource.getRemoteRepositoryLastYearStats()

    fun saveRepositoryDetailsDueConfChanges(repositoryDetails: RepositoryDetails) {
        handle.set(REPOSITORY_DETAILS_SAVED_STATE, repositoryDetails)
    }

    fun getRepositoryDetailsAfterConfChanges(): LiveData<RepositoryDetails> {
        return handle.getLiveData(REPOSITORY_DETAILS_SAVED_STATE)
    }

    fun saveRepositoryIssuesDueConfChanges(repositoryIssuesCounter: RepositoryIssuesCounter) {
        handle.set(REPOSITORY_ISSUES_COUNTER_SAVED_STATE, repositoryIssuesCounter)
    }

    fun getRepositoryIssuesAfterConfChanges(): LiveData<RepositoryIssuesCounter> {
        return handle.getLiveData(REPOSITORY_ISSUES_COUNTER_SAVED_STATE)
    }

    fun saveRepositoryLastYearStatsDueConfChanges(repositoryLastYearStats: MutableList<LastYearStats>) {
        handle.set(REPOSITORY_LAST_YEAR_STATS_SAVED_STATE, repositoryLastYearStats)
    }

    fun getRepositoryStatsAfterConfChanges(): LiveData<MutableList<LastYearStats>> {
        return handle.getLiveData(REPOSITORY_LAST_YEAR_STATS_SAVED_STATE)
    }
}