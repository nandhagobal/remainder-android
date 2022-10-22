package com.tw.remainder.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllRemainderViewModel : ViewModel() {
    val enableBack = true
    val searchEnable= MutableLiveData(false)

    fun toggleSearch() {
        searchEnable.value = searchEnable.value?.let { !it }
    }
}