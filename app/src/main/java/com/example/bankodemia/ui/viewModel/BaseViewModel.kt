package com.example.bankodemia.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bankodemia.core.utils.BaseUiState
import com.example.bankodemia.core.utils.RouterEmitter

abstract class BaseViewModel: ViewModel() {

    val uiStateEmitter: MutableLiveData<BaseUiState> by lazy {
        MutableLiveData<BaseUiState>()
    }

    val routerEmitter: MutableLiveData<RouterEmitter<*>> by lazy {
        MutableLiveData<RouterEmitter<*>>()
    }
}