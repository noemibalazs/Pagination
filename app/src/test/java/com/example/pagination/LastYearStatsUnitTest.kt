package com.example.pagination

import com.example.pagination.data.LastYearStats
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LastYearStatsUnitTest {

    private lateinit var lastYear: LastYearStats

    @Before
    fun setUp(){
        lastYear = LastYearStats(12, 123456L, mutableListOf(1, 5, 2, 0, 0, 1, 3))
    }

    @Test
    fun testLastYearAreSame(){
        val myStats = LastYearStats(12, 123456L, mutableListOf(1, 5, 2, 0, 0, 1, 3))
        assertEquals(lastYear, myStats)
    }


    @Test
    fun testLastYearShouldBeDifferent(){
        val myStats = LastYearStats(9, 123456L, mutableListOf(1, 1, 2, 0, 0, 1, 3))
        assertNotEquals(lastYear, myStats)
    }

    @After
    fun tearDown(){
        stopKoin()
    }
}