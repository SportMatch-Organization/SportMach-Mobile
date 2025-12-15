package com.example.sportmatch.data.api.APITempo

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class CurrentCondition(
    val temp_C: String? = null,
    val weatherDesc: List<WeatherDesc> = emptyList()
)

@Serializable
data class WeatherDesc(
    val value: String? = ""
)

@Serializable
data class WeatherResponse(
    val current_condition: List<CurrentCondition> = emptyList()
)

object WeatherService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true      // ignora campos extras
            })
        }
    }

    suspend fun getWeather(city: String): WeatherResponse {
        return try {
            client.get("https://wttr.in/$city?format=j1") {
                headers {
                    append("Accept", "application/json")
                    append("User-Agent", "Ktor client")
                }
            }.body()
        } catch (e: Exception) {
            println("Erro ao buscar clima: ${e.message}")
            WeatherResponse(emptyList())
        }
    }
}
