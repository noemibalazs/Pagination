package com.example.pagination

import com.example.pagination.helper.DataManager
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class DataManagerUnitTest {

    private lateinit var dataManager: DataManager
    private val ID = 12
    private val FULL_NAME = "kotlin/pdfReader"

    @Before
    fun setUp(){
        dataManager = DataManager(RuntimeEnvironment.application.applicationContext)
    }

    @Test
    fun testRepositoryIdShouldPass(){
        dataManager.saveRepositoryID(ID)
        val id = dataManager.getRepositoryId()
        assertEquals(id, ID)
    }

    @Test
    fun testRepositoryIdShouldFail(){
        dataManager.saveRepositoryID(ID)
        val id = dataManager.getRepositoryId()
        assertNotEquals(id, 9)
    }

    @Test
    fun testRepositoryFullNameShouldPass(){
        dataManager.saveRepositoryFullName(FULL_NAME)
        val fullName = dataManager.getRepositoryFullName()
        assertEquals(fullName, FULL_NAME)
    }

    @Test
    fun testRepositoryFullNameShouldFail(){
        dataManager.saveRepositoryFullName(FULL_NAME)
        val fullName = dataManager.getRepositoryFullName()
        assertNotEquals(fullName, "kotlin")
    }

    @After
    fun tearDown(){
        stopKoin()
    }
}