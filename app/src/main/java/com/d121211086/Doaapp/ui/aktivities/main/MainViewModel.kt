package com.d121211086.Doaapp.ui.aktivities.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211086.Doaapp.MyApplication
import com.d121211086.Doaapp.data.models.Doa
import com.d121211086.Doaapp.data.repository.DoaRepository
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.io.IOException

sealed interface MainUiState {
    data class Success(val Doa: List<Doa>) : MainUiState
    object Error : MainUiState
    object Loading : MainUiState
}

class MainViewModel(private val DoaRepository: DoaRepository): ViewModel() {

    var mainUiState: MainUiState by mutableStateOf(MainUiState.Loading)
        private set

    fun getDoa() = viewModelScope.launch {
        mainUiState = MainUiState.Loading
        try {
            val result = DoaRepository.getDoa()

            mainUiState = MainUiState.Success(Json.decodeFromString<List<Doa>>(result.toString()))
        } catch (e: IOException) {
            Log.d("MainViewMode", "getDoa error: ${e.message}")
            mainUiState = MainUiState.Error
        }
    }

    init {
        getDoa()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                val doaRepository = application.container.doaRepository
                MainViewModel(doaRepository)
            }
        }
    }

}