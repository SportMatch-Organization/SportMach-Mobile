package com.example.sportmatch.ui.data.repositorio
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth

class RepositorioAutenticacao(
        private val auth: FirebaseAuth = FirebaseAuth.getInstance()
){
        suspend fun login(email: String, senha: String) {
            auth.signInWithEmailAndPassword(email, senha).await()
        }
    }