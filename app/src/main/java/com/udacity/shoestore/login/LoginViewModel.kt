package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    // Screen State
    private val _eventShouldGoWelcomeScreen = MutableLiveData<Boolean>()
    val eventShouldGoWelcomeScreen: LiveData<Boolean>
        get() = _eventShouldGoWelcomeScreen

    init {
        _eventShouldGoWelcomeScreen.value = false
    }

    fun onExistingLogin() {
        // Do nothing in this project. Just proceed
        _eventShouldGoWelcomeScreen.value = true
    }

    fun onCreateNewLogin() {
        // Do nothing in this project. Just proceed
        _eventShouldGoWelcomeScreen.value = true
    }

    fun onGoWelcomeScreenComplete() {
        _eventShouldGoWelcomeScreen.value = false
    }
}