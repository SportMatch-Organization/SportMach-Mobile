package com.example.sportmatch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sportmatch.database.dao.UserDao
import com.example.sportmatch.database.entities.User


@Database(entities = [User::class], version = 1)
abstract class SportMatchDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    //método de conexão utilizando o padrão singleton, agarantindo uma única instância do banco de dados
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
