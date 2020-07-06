package com.example.pagination.helper

import android.content.Context
import com.example.pagination.util.MY_PREFERENCES
import com.example.pagination.util.REPOSITORY_FULL_NAME
import com.example.pagination.util.REPOSITORY_ID

class DataManager(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE)

    fun saveRepositoryID(id: Int) {
        sharedPreferences.edit().putInt(REPOSITORY_ID, id).apply()
    }

    fun getRepositoryID(): Int {
        return sharedPreferences.getInt(REPOSITORY_ID, 0)
    }

    fun saveRepositoryFullName(fullName: String) {
        sharedPreferences.edit().putString(REPOSITORY_FULL_NAME, fullName).apply()
    }

    fun getRepositoryFullName(): String {
        return sharedPreferences.getString(REPOSITORY_FULL_NAME, "") ?: ""
    }
}