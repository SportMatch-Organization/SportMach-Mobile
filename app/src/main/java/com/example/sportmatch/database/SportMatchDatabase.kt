package com.example.sportmatch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportmatch.database.converters.UserTypeConverters
import com.example.sportmatch.database.dao.CompeticaoDao
import com.example.sportmatch.database.dao.PatrocinadorDao
import com.example.sportmatch.database.dao.user.EsportesInteresseDao
import com.example.sportmatch.database.dao.user.UserDao
import com.example.sportmatch.database.entities.user.User
import com.example.sportmatch.database.entities.Competicao
import com.example.sportmatch.database.entities.Patrocinador
import com.example.sportmatch.database.entities.user.EsportesInteresse


@Database(entities = [User::class, Competicao::class, Patrocinador:: class, EsportesInteresse::class], version = 2)
@TypeConverters(UserTypeConverters::class)
abstract class SportMatchDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun esportesInteresseDao(): EsportesInteresseDao
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
                            ).fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
