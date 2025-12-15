package com.example.sportmatch.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sportmatch.data.database.converters.UserTypeConverters
import com.example.sportmatch.data.database.dao.CompeticaoDao
import com.example.sportmatch.data.database.dao.EspacoEsportivoDao
import com.example.sportmatch.data.database.dao.PatrocinadorDao
import com.example.sportmatch.data.database.dao.user.EsportesInteresseDao
import com.example.sportmatch.data.database.dao.user.UserDao
import com.example.sportmatch.data.database.entities.Competicao
import com.example.sportmatch.data.database.entities.EspacoEsportivo
import com.example.sportmatch.data.database.entities.Patrocinador
import com.example.sportmatch.data.database.entities.user.Endereco
import com.example.sportmatch.data.database.entities.user.EsportesInteresse
import com.example.sportmatch.data.database.entities.user.User


@Database(entities = [User::class, Competicao::class, Patrocinador:: class, EsportesInteresse::class, EspacoEsportivo::class, Endereco::class], version = 2)
@TypeConverters(UserTypeConverters::class)
abstract class SportMatchDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun esportesInteresseDao(): EsportesInteresseDao
    abstract fun competicaoDao(): CompeticaoDao
    abstract fun patrocinadorDao(): PatrocinadorDao
    abstract fun espacoEsportivoDao(): EspacoEsportivoDao

    abstract fun enderecoDao(): LocalAddressDataSource

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
