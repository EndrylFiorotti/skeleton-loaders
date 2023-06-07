package com.example.skeletonloader.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SkeletonViewModel : ViewModel() {
    private var _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun setLoading() {
        _loading.postValue(true)
    }
}