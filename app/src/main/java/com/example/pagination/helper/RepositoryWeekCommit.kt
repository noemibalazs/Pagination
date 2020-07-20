package com.example.pagination.helper

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.pagination.R
import kotlinx.android.synthetic.main.repository_week_commit.view.*

class RepositoryWeekCommit : ConstraintLayout {

    var colorOrNot = false

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

        colorOrNot = typedArray.getBoolean(R.styleable.RepositoryWeekCommit_colorOrNot, false)
        typedArray.recycle()

        colorOrNot()
    }

    private fun colorOrNot() {
        val color = ContextCompat.getColor(context, R.color.colorPrimary)
        if (colorOrNot) {
            this.tv_week.setTextColor(color)
            this.tv_sunday.setTextColor(color)
            this.tv_monday.setTextColor(color)
            this.tv_tuesday.setTextColor(color)
            this.tv_wednesday.setTextColor(color)
            this.tv_thursday.setTextColor(color)
            this.tv_friday.setTextColor(color)
            this.tv_saturday.setTextColor(color)
        }
    }

    fun showOrHide(show: Boolean) {
        this.isVisible = show
    }
}