package com.pyaesonehan.snakes.di

import com.pyaesonehan.snakes.domin.repository.SnakesRepository

interface AppContainer {
    val snakesRepository: SnakesRepository
}