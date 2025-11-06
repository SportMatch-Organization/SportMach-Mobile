package com.example.sportmatch.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sportmatch.model.enumTypes.user.GeneroEnum
import com.example.sportmatch.model.enumTypes.user.PerfilEnum
import java.time.LocalDate
import java.util.Date

@Entity(tableName = "user")
data class User(
    //Adicionando valores padrão para o CadastroViewModel
    @PrimaryKey val id: String = "",
    val nome: String = "",
    @ColumnInfo(name = "cpf_cnpj") val cpfCnpj: String = "",
    val genero: String = "",
    val telefone: String = "",
    val usuario: String = "",
    val email: String = "",
    val senha: String = "",
    val cep: String = "",
    val endereco: String = "",
    @ColumnInfo(name = "dt_nascimento") val dataNascimento: String = "",
    val perfil: String = "",
    @ColumnInfo(name="esporte_interesse") val esporteIntresse: String = ""
)