package com.example.pagination.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryIssuesCounter(
    @field:SerializedName("total_count") val total_count: Int = 0
) : Parcelable {
    override fun toString(): String {
        return "RepositoryIssuesCounter: total_count=$total_count"
    }

}