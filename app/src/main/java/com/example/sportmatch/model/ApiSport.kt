package com.example.sportmatch.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiSport(
    val id: Int,
    val name: String
)