package com.tw.remainder

import androidx.room.Room
import com.tw.remainder.database.TaskDAO
import com.tw.remainder.database.TaskDatabase
import com.tw.remainder.repositories.TaskRepository
import com.tw.remainder.repositories.TaskRepositoryImpl
import com.tw.remainder.useCase.GetAllTaskUseCase
import com.tw.remainder.useCase.SaveTaskUseCase
import com.tw.remainder.viewModels.AddNewTaskViewModel
import com.tw.remainder.viewModels.AllRemainderViewModel
import com.tw.remainder.viewModels.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { Room.databaseBuilder(androidContext(), TaskDatabase::class.java, "TaskDb").build() }
    single { get<TaskDatabase>().taskDAO() }
    factory { SaveTaskUseCase(get()) }
    factory<TaskRepository> { TaskRepositoryImpl(get()) }
    factory { GetAllTaskUseCase(get()) }
    viewModel { HomeViewModel() }
    viewModel { AllRemainderViewModel() }
    viewModel { AddNewTaskViewModel() }
}