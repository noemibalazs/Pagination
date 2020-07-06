package com.example.pagination.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(
    @field:SerializedName("id") val id: Int = 0,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("full_name") val full_name: String? = null,
    @field:SerializedName("description") val description: String? = null
) : Parcelable {

    override fun toString(): String {
        return "Repository(id=$id, name=$name, full_name=$full_name, description=$description)"
    }
}