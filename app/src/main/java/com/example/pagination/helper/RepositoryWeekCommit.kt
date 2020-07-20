package com.example.pagination.helper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.pagination.R

class RepositoryWeekCommit : ConstraintLayout {

    var color = 0
    var defaultColor = 0

    constructor(context: Context) : super(context) {
        init(null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.repository_week_commit, this, true)
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.RepositoryWeekCommit)
        color = typedArray.getColor(
            R.styleable.RepositoryWeekCommit_textColor,
            ContextCompat.getColor(context, R.color.colorPrimary)
        )
        defaultColor = typedArray.getColor(
            R.styleable.RepositoryWeekCommit_defaultTextColor,
            ContextCompat.getColor(context, R.color.colorPrimaryDark)
        )
        typedArray.recycle()
    }
}