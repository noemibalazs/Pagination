package com.example.pagination

import com.example.pagination.data.Repository
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class RepositoryUnitTest {

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = Repository(
            id = 1,
            name = "kotlin",
            full_name = "jetbrains/kotlin",
            description = "best_oop_language"
        )
    }

    @Test
    fun testRepositoryShouldBeSame() {
        val repos = Repository(
            1,
            "kotlin",
            "jetbrains/kotlin",
            "best_oop_language"
        )

        assertEquals(repository, repos)
    }

    @Test
    fun testRepositoryShouldBeDifferent() {
        val repos = Repository(
            12,
            "kotlin",
            "jetbrains/kotlin",
            "best_oop_language"
        )

        assertNotEquals(repository, repos)
    }

    @After
    fun tearDown(){
        stopKoin()
    }
}