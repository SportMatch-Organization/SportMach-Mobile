package com.example.sportmatch.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.sportmatch.database.entities.User

@Dao
interface UserDao {

    @Insert
    fun insert(user: User)
}