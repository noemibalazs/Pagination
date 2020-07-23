package com.example.pagination.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class RepositoryIssuesCounter(
    @field:Json(name = "total_count") val total_count: Int = 0
) : Parcelable {
    override fun toString(): String {
        return "RepositoryIssuesCounter: total_count=$total_count"
    }
}