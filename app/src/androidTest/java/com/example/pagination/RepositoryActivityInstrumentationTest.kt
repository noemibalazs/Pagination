package com.example.pagination

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.pagination.data.Repository
import com.example.pagination.helper.ListDataSource
import com.example.pagination.helper.ListProvider
import com.example.pagination.repository.RepositoryActivity
import com.example.pagination.repository.RepositoryAdapter
import com.example.pagination.repository.RepositoryVH
import com.example.pagination.repository.RepositoryViewModel
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RepositoryActivityInstrumentationTest {

    private lateinit var viewModel: RepositoryViewModel

    @get:Rule
    val repositoryActivityRule = ActivityTestRule(RepositoryActivity::class.java)

    @Before
    fun setUp() {
        viewModel = mockk<RepositoryViewModel>()
    }

    @Test
    fun testClickOnRecycleViewItem() {
        val repositoryList =
            mutableListOf(Repository(12, "kotlin", "JetBrain/kotlin", "Lorem ipsum dolor sit amet"))
        val listDataSource = ListDataSource(repositoryList)
        val pagedList = ListProvider(listDataSource).getPagedList()
        val repositoryAdapter = RepositoryAdapter(viewModel)
        repositoryAdapter.submitList(pagedList)
        repositoryActivityRule.activity.runOnUiThread {
            repositoryActivityRule.activity.findViewById<RecyclerView>(R.id.rv_repository).adapter =
                repositoryAdapter
        }

        onView(withId(R.id.rv_repository)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_repository)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RepositoryVH>(
                0, click()
            )
        )
    }
}