package com.example.sportmatch.data.api.sportsApi

import ApiSport
import retrofit2.http.GET

interface SportsApi {
    @GET("sports")
    suspend fun getSports(): List<ApiSport>

}