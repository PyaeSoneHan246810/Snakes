package com.pyaesonehan.snakes.presentation.navhost

import kotlinx.serialization.Serializable

object Destination {
    @Serializable data object SnakesScreen
    @Serializable data class SnakeDetailsScreen(val id: Int)
}