package com.example.pagination.helper

import android.os.SystemClock
import android.view.View
import java.util.*
import kotlin.math.abs

abstract class DebounceClickListener(private val timeThreshold: Long = 900) : View.OnClickListener {

    private val myMap: MutableMap<View, Long>

    abstract fun onDebounce(view: View)

    init {
        myMap = WeakHashMap<View, Long>()
    }

    override fun onClick(view: View) {
        val previousTimeStamp = myMap[view]
        val currentTimeStamp = SystemClock.uptimeMillis()
        myMap[view] = currentTimeStamp
        if (previousTimeStamp == null || abs(previousTimeStamp - currentTimeStamp.toLong()) > timeThreshold)
            onDebounce(view)
    }
}