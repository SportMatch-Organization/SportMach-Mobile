package com.example.sportmatch.data.repositorio

import com.example.sportmatch.data.api.LoginApi.kt.RemoteLoginDataSource
import com.example.sportmatch.data.database.Login.LocalLoginDataSource

class RepositorioAutenticacao(
    private val remoteLoginDataSource: RemoteLoginDataSource,
    private val localLoginDataSource: LocalLoginDataSource
) {
    suspend fun login(email: String, senha: String): String {
        var uid = ""
        try {
            uid = remoteLoginDataSource.login(email, senha)
            localLoginDataSource.salvarLogin(email, senha, uid)
        } catch (e: Exception) {
            val cache = localLoginDataSource.buscarLogin(email, senha)
            if (cache != null) {
                uid = cache.uid
            } else {
                throw Exception("Falha ao autenticar: API indisponível e nenhum cache encontrado.")
            }
        }
        return uid
    }
}