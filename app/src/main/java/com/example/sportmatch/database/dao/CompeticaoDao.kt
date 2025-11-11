package com.example.sportmatch.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.sportmatch.database.entities.Competicao

@Dao
interface CompeticaoDao {

    @Insert
    suspend fun insert(competicao: Competicao)
}
