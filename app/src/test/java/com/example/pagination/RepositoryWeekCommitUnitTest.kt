package com.example.pagination

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.example.pagination.helper.RepositoryWeekCommit
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.mockito.junit.MockitoJUnit
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class RepositoryWeekCommitUnitTest {

    @get:Rule
    val rule = MockitoJUnit.rule()

    private lateinit var context: Context
    private lateinit var repositoryWeekCommit: RepositoryWeekCommit

    @Before
    fun setUp() {
        context = RuntimeEnvironment.application.applicationContext
        repositoryWeekCommit = RepositoryWeekCommit(context)
    }

    @Test
    fun testColorOrNotIsTrueSameWithExpectedResult() {
        repositoryWeekCommit.colorOrNot = true
        assertEquals(true, repositoryWeekCommit.colorOrNot)
    }

    @Test
    fun testColorOrNotIsFalseNotSameWithExpectedResult() {
        repositoryWeekCommit.colorOrNot = false
        assertNotEquals(true, repositoryWeekCommit.colorOrNot)
    }

    @Test
    fun testColorOrNotIsFalseSameWithExpectedResult() {
        repositoryWeekCommit.colorOrNot = false
        assertEquals(false, repositoryWeekCommit.colorOrNot)
    }

    @Test
    fun testColorOrNotIsTrueNotSameWithExpectedResult() {
        repositoryWeekCommit.colorOrNot = true
        assertNotEquals(false, repositoryWeekCommit.colorOrNot)
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}