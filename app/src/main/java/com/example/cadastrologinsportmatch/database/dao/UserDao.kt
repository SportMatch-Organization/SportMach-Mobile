package com.example.cadastrologinsportmatch.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cadastrologinsportmatch.database.entities.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table WHERE email = :email AND senha = :senha LIMIT 1")
    suspend fun fazerLogin(email: String, senha: String): User?

    @Query("SELECT * FROM user_table WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
}