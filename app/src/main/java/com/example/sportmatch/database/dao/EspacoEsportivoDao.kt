package com.example.sportmatch.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.sportmatch.database.entities.EspacoEsportivo
import com.example.sportmatch.database.entities.User

@Dao
interface EspacoEsportivoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(espacoEsportivo: EspacoEsportivo)
}