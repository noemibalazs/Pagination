package com.example.pagination.helper

import kotlinx.android.synthetic.main.repository_week_commit.view.*

class BindingAdapter {

    companion object {
        @androidx.databinding.BindingAdapter("setColor")
        fun setColor(view: RepositoryWeekCommit, set: Boolean) {
            if (set) {
                view.apply {
                    tv_week.setTextColor(view.color)
                    tv_sunday.setTextColor(view.color)
                    tv_monday.setTextColor(view.color)
                    tv_tuesday.setTextColor(view.color)
                    tv_wednesday.setTextColor(view.color)
                    tv_thursday.setTextColor(view.color)
                    tv_friday.setTextColor(view.color)
                    tv_saturday.setTextColor(view.color)
                }
            } else {
                view.apply {
                    tv_week.setTextColor(view.defaultColor)
                    tv_sunday.setTextColor(view.defaultColor)
                    tv_monday.setTextColor(view.defaultColor)
                    tv_tuesday.setTextColor(view.defaultColor)
                    tv_wednesday.setTextColor(view.defaultColor)
                    tv_thursday.setTextColor(view.defaultColor)
                    tv_friday.setTextColor(view.defaultColor)
                    tv_saturday.setTextColor(view.defaultColor)
                }
            }
        }
    }
}