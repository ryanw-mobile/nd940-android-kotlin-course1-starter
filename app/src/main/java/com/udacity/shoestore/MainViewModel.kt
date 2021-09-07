package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<List<Shoe>>()
    val shoeList: LiveData<List<Shoe>>
        get() = _shoeList

    private val _eventShouldGoShoeDetailScreen = MutableLiveData<Boolean>()
    val eventShouldGoShoeDetailScreen: LiveData<Boolean>
        get() = _eventShouldGoShoeDetailScreen


    fun insertShoe(shoe: Shoe) {
//        var newlist = mutableListOf<Shoe>()
//        newlist.(_shoeList.value)
//        val list:List<Shoe> = _shoeList.value:? List<Shoe>()
//
//        _shoeList.value = list
    }

}