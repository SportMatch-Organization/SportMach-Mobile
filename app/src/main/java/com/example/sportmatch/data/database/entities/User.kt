package com.example.sportmatch.data.database.entities
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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