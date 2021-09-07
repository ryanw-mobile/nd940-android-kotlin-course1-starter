package com.udacity.shoestore.shoedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeDetailViewModel : ViewModel() {
    // Screen State
    private val _eventShouldGoShoeList = MutableLiveData<Boolean>()
    val eventShouldGoShoeList: LiveData<Boolean>
        get() = _eventShouldGoShoeList

    init {
        _eventShouldGoShoeList.value = false
    }

    fun onGoShoeList() {
        // Do nothing in this project. Just proceed
        _eventShouldGoShoeList.value = true
    }

    fun onGoShoeListComplete() {
        _eventShouldGoShoeList.value = false
    }
}