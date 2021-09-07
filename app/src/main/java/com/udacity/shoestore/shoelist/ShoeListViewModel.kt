package com.udacity.shoestore.shoelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel : ViewModel() {

    // Screen State
    private val _eventShouldGoShoeDetailScreen = MutableLiveData<Boolean>()
    val eventShouldGoShoeDetailScreen: LiveData<Boolean>
        get() = _eventShouldGoShoeDetailScreen

    private val _eventShouldGoLoginScreen = MutableLiveData<Boolean>()
    val eventShouldGoLoginScreen: LiveData<Boolean>
        get() = _eventShouldGoLoginScreen

    init {
        _eventShouldGoShoeDetailScreen.value = false
        _eventShouldGoLoginScreen.value = false
    }

    fun onGoShoeDetailScreen() {
        _eventShouldGoShoeDetailScreen.value = true
    }

    fun onGoShoeDetailScreenComplete() {
        _eventShouldGoShoeDetailScreen.value = false
    }

    fun logout() {
        _eventShouldGoLoginScreen.value = true
    }

    fun onGoLoginScreenComplete() {
        _eventShouldGoLoginScreen.value = false
    }

}