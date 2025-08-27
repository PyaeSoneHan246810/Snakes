package com.pyaesonehan.snakes.presentation.navhost

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pyaesonehan.snakes.presentation.snake_details.SnakeDetailsScreenView
import com.pyaesonehan.snakes.presentation.snake_details.SnakeDetailsViewModel
import com.pyaesonehan.snakes.presentation.snakes.SnakesScreenView
import com.pyaesonehan.snakes.presentation.snakes.SnakesViewModel
import com.pyaesonehan.snakes.presentation.theme.SnakesTheme

@Composable
fun NavHostView(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier
            .fillMaxSize(),
        navController = navController,
        startDestination = Destination.SnakesScreen
    ) {
        composable<Destination.SnakesScreen> {
            val snakesViewModel: SnakesViewModel = viewModel(factory = SnakesViewModel.Factory)
            val snakesUiState by snakesViewModel.snakesUiState.collectAsStateWithLifecycle()
            SnakesScreenView(
                snakesUiState = snakesUiState,
                onRetry = {
                    snakesViewModel.getSnakes()
                },
                onSnakeClick = { id ->
                    val snakeDetailsScreen = Destination.SnakeDetailsScreen(id = id)
                    navController.navigate(snakeDetailsScreen)
                }
            )
        }
        composable<Destination.SnakeDetailsScreen> { backStackEntry ->
            val snakeDetailsScreen: Destination.SnakeDetailsScreen = backStackEntry.toRoute()
            val snakeId: Int = snakeDetailsScreen.id
            val snakeDetailsViewModel: SnakeDetailsViewModel = viewModel(factory = SnakeDetailsViewModel.Factory)
            val snakeUiState by snakeDetailsViewModel.snakeUiState.collectAsStateWithLifecycle()
            LaunchedEffect(snakeId) {
                snakeDetailsViewModel.getSnakeById(
                    id = snakeId
                )
            }
            SnakeDetailsScreenView(
                snakeUiState = snakeUiState,
                onRetry = {
                    snakeDetailsViewModel.getSnakeById(snakeId)
                },
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}

@Preview
@Composable
private fun NavHostViewPreview() {
    SnakesTheme {
        NavHostView()
    }
}