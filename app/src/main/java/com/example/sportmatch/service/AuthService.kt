package com.example.sportmatch.service

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class AuthService {

    private val auth: FirebaseAuth = Firebase.auth

    fun cadastrarUsuario(
        email: String,
        senha: String,
        onComplete: (sucesso: Boolean, uid: String?, erroMsg: String?) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = task.result?.user?.uid
                    onComplete(true, uid, null)
                } else {
                    val errorMessage = task.exception?.localizedMessage ?: "Erro desconhecido."
                    onComplete(false, null, errorMessage)
                }
            }
    }
}