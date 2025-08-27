package com.pyaesonehan.snakes.presentation.snake_details

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

class SnakeDetailsViewModel(
    private val snakesRepository: SnakesRepository
): ViewModel() {
    private val _snakeUiState: MutableStateFlow<SnakeUiState> = MutableStateFlow(SnakeUiState.Loading)
    val snakeUiState: StateFlow<SnakeUiState> = _snakeUiState.asStateFlow()

    fun getSnakeById(id: Int) {
        viewModelScope.launch {
            try {
                val snake = snakesRepository.getSnakeById(id)
                _snakeUiState.value = SnakeUiState.Success(snake)
            } catch (e: IOException) {
                e.printStackTrace()
                _snakeUiState.value = SnakeUiState.Failure
            }
        }
    }
    companion object {
        sealed interface SnakeUiState {
            data object Loading: SnakeUiState
            data class Success(val snake: Snake?): SnakeUiState
            data object Failure: SnakeUiState
        }
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val appContainer = (this[APPLICATION_KEY] as SnakesApp).appContainer
                val snakesRepository = appContainer.snakesRepository
                SnakeDetailsViewModel(
                    snakesRepository = snakesRepository
                )
            }
        }
    }
}