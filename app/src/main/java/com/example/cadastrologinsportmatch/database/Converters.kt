package com.example.cadastrologinsportmatch.database

import androidx.room.TypeConverter
import com.example.cadastrologinsportmatch.model.enumTypes.GeneroEnum
import com.example.cadastrologinsportmatch.model.enumTypes.PerfilEnum
import java.time.LocalDate

class Converters {
    // --- Para LocalDate ---
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? = date?.toString()

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }

    // --- Para GeneroEnum ---
    @TypeConverter
    fun fromGeneroEnum(genero: GeneroEnum?): String? = genero?.name

    @TypeConverter
    fun toGeneroEnum(value: String?): GeneroEnum? = value?.let { GeneroEnum.valueOf(it) }

    // --- Para PerfilEnum ---
    @TypeConverter
    fun fromPerfilEnum(perfil: PerfilEnum?): String? = perfil?.name

    @TypeConverter
    fun toPerfilEnum(value: String?): PerfilEnum? = value?.let { PerfilEnum.valueOf(it) }

    // --- Para a tela Pesquisar (Listas) ---
    @TypeConverter
    fun fromStringList(list: List<String>?): String? = list?.joinToString(separator = ",")

    @TypeConverter
    fun toStringList(value: String?): List<String>? = value?.split(",")?.map { it.trim() }
}