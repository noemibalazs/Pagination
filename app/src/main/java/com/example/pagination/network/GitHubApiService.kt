package com.example.pagination.network

import com.example.pagination.data.KotlinRepositories
import com.example.pagination.data.LastYearStats
import com.example.pagination.data.RepositoryDetails
import com.example.pagination.data.RepositoryIssuesCounter
import com.example.pagination.util.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {

    @GET("search/repositories?q=language:kotlin&sort=starts")
    fun getRepositoryList(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<KotlinRepositories>

    @GET("repositories/{id}")
    fun getRepositoryDetails(@Path("id") id: Int): Call<RepositoryDetails>

    @GET("search/issues?")
    fun getRepositoryIssues(
        @Query("q") queryParameter: String,
        @Query("created") created: String,
        @Query("type") type: String
    ): Call<RepositoryIssuesCounter>

    @GET("repos/{name}/{repo}/stats/commit_activity")
    fun getRepositoryLastYearStats(
        @Path("name") name: String,
        @Path("repo") repo: String
    ): Call<MutableList<LastYearStats>>

    companion object {

        fun getGitHubAPI(): GitHubApiService {
            val interceptor =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(client)
                .build().create(GitHubApiService::class.java)
        }
    }
}