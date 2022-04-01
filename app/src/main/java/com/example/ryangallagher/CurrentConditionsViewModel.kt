package com.example.ryangallagher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CurrentConditionsViewModel  : ViewModel() {
    private val _currentConditions = MutableLiveData<CurrentConditions>()
    val currentConditions: LiveData<CurrentConditions>
        get() = _currentConditions

    fun loadData(currentConditionsArg: CurrentConditions) {
        _currentConditions.value = currentConditionsArg
    }
}