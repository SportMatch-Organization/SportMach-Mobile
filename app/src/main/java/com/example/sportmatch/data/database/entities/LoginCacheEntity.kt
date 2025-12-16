package com.example.sportmatch.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login_cache")
data class LoginCacheEntity(
    @PrimaryKey val uid: String,
    val email: String,
    val senha: String,
)