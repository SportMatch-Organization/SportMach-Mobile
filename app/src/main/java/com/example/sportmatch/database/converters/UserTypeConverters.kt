package com.example.sportmatch.database.converters

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import com.example.sportmatch.model.enumTypes.user.GeneroEnum
import com.example.sportmatch.model.enumTypes.user.PerfilEnum
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date

class UserTypeConverters {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun longToDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    // método auxiliar - ignorado pelo Room Database
    @RequiresApi(Build.VERSION_CODES.O)
    fun stringToLocalDate(date: String): LocalDate?{
        return try{
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            LocalDate.parse(date, formatter)

        } catch (e: DateTimeParseException){
            e.printStackTrace()
            null
        }
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