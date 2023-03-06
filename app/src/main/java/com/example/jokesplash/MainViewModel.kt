package com.example.jokesplash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    val repository = Repository()


    fun geJokes(){
        viewModelScope.launch {
            try {
                repository.getJokes()
                Log.e("MainViewModelTest","${repository.getJokes()}")

            } catch (e:Exception) {
                Log.e("MainViewModel","$e")
            }
        }
    }
}