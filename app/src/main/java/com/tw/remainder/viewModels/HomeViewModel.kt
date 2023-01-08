package com.tw.remainder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.useCase.GetAllTaskUseCase
import com.tw.remainder.useCase.SaveTaskUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {
    private val getTaskUseCase: GetAllTaskUseCase by inject()
    private val saveTaskUseCase: SaveTaskUseCase by inject()
    private val _taskList = MutableLiveData<List<TaskEntity>>()
    val taskList: LiveData<List<TaskEntity>>
        get() = _taskList

    fun loadTask() {
        viewModelScope.launch {
            val taskListResponse = getTaskUseCase.invoke()
            _taskList.value = taskListResponse
        }
    }

    fun addQuickTask(text: String) {
        viewModelScope.launch {
            saveTaskUseCase.addTask(TaskEntity(title = text, date = "", time = ""))
        }.invokeOnCompletion { loadTask() }
    }
}