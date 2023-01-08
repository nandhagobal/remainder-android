package com.tw.remainder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.useCase.GetTaskUseCase
import com.tw.remainder.useCase.SaveTaskUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddNewTaskViewModel:ViewModel(), KoinComponent {
    private val getTaskUseCase: GetTaskUseCase by inject()
    private val saveTaskUseCase: SaveTaskUseCase by inject()

    companion object {
         const val POST_MERIDIEN = "PM"
         const val ANTE_MERIDIEN = "AM"
    }
    private val _title = MutableLiveData("")
    val title : LiveData<String>
        get() = _title

    private val _date = MutableLiveData("")
    val date : LiveData<String>
    get() = _date

    private val _time = MutableLiveData("")
    val time : LiveData<String>
        get() = _time

    var selectedTaskId:Long = 0

    fun setDate(day:Int,month:Int,year:Int){
        _date.value = "$day/${month + 1}/$year"
    }

    fun setTime(minute:Int,hour:Int){
        var hourString: String
        hourString = if(hour>12) "${hour-12}"
        else if(hour == 0) "${hour+12}"
        else "$hour"

        if(hourString.length==1) hourString = "0$hourString"
        var minuteString = minute.toString()

        if(minuteString.length==1) minuteString = "0$minuteString"
        _time.value = if(hour>=12) "$hourString:$minuteString $POST_MERIDIEN" else "$hourString:$minuteString $ANTE_MERIDIEN"
    }

    fun clearDate(){
        _date.value = ""
    }

    fun clearTime(){
        _time.value = ""
    }

    fun addTask(id: Long = 0, title: String, time: String, date: String) {
        viewModelScope.launch {
            val taskEntity = TaskEntity(id = id, title = title, date = date, time = time)
            saveTaskUseCase.addTask(taskEntity)
        }
    }

    fun setTitle(title: String) {
        val previousValue = _title.value
        if(previousValue != title)  {
            _title.value = title
        }
    }

    fun checkTitleField(): Boolean {
        return _title.value?.matches(Regex("[ ]*")) == false
    }
    fun getTask(id: String){
        viewModelScope.launch {
            val task = getTaskUseCase.invoke(id)
            _date.value = task.date
            _time.value = task.time
            _title.value = task.title
            selectedTaskId = id.toLong()
        }
    }
}