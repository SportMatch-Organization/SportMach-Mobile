package com.example.sportmatch.data.api.LoginApi
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class RemoteLoginDataSource(
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) {
    suspend fun login(email: String, senha: String): String {
        val result = auth.signInWithEmailAndPassword(email, senha).await()
        return result.user?.uid ?: throw Exception("Usuário sem UID")
    }
}