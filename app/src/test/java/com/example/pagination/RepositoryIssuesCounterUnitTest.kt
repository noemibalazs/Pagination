package com.example.pagination

import com.example.pagination.data.RepositoryIssuesCounter
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RepositoryIssuesCounterUnitTest {

    private lateinit var issuesCounter: RepositoryIssuesCounter

    @Before
    fun setUp(){
        issuesCounter = RepositoryIssuesCounter(12)
    }

    @Test
    fun testRepositoryIssuesShouldBeSame(){
        val issues = RepositoryIssuesCounter(12)
        assertEquals(issues, issuesCounter)
    }

    @Test
    fun testRepositoryIssuesShouldBeDifferent(){
        val issues = RepositoryIssuesCounter(9)
        assertNotEquals(issues, issuesCounter)
    }

    @After
    fun tearDown(){
        stopKoin()
    }
}