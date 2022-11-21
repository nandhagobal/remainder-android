package com.tw.remainder.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tw.remainder.entities.TaskEntity
import com.tw.remainder.useCase.SaveTaskUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddNewTaskViewModel:ViewModel(), KoinComponent {
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

    fun addTask() {
        viewModelScope.launch {
            _title.value?.let { _date.value?.let { it1 -> _time.value?.let { it2 -> TaskEntity(title = it, date = it1, time = it2) } } }
                ?.let { saveTaskUseCase.invoke(it) }
        }
    }

    fun setTitle(title: String) {
        _title.value = title
    }

    fun checkTitleField(): Boolean {
        return _title.value?.matches(Regex("[ ]*")) == false
    }
}