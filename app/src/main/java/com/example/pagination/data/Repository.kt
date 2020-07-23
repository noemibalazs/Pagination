package com.example.pagination.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Repository(
    @field:Json(name = "id") val id: Int = 0,
    @field:Json(name = "name") val name: String? = null,
    @field:Json(name = "full_name") val full_name: String? = null,
    @field:Json(name = "description") val description: String? = null
) : Parcelable {

    override fun toString(): String {
        return "Repository(id=$id, name=$name, full_name=$full_name, description=$description)"
    }
}