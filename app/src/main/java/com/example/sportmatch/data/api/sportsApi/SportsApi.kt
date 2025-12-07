package com.example.sportmatch.data.api.sportsApi

import com.example.sportmatch.model.ApiSport
import retrofit2.http.GET

interface SportsApi {
    @GET("sports")
    suspend fun getSports(): List<ApiSport>

}