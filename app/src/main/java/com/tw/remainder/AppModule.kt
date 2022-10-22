package com.tw.remainder

import com.tw.remainder.fragments.AllRemainderFragment
import com.tw.remainder.viewModels.AllRemainderViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule=module{
    viewModel { AllRemainderViewModel() }
}