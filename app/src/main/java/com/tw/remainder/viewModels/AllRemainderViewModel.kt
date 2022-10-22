package com.tw.remainder.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class AllRemainderViewModel : ViewModel() {
    val enableBack = true
    val searchEnable= MutableLiveData(false)

    fun toggleSearch() {
        searchEnable.value = searchEnable.value?.let { !it }
    }
}