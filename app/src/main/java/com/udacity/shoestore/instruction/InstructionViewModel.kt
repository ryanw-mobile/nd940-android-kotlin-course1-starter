package com.udacity.shoestore.instruction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionViewModel : ViewModel() {
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