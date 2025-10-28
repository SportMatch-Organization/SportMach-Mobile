package com.example.sportmatch.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.sportmatch.database.entities.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User) //indica que essa função pode demorar e deve ser executada em segundo plano
}
