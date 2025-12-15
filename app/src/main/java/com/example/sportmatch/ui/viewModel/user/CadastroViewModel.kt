package com.example.sportmatch.ui.viewModel.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sportmatch.data.database.entities.user.User

class CadastroViewModel: ViewModel() {

    var user by mutableStateOf(User())
        private set

    fun setId(id: String){
        user = user.copy(id = id)
    }

    fun setNome(nome:String){
        user = user.copy(nome = nome)
    }

    fun setCpfCnpj(cpfCnpj:String){
        user = user.copy(cpfCnpj = cpfCnpj)
    }

    fun setGenero(genero: String){
        user = user.copy(genero = genero)
    }

    fun setTelefone(telefone:String){
        user = user.copy(telefone = telefone)
    }

    fun setUsuario(usuario:String){
        user = user.copy(usuario = usuario)
    }

    fun setEmail(email:String){
        user = user.copy(email = email)
    }

    fun setSenha(senha:String){
        user = user.copy(senha = senha)
    }

    fun setCep(cep:String){
        user = user.copy(cep = cep)
    }

    fun setEndereco(endereco:String){
        user = user.copy(endereco = endereco)
    }

    fun setDataNascimento(dataNascimento: String){
        user = user.copy(dataNascimento = dataNascimento)
    }

    fun setPerfil(perfil: String){
        user = user.copy(perfil = perfil)
    }

}