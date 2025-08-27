package com.pyaesonehan.snakes.presentation.snakes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pyaesonehan.snakes.app.SnakesApp
import com.pyaesonehan.snakes.domin.model.Snake
import com.pyaesonehan.snakes.domin.repository.SnakesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class SnakesViewModel(
    private val snakesRepository: SnakesRepository
): ViewModel() {
    private val _snakesUiState: MutableStateFlow<SnakesUiState> = MutableStateFlow(SnakesUiState.Loading)
    val snakesUiState: StateFlow<SnakesUiState> = _snakesUiState.asStateFlow()

    init {
        getSnakes()
    }

    fun getSnakes() {
        viewModelScope.launch {
            try {
                val snakes = snakesRepository.getSnakes()
                _snakesUiState.value = SnakesUiState.Success(
                    snakes = snakes
                )
            } catch (e: IOException) {
                e.printStackTrace()
                _snakesUiState.value = SnakesUiState.Failure
            }
        }
    }

    companion object {
        sealed interface SnakesUiState {
            data object Loading: SnakesUiState
            data class Success(val snakes: List<Snake>): SnakesUiState
            data object Failure: SnakesUiState
        }
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val appContainer = (this[APPLICATION_KEY] as SnakesApp).appContainer
                val snakesRepository = appContainer.snakesRepository
                SnakesViewModel(
                    snakesRepository = snakesRepository
                )
            }
        }
    }
}