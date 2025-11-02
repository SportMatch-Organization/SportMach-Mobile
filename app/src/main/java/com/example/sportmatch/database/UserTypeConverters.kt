package com.example.sportmatch.database

import androidx.room.TypeConverter
import com.example.sportmatch.model.enumTypes.user.GeneroEnum
import com.example.sportmatch.model.enumTypes.user.PerfilEnum
import java.util.Date

class UserTypeConverters {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun fromGeneroEnum(value: String?): GeneroEnum? {
        return value?.let { GeneroEnum.valueOf(it) }
    }

    @TypeConverter
    fun generoEnumToString(genero: GeneroEnum?): String? {
        return genero?.name
    }

    @TypeConverter
    fun fromPerfilEnum(value: String?): PerfilEnum? {
        return value?.let { PerfilEnum.valueOf(it) }
    }

    @TypeConverter
    fun perfilEnumToString(perfil: PerfilEnum?): String? {
        return perfil?.name
    }
}
