package com.pyaesonehan.snakes.domin.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Snake(
    @SerialName("Id")
    val id: Int,
    @SerialName("ImageUrl")
    val imageUrl: String,
    @SerialName("MMName")
    val myanmarName: String,
    @SerialName("EngName")
    val englishName: String,
    @SerialName("Detail")
    val detail: String,
    @SerialName("IsPoison")
    val isPoisonous: String,
    @SerialName("IsDanger")
    val isDangerous: String
)

val Snake.isPoisonousBool: Boolean
    get() = isPoisonous.equals("Yes", ignoreCase = true)

val Snake.isDangerousBool: Boolean
    get() = isDangerous.equals("Yes", ignoreCase = true)