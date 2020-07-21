package com.example.pagination

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.pagination.data.RepositoryDetails
import com.example.pagination.details.RepositoryDetailsActivity
import com.example.pagination.util.setSpannableText
import kotlinx.android.synthetic.main.activity_repository_details.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RepositoryDetailsActivityInstrumentationTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(RepositoryDetailsActivity::class.java)


    @Test
    fun layoutItemsAreVisibleTest() {
        val activity = activityTestRule.activity
        val context = activity.applicationContext
        val repositoryDetails = RepositoryDetails(
            id = 12, name = "Anko", description = "That's all", size = 33, forks_count = 12,
            stargazers_count = 21, open_issues_count = 27, watchers_count = 69
        )
        activity.runOnUiThread {
            activity.tv_repo_name.setSpannableText(
                context.getString(
                    R.string.txt_repo_name,
                    repositoryDetails.name
                )
            )
            activity.tv_repo_description.setSpannableText(
                context.getString(
                    R.string.txt_repo_description,
                    repositoryDetails.description
                )
            )
            activity.tv_repo_size.setSpannableText(
                context.getString(
                    R.string.txt_repo_size,
                    repositoryDetails.size
                )
            )
            activity.tv_repo_watchers.setSpannableText(
                context.getString(
                    R.string.txt_repo_watchers,
                    repositoryDetails.watchers_count
                )
            )
            activity.tv_repo_stargazers.setSpannableText(
                context.getString(
                    R.string.txt_repo_stargazers,
                    repositoryDetails.stargazers_count
                )
            )
            activity.tv_repo_forks.setSpannableText(
                context.getString(
                    R.string.txt_repo_forks,
                    repositoryDetails.forks_count
                )
            )
            activity.tv_repo_open_issues_count.setSpannableText(
                context.getString(
                    R.string.txt_repo_open_issues_count,
                    repositoryDetails.open_issues_count
                )
            )
        }

        onView(withId(R.id.tv_repo_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).check(
                ViewAssertions.matches(
                    withText(
                        context.getString(
                            R.string.txt_repo_name,
                            repositoryDetails.name
                        )
                    )
                )
            )
        onView(withId(R.id.tv_repo_description))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).check(
                ViewAssertions.matches(
                    withText(
                        context.getString(
                            R.string.txt_repo_description,
                            repositoryDetails.description
                        )
                    )
                )
            )
        onView(withId(R.id.tv_repo_size))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).check(
                ViewAssertions.matches(
                    withText(
                        context.getString(
                            R.string.txt_repo_size,
                            repositoryDetails.size
                        )
                    )
                )
            )
        onView(withId(R.id.tv_repo_stargazers))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).check(
                ViewAssertions.matches(
                    withText(
                        context.getString(
                            R.string.txt_repo_stargazers,
                            repositoryDetails.stargazers_count
                        )
                    )
                )
            )
        onView(withId(R.id.tv_repo_watchers))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).check(
                ViewAssertions.matches(
                    withText(
                        context.getString(
                            R.string.txt_repo_watchers,
                            repositoryDetails.watchers_count
                        )
                    )
                )
            )
        onView(withId(R.id.tv_repo_forks))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).check(
                ViewAssertions.matches(
                    withText(
                        context.getString(
                            R.string.txt_repo_forks,
                            repositoryDetails.forks_count
                        )
                    )
                )
            )
        onView(withId(R.id.tv_repo_open_issues_count))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed())).check(
                ViewAssertions.matches(
                    withText(
                        context.getString(
                            R.string.txt_repo_open_issues_count,
                            repositoryDetails.open_issues_count
                        )
                    )
                )
            )
        onView(withId(R.id.tv_repos_stats))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(withText(R.string.txt_stats)))
    }
}