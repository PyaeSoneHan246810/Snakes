package com.pyaesonehan.snakes.data.repository

import com.pyaesonehan.snakes.data.service.SnakesApiService
import com.pyaesonehan.snakes.domin.model.Snake
import com.pyaesonehan.snakes.domin.repository.SnakesRepository

class NetworkSnakesRepository(
    private val snakesApiService: SnakesApiService
): SnakesRepository {
    override suspend fun getSnakes(): List<Snake> {
        return snakesApiService.getSnakes()
    }

    override suspend fun getSnakeById(id: Int): Snake? {
        return snakesApiService.getSnakeById(id).firstOrNull()
    }
}