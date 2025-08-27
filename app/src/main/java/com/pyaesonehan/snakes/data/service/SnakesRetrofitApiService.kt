package com.pyaesonehan.snakes.data.service

import com.pyaesonehan.snakes.domin.model.Snake
import retrofit2.http.GET
import retrofit2.http.Path

interface SnakesRetrofitApiService: SnakesApiService {
    @GET("snakes")
    override suspend fun getSnakes(): List<Snake>
    @GET("snakes/{id}")
    override suspend fun getSnakeById(@Path("id") id: Int): List<Snake>
}