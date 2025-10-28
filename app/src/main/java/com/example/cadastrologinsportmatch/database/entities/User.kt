package com.example.cadastrologinsportmatch.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cadastrologinsportmatch.model.enumTypes.GeneroEnum
import com.example.cadastrologinsportmatch.model.enumTypes.PerfilEnum
import java.time.LocalDate
import java.util.UUID // Import para o ID

@Entity(tableName = "user_table") // Nome da tabela no SQL
data class User(
    // Vamos usar um UUID String como ID, já que o Firebase não está mais gerenciando isso
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    val nome: String,
    @ColumnInfo(name = "cpf_cnpj") val cpfCnpj: String,
    val genero: GeneroEnum,
    val telefone: String,
    val usuario: String,
    val email: String,
    val senha: String,
    val endereco: String,
    @ColumnInfo(name = "dt_nascimento") val dataNascimento: LocalDate,
    val perfil: PerfilEnum,
    @ColumnInfo(name = "esportes_interesse") val esportesInteresse: List<String> = emptyList()
)