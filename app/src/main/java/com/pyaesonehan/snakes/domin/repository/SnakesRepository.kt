package com.pyaesonehan.snakes.domin.repository

import com.pyaesonehan.snakes.domin.model.Snake

interface SnakesRepository {
    suspend fun getSnakes(): List<Snake>
    suspend fun getSnakeById(id: Int): Snake?
}