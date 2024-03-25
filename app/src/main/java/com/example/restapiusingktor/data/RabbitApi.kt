package com.example.restapiusingktor.data

import retrofit2.http.GET

interface RabbitApi {
    @GET("/randomrabbit")
    suspend fun getRandomRabbit():Rabbit
}