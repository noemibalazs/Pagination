package com.example.pagination.helper

class BindingAdapter {

    companion object {
        @androidx.databinding.BindingAdapter("toShowOrHide")
        fun toShowOrHide(view: RepositoryWeekCommit, show: Boolean) {
            view.showOrHide(show)
        }
    }
}