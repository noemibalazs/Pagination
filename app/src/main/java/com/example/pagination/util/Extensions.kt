package com.example.pagination.util

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.paging.PagedList
import com.example.pagination.data.Repository

fun TextView.setSpannableText(text: String) {
    val spannableString = SpannableString(text)
    val index = text.indexOf(":")
    spannableString.setSpan(
        RelativeSizeSpan(1.1f),
        0,
        index + 1,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    spannableString.setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        index + 1,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    this.text = spannableString
}

fun PagedList<Repository>.parseToList(): MutableList<Repository> {
    val mutableList = mutableListOf<Repository>()
    this.map { repository -> mutableList.add(repository) }
    return mutableList
}

fun Context.openActivity(dest: Class<*>) {
    startActivity(Intent(this, dest))
}