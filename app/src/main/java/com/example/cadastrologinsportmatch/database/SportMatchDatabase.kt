package com.example.cadastrologinsportmatch.database // Certifique-se de que este é o seu package name

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cadastrologinsportmatch.database.dao.CompeticaoDao
import com.example.cadastrologinsportmatch.database.dao.UserDao
import com.example.cadastrologinsportmatch.database.entities.Competicao
import com.example.cadastrologinsportmatch.database.entities.User

/**
 * Classe principal do banco de dados Room.
 * Define as entidades (tabelas), a versão do banco e os DAOs (ações).
 * Também registra os TypeConverters para lidar com tipos complexos.
 */
@Database(entities = [User::class, Competicao::class], version = 1)
@TypeConverters(Converters::class)
abstract class SportMatchDatabase : RoomDatabase() {

    // Fornece acesso ao DAO para interagir com a tabela de usuários.
    abstract fun userDao(): UserDao
    // Fornece acesso ao DAO para interagir com a tabela de competições.
    abstract fun competicaoDao(): CompeticaoDao

    // Companion object para implementar o padrão Singleton
    companion object {
        // A instância Singleton do banco de dados.
        @Volatile
        private var INSTANCE: SportMatchDatabase? = null

        // Nome do arquivo do banco de dados que será criado no dispositivo.
        private const val DATABASE_NAME = "sportmatch_local.db"

        /**
         * Retorna a instância Singleton do banco de dados.
         * Se a instância ainda não existir, ela será criada.
         * Garante que apenas uma conexão com o banco esteja ativa por vez.
         * @param context O contexto da aplicação.
         * @return A instância do SportMatchDatabase.
         */
        fun getDatabase(context: Context): SportMatchDatabase {
            // Retorna a instância existente se já foi criada
            return INSTANCE ?: synchronized(this) {
                // Se a instância é nula, cria o banco de dados
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SportMatchDatabase::class.java,
                    DATABASE_NAME
                )
                    // .build() cria um novo banco de dados vazio se ele não existir.
                    // (Se tivéssemos um arquivo .db pré-populado, usaríamos .createFromAsset() aqui)
                    .build()

                // Atribui a instância recém-criada à variável estática
                INSTANCE = instance
                // Retorna a instância
                instance
            }
        }
    }
}
