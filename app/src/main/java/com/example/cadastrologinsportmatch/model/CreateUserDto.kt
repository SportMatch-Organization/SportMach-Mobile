package com.example.cadastrologinsportmatch.model
data class CreateUserDto(
    val nome: String,
    val usuario: String,
    val cpf: String,
    val dataNascimento: String,
    val genero: String,
    val telefone: String,
    val email: String,
    val senha: String,
    val endereco: String,
    val perfil: String, // organizador, atleta, locador, patrocinador
    val esportesInteresse: List<String> = emptyList() // opcional
)

