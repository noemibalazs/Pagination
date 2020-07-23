package com.example.pagination.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class LastYearStats(
    @field:Json(name = "total") val total: Int = 0,
    @field:Json(name = "week") val week: Long = 0,
    @field:Json(name = "days") val days: MutableList<Int>
) : Parcelable {

    override fun toString(): String {
        return "LastYearStats: total=$total, week=$week, days=$days"
    }
}