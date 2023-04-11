package com.example.jokesplash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesplash.model.JokesClass
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {


    val repository = Repository()

    val jokes = repository.jokes


    fun getJokes(limit: Int){
        viewModelScope.launch {
            try {
                repository.getJokes(limit)
            } catch (e:Exception) {
                Log.e("MainViewModel","$e")
            }
        }
    }
}