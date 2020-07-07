package com.example.pagination

import com.example.pagination.repository.RepositoryActivity
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest = Config.NONE)
class RepositoryActivityUnitTest {

    private var repositoryActivity: RepositoryActivity? = null

    @Before
    fun setUp() {
        repositoryActivity =
            Robolectric.buildActivity(RepositoryActivity::class.java).create().resume().get()
    }

    @Test
    fun testActivity() {
        val context = RuntimeEnvironment.systemContext
        assertEquals("android", context.packageName)
    }
}