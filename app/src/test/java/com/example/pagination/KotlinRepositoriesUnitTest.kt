package com.example.pagination

import com.example.pagination.data.KotlinRepositories
import com.example.pagination.data.Repository
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class KotlinRepositoriesUnitTest {

    private lateinit var kotlinRepositories: KotlinRepositories

    @Before
    fun setUp() {
        kotlinRepositories = KotlinRepositories(
            total_count = 2,
            items = mutableListOf(
                Repository(1, "android", "kotlin/android", "kotlin for android"),
                Repository(2, "canary", "canaryleak", "memory leak")
            )
        )
    }

    @Test
    fun testRepositoriesShouldBeSame() {
        val myList = KotlinRepositories(
            total_count = 2,
            items = mutableListOf(
                Repository(1, "android", "kotlin/android", "kotlin for android"),
                Repository(2, "canary", "canaryleak", "memory leak")
            )
        )

        assertEquals(myList, kotlinRepositories)
    }

    @Test
    fun testRepositoriesShouldBeDifferent(){
        val myList = KotlinRepositories(
            total_count = 21,
            items = mutableListOf(
                Repository(1, "android", "kotlin/android", "kotlin for android"),
                Repository(2, "canary", "canaryleak", "memory leak")
            )
        )

        assertNotEquals(myList, kotlinRepositories)
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}