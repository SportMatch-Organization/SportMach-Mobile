package com.example.sportmatch.data.database.converters

import androidx.room.TypeConverter

class CompetitionTypeConverters {

    // --- Para a tela Pesquisar (Listas) ---
    @TypeConverter
    fun fromStringList(list: List<String>?): String? = list?.joinToString(separator = ",")

    @TypeConverter
    fun toStringList(value: String?): List<String>? = value?.split(",")?.map { it.trim() }
}