package com.pyaesonehan.snakes.data.service

import com.pyaesonehan.snakes.domin.model.Snake

interface SnakesApiService {
    suspend fun getSnakes(): List<Snake>
    suspend fun getSnakeById(id: Int): List<Snake>
}