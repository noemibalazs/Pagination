package com.example.pagination.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class RepositoryDetails(
    @field:Json(name = "id") val id: Int = 0,
    @field:Json(name = "name") val name: String? = null,
    @field:Json(name = "description") val description: String? = null,
    @field:Json(name = "size") val size: Int = 0,
    @field:Json(name = "stargazers_count") val stargazers_count: Int = 0,
    @field:Json(name = "watchers_count") val watchers_count: Int = 0,
    @field:Json(name = "forks_count") val forks_count: Int = 0,
    @field:Json(name = "open_issues_count") val open_issues_count: Int = 0
) : Parcelable {
    override fun toString(): String {
        return "RepositoryDetails: id=$id, name=$name, description=$description, size=$size, stargazers_count=$stargazers_count, watchers_count=$watchers_count, forks_count=$forks_count, open_issues_count=$open_issues_count"
    }

}