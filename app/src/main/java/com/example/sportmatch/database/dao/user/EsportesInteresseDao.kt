package com.example.sportmatch.database.dao.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.sportmatch.database.entities.user.EsportesInteresse

@Dao
interface EsportesInteresseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(esporteInteresse: EsportesInteresse)
}