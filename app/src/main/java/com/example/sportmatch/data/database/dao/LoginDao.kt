package com.example.sportmatch.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportmatch.data.database.entities.LoginCacheEntity

@Dao
interface LoginCacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(cache: LoginCacheEntity)
    @Query("SELECT * FROM login_cache WHERE email = :email AND senha = :senha LIMIT 1")
    suspend fun buscarLogin(email: String, senha: String): LoginCacheEntity?
}