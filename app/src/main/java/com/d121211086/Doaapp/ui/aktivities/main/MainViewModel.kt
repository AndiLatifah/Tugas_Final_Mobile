package com.d121211086.Doaapp.ui.aktivities.main

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211086.Doaapp.main.MyApplication
import com.d121211086.Doaapp.data.models.Doa
import com.d121211086.Doaapp.data.repository.DoaRepository
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.IOException

sealed interface MainUiState {
    data class Success(val Doa: List<Doa>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(application: Application, private val doaRepository: DoaRepository): AndroidViewModel(application){
    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getDoa() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val jsonString = doaRepository.getDoa()
            println("Received JSON: $jsonString")

            if (jsonString.isNotEmpty()) {
                try {
                    val response = Json.decodeFromString<List<Doa>>(jsonString.toString())
                    mainUiState = MainUiState.Success(response)
                } catch (e: SerializationException) {
                    e.printStackTrace()
                    mainUiState = MainUiState.Error
                    println("Error during JSON parsing: ${e.message}")
                }
            } else {
                mainUiState = MainUiState.Error
                println("Empty JSON response")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            mainUiState = MainUiState.Error
            println("Error: ${e.message}")
        }
    }

    init {
        getDoa()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyApplication)
                val DoaRepository = application.container.doaRepository
                MainViewModel(application, DoaRepository)
            }
        }
    }
}