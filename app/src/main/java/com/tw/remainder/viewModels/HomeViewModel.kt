package com.tw.remainder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.remainder.database.TaskDatabase
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.useCase.GetAllTaskUseCase
import com.tw.remainder.useCase.SaveTaskUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

class HomeViewModel : ViewModel(), KoinComponent {
    private val getTaskUseCase: GetAllTaskUseCase by inject()
    private val saveTaskUseCase: SaveTaskUseCase by inject()
    private val _taskList = MutableLiveData<List<TaskEntity>>()
    val taskList: LiveData<List<TaskEntity>>
        get() = _taskList


    init {
        loadTask()
    }

    private fun loadTask() {
        viewModelScope.launch {
            val taskListResponse = getTaskUseCase.invoke()
            _taskList.value = taskListResponse
        }
    }

    fun addTask() {
        viewModelScope.launch {
            saveTaskUseCase.invoke(TaskEntity(12, "hello", Date(223232323)))
        }
    }
}