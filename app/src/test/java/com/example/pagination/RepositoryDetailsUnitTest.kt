package com.example.pagination

import com.example.pagination.data.RepositoryDetails
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RepositoryDetailsUnitTest {

    private lateinit var repositoryDetails: RepositoryDetails

    @Before
    fun setUp() {
        repositoryDetails = RepositoryDetails(
            12, "kotlin", "description",
            90, 12, 121, 63, 3
        )
    }

    @Test
    fun testRepositoryDetailsShouldBeSame() {
        val repoDetails = RepositoryDetails(
            12, "kotlin", "description",
            90, 12, 121, 63, 3
        )
        assertEquals(repositoryDetails, repoDetails)
    }

    @Test
    fun testRepositoryDetailsShouldBeDifferent() {
        val repoDetails = RepositoryDetails(
            12, "kotlin", "description",
            90, 12, 121, 63, 8
        )
        assertNotEquals(repositoryDetails, repoDetails)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

}
