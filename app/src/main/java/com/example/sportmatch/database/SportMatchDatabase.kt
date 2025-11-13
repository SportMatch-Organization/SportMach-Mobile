package com.example.sportmatch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportmatch.database.converters.UserTypeConverters
import com.example.sportmatch.database.dao.CompeticaoDao
import com.example.sportmatch.database.dao.PatrocinadorDao
import com.example.sportmatch.database.dao.UserDao
import com.example.sportmatch.database.entities.User
import com.example.sportmatch.database.entities.Competicao
import com.example.sportmatch.database.entities.Patrocinador


@Database(entities = [User::class, Competicao::class, Patrocinador:: class], version = 1)
@TypeConverters(UserTypeConverters::class)
abstract class SportMatchDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun competicaoDao(): CompeticaoDao
    abstract fun patrocinadorDao(): PatrocinadorDao
    companion object {
        @Volatile
        private var INSTANCE: SportMatchDatabase? = null

        fun getDatabase(context: Context): SportMatchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SportMatchDatabase::class.java,
                    "sportmatch-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
