package com.tw.remainder.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class AllRemainderViewModel : ViewModel(),KoinComponent {
    val enableBack = true
    val searchEnable= MutableLiveData(false)

    fun toggleSearch() {
        searchEnable.value = searchEnable.value?.let { !it }
    }
}