package com.example.restapiusingktor.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restapiusingktor.data.Rabbit
import com.example.restapiusingktor.data.RabbitApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor(
    private val api:RabbitApi
):ViewModel() {
    private val _state= mutableStateOf(RabbitState())
    val state:State<RabbitState> = _state
    init {
        getRandomRabbit()
    }

    fun getRandomRabbit(){
        viewModelScope.launch {
            try {
                _state.value=state.value.copy(isLoading = true)
                _state.value=state.value.copy(rabbit = api.getRandomRabbit(), isLoading = true)

            }catch (e:Exception){
                Log.e("network",e.message.toString())
                _state.value=state.value.copy(isLoading = false)
            }
        }
    }
    data class RabbitState(
        val rabbit: Rabbit?=null,
        val isLoading:Boolean=false,

    )
}