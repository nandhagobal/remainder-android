package com.tw.remainder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.entities.TaskStatus
import com.tw.remainder.useCase.GetAllTaskUseCase
import com.tw.remainder.useCase.SaveTaskUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AllRemainderViewModel : ViewModel(),KoinComponent {
    private val getTaskUseCase: GetAllTaskUseCase by inject()
    private val saveTaskUseCase: SaveTaskUseCase by inject()
    val enableBack = true
    val searchEnable= MutableLiveData(false)
    private val _searchText = MutableStateFlow("")
    val searchText : StateFlow<String> get()= _searchText

    private lateinit var remainderList: List<TaskEntity>

    private val _filteredRemainderList = MutableLiveData<List<TaskEntity>>()
    val filteredRemainderList: LiveData<List<TaskEntity>> get() = _filteredRemainderList

    init {
        viewModelScope.launch {
            remainderList = getTaskUseCase.invoke()
            _filteredRemainderList.value = remainderList
        }
    }

    fun toggleSearch() {
        searchEnable.value = searchEnable.value?.let { !it }
    }

    fun onChangeSearchTextHandler(text: String){
        _searchText.value = text
        applyFilter()
    }

    private fun applyFilter() {
        val filteredList = remainderList.filter {
            it.title.matches(Regex("\\w*${searchText.value}\\w*"))
        }
        _filteredRemainderList.value = filteredList
    }

    fun changeStatus(task: TaskEntity) {
        viewModelScope.launch {

                saveTaskUseCase.addTask(task = task.copy(status = when(task.status) {
                    TaskStatus.COMPLETED -> TaskStatus.IN_PROGRESS
                    TaskStatus.IN_PROGRESS -> TaskStatus.COMPLETED
                }))
        }
    }

}