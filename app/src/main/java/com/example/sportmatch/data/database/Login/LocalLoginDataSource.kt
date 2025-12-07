package com.example.sportmatch.data.database.Login
import com.example.sportmatch.data.database.dao.LoginCacheDao
import com.example.sportmatch.data.database.entities.LoginCacheEntity

class LocalLoginDataSource(
    private val dao: LoginCacheDao
) {
    suspend fun salvarLogin(email: String, senha: String, uid: String) {
        dao.upsert(LoginCacheEntity(email, senha, uid))
    }
    suspend fun buscarLogin(email: String, senha: String): LoginCacheEntity? {
        return dao.buscarLogin(email, senha)
    }
}