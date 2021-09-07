package com.udacity.shoestore.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    // Screen State
    private val _eventShouldGoInstructionScreen = MutableLiveData<Boolean>()
    val eventShouldGoWelcomeScreen: LiveData<Boolean>
        get() = _eventShouldGoInstructionScreen

    init {
        _eventShouldGoInstructionScreen.value = false
    }

    fun onGoInstructionScreen() {
        // Do nothing in this project. Just proceed
        _eventShouldGoInstructionScreen.value = true
    }

    fun onGoInstructionScreenComplete() {
        _eventShouldGoInstructionScreen.value = false
    }
}