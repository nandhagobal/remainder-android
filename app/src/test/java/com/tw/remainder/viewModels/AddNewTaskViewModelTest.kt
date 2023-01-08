package com.tw.remainder.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.useCase.SaveTaskUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class AddNewTaskViewModelTest{

    private val saveTaskUseCase = mockk<SaveTaskUseCase>(relaxed = true)
    private lateinit var addNewTaskViewModel: AddNewTaskViewModel
    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        startKoin{loadKoinModules(module { factory { saveTaskUseCase } })}
        addNewTaskViewModel = AddNewTaskViewModel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown(){
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
        stopKoin()
    }

    @Test
    fun shouldReturnDD_MM_YYYYWhenSetDateIsCalled() {
        addNewTaskViewModel.setDate(12,1,2000)
        assertEquals("12/2/2000",addNewTaskViewModel.date.value)
    }

    @Test
    fun shouldReturnTimeInHR_MINFormatWhenSetTimeIsCalled() {
        addNewTaskViewModel.setTime(23,23)
        assertEquals("11:23 PM",addNewTaskViewModel.time.value)
    }

    @Test
    fun shouldSaveTaskWhenAddTaskIsCalled() {
        addNewTaskViewModel.addTask(title = "", date = "", time = "")
        coVerify { saveTaskUseCase.addTask(TaskEntity(id =0,title = "", date = "", time = "")) }
    }

    @Test
    fun shouldReturnFalseWhenOnlySpaceIsGiven() {
        addNewTaskViewModel.setTitle("    ")
        assertFalse(addNewTaskViewModel.checkTitleField())
    }

    @Test
    fun shouldReturnFalseWhenValidTitleIsGiven() {
        addNewTaskViewModel.setTitle("title")
        assertTrue(addNewTaskViewModel.checkTitleField())
    }

}