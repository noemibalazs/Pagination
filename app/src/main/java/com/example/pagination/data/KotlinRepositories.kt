package com.example.pagination.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class KotlinRepositories(
    @field:Json(name = "total_count") val total_count: Int = 0,
    @field:Json(name = "items") val items: MutableList<Repository>
) {

    override fun toString(): String {
        return "KotlinRepositories total_count: $total_count, items: ${items.size}"
    }
}