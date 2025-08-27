package com.pyaesonehan.snakes.di

import com.pyaesonehan.snakes.data.repository.NetworkSnakesRepository
import com.pyaesonehan.snakes.data.service.SnakesRetrofitApiService
import com.pyaesonehan.snakes.domin.repository.SnakesRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class DefaultAppContainer: AppContainer {
    private val baseUrl: String = "https://burma-project-ideas.vercel.app"

    private val retrofit: Retrofit = Retrofit
        .Builder()
        .addConverterFactory(Json.asConverterFactory(contentType = "application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val snakesRetrofitApiService: SnakesRetrofitApiService by lazy {
        retrofit.create(SnakesRetrofitApiService::class.java)
    }

    override val snakesRepository: SnakesRepository by lazy {
        NetworkSnakesRepository(
            snakesApiService = snakesRetrofitApiService
        )
    }
}