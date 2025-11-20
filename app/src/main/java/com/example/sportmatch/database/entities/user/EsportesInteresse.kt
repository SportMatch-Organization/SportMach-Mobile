package com.example.sportmatch.database.entities.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class EsportesInteresse(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "id_usuario") val idUsuario: String = "",
    @ColumnInfo(name = "esporte_interesse") val esportesInteresse: String = ""
)