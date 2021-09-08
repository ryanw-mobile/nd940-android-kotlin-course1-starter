package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _eventShouldGoShoeDetailScreen = MutableLiveData<Boolean>()
    val eventShouldGoShoeDetailScreen: LiveData<Boolean>
        get() = _eventShouldGoShoeDetailScreen


    fun insertShoe(shoe: Shoe) {
        var newlist = _shoeList.value
        if (null == newlist) {
            newlist = mutableListOf()
        }
        newlist.add(shoe)
        _shoeList.value = newlist
    }

    fun clear() {
        _shoeList.value = mutableListOf()
    }

}