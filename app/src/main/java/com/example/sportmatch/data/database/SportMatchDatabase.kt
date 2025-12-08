package com.example.sportmatch.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportmatch.data.database.converters.UserTypeConverters
import com.example.sportmatch.data.database.dao.CompeticaoDao
import com.example.sportmatch.data.database.dao.LoginCacheDao
import com.example.sportmatch.data.database.dao.PatrocinadorDao
import com.example.sportmatch.data.database.dao.UserDao
import com.example.sportmatch.data.database.entities.User
import com.example.sportmatch.data.database.entities.Competicao
import com.example.sportmatch.data.database.entities.LoginCacheEntity
import com.example.sportmatch.data.database.entities.Patrocinador


@Database(entities = [User::class, Competicao::class, Patrocinador:: class, LoginCacheEntity::class], version = 3)
@TypeConverters(UserTypeConverters::class)
abstract class SportMatchDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun competicaoDao(): CompeticaoDao
    abstract fun patrocinadorDao(): PatrocinadorDao
    abstract fun loginCacheDao(): LoginCacheDao
    companion object {
        @Volatile
        private var INSTANCE: SportMatchDatabase? = null

        fun getDatabase(context: Context): SportMatchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SportMatchDatabase::class.java,
                    "sportmatch-database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}
