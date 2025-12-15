package com.example.sportmatch.data.database.dao.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportmatch.data.database.entities.user.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(user: User) //indica que essa função pode demorar e deve ser executada em segundo plano

    @Query("SELECT * FROM user WHERE email = :email AND senha = :senha LIMIT 1")
    suspend fun fazerLogin(email: String, senha: String): User?

    @Query("SELECT * FROM user WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?
}