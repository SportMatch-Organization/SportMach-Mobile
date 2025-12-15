package com.example.sportmatch.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.sportmatch.data.database.entities.EspacoEsportivo

@Dao
interface EspacoEsportivoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(espacoEsportivo: EspacoEsportivo)
}