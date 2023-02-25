package com.tw.remainder.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.entities.TaskStatus
import com.tw.remainder.useCase.GetAllTaskUseCase
import com.tw.remainder.useCase.SaveTaskUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val getTaskUseCase = mockk<GetAllTaskUseCase>(relaxed = true)
    private val saveTaskUseCase = mockk<SaveTaskUseCase>(relaxed = true)
    private val mockedModule = module {
        factory { getTaskUseCase }
        factory { saveTaskUseCase }
    }
    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        startKoin {
            loadKoinModules(mockedModule)
        }
        viewModel = HomeViewModel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearsDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
        stopKoin()
    }

    @Test
    fun shouldUpdateListOfTaskWhenLoadTaskIsCalled() {
        coEvery { getTaskUseCase.invoke() } returns listOf(TaskEntity(0, "", "", "",TaskStatus.IN_PROGRESS))
        viewModel.loadTask()
        Assert.assertEquals(
            listOf(TaskEntity(0, "", "", "",TaskStatus.IN_PROGRESS)),
            viewModel.taskList.value as List<TaskEntity>
        )
    }

    @Test
    fun shouldCallLoadDetailsAfterSavingTheTask() {
        viewModel.addQuickTask("title")
        verify { viewModel.loadTask() }
    }

    @Test
    fun shouldSaveTaskWhenAddQuickTaskIsCalled() {
        viewModel.addQuickTask("title")
        coVerify { saveTaskUseCase.addTask(TaskEntity(title = "title", date = "", time = "", status = TaskStatus.IN_PROGRESS)) }
    }
}