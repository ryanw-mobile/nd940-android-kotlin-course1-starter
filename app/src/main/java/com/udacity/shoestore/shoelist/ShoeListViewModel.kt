package com.udacity.shoestore.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {

    // Screen State
    private val _eventShouldGoLoginScreen = MutableLiveData<Boolean>()
    val eventShouldGoLoginScreen: LiveData<Boolean>
        get() = _eventShouldGoLoginScreen
    init {
        _eventShouldGoLoginScreen.value = false
    }
    fun logout() {
        _eventShouldGoLoginScreen.value = true
    }

    fun onGoLoginScreenComplete() {
        _eventShouldGoLoginScreen.value = false
    }

}