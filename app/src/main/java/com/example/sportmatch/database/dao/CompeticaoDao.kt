package com.example.sportmatch.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sportmatch.database.entities.Competicao

@Dao
interface CompeticaoDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insert(competicao: Competicao)

    @Query("SELECT * FROM competicao WHERE status = :status")
    suspend fun getCompeticoesPorStatus(status: String): List<Competicao>

    @Query("SELECT * FROM competicao")
    suspend fun getTodasCompeticoes(): List<Competicao>
}