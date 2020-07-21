package com.example.pagination

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.pagination.details.RepositoryDetailsActivity
import com.example.pagination.helper.RepositoryWeekCommit
import org.hamcrest.CoreMatchers.*

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RepositoryWeekCommitInstrumentationTest {

    @get:Rule
    val rule = ActivityTestRule(RepositoryDetailsActivity::class.java)

    @Test
    fun testCustomViewIsNotVisible() {
        val activity = rule.activity
        activity.runOnUiThread {
            activity.findViewById<RepositoryWeekCommit>(R.id.rwc_first_week).showOrHide(false)
            activity.findViewById<RepositoryWeekCommit>(R.id.rwc_last_week).showOrHide(false)
        }

        onView(withId(R.id.rwc_first_week)).check(matches(not(isDisplayed())))
        onView(withId(R.id.rwc_last_week)).check(matches(not(isDisplayed())))
    }


    @Test
    fun testCustomViewIsVisible() {
        val activity = rule.activity
        activity.runOnUiThread {
            activity.findViewById<RepositoryWeekCommit>(R.id.rwc_first_week).showOrHide(true)
            activity.findViewById<RepositoryWeekCommit>(R.id.rwc_last_week).showOrHide(true)
        }

        onView(withId(R.id.rwc_first_week)).check(matches((isDisplayed())))
        onView(withId(R.id.rwc_last_week)).check(matches(isDisplayed()))
    }
}