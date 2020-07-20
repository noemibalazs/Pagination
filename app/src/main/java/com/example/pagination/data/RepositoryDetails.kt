package com.example.pagination.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryDetails(
    @field:SerializedName("id") val id: Int = 0,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("description") val description: String? = null,
    @field:SerializedName("size") val size: Int = 0,
    @field:SerializedName("stargazers_count") val stargazers_count: Int = 0,
    @field:SerializedName("watchers_count") val watchers_count: Int = 0,
    @field:SerializedName("forks_count") val forks_count: Int = 0,
    @field:SerializedName("open_issues_count") val open_issues_count: Int = 0
) : Parcelable {
    override fun toString(): String {
        return "RepositoryDetails: id=$id, name=$name, description=$description, size=$size, stargazers_count=$stargazers_count, watchers_count=$watchers_count, forks_count=$forks_count, open_issues_count=$open_issues_count"
    }

}