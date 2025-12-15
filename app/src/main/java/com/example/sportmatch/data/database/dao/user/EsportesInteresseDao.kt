package com.example.sportmatch.data.database.dao.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.sportmatch.data.database.entities.user.EsportesInteresse

@Dao
interface EsportesInteresseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(esporteInteresse: EsportesInteresse)
}