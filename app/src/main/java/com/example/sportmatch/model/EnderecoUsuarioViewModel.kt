package com.example.sportmatch.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sportmatch.data.api.viaCepApi.Endereco

class EnderecoUsuarioViewModel: ViewModel() {

    var enderecoApi by mutableStateOf(Endereco())
        private set

    fun setCep(cep: String?){
        cep?.let { enderecoApi = enderecoApi.copy(cep = it) }
    }

    fun setLogradouro(logradouro:String?){
        logradouro?.let { enderecoApi = enderecoApi.copy(logradouro = it) }
    }

    fun setComplemento(complemento:String?){
        complemento?.let { enderecoApi = enderecoApi.copy(complemento = it) }
    }

    fun setUnidade(unidade: String?){
        unidade?.let { enderecoApi = enderecoApi.copy(unidade = it) }
    }

    fun setBairro(bairro:String?){
        bairro?.let { enderecoApi = enderecoApi.copy(bairro = it) }
    }

    fun setLocalidade(localidade:String?){
        localidade?.let { enderecoApi = enderecoApi.copy(localidade = it) }
    }

    fun setUf(uf:String?){
        uf?.let { enderecoApi = enderecoApi.copy(uf = it) }
    }

    fun setEstado(estado:String?){
        estado?.let { enderecoApi = enderecoApi.copy(estado = it) }
    }

    fun setRegiao(regiao:String?){
        regiao?.let { enderecoApi = enderecoApi.copy(regiao = it) }
    }

    fun setIbge(ibge:String?){
        ibge?.let { enderecoApi = enderecoApi.copy(ibge = it) }
    }

    fun setGia(gia: String?){
        gia?.let { enderecoApi = enderecoApi.copy(gia = it) }
    }

    fun setDdd(ddd: String?){
        ddd?.let { enderecoApi = enderecoApi.copy(ddd = it) }
    }

    fun setSiafi(siafi:String?){
        siafi?.let { enderecoApi = enderecoApi.copy(siafi = it) }
    }

}