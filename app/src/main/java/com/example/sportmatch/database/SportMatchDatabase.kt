package com.example.sportmatch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportmatch.database.converters.UserTypeConverters
import com.example.sportmatch.database.dao.UserDao
import com.example.sportmatch.database.entities.User


@Database(entities = [User::class], version = 1)
@TypeConverters(UserTypeConverters::class)
abstract class SportMatchDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

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
