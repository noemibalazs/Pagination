package com.example.pagination.helper

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class UIThreadExecutor : Executor {

    private val handler = Handler(Looper.getMainLooper())

    override fun execute(runnable: Runnable) {
        handler.post(runnable)
    }
}