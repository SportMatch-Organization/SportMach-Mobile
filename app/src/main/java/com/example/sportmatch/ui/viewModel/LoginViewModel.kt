package com.example.sportmatch.ui.viewModel
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportmatch.data.api.LoginApi.kt.RemoteLoginDataSource
import com.example.sportmatch.data.repositorio.RepositorioAutenticacao
import com.example.sportmatch.data.database.Login.LocalLoginDataSource
import com.example.sportmatch.data.database.SportMatchDatabase
import kotlinx.coroutines.launch
class LoginViewModel(
    private val database: SportMatchDatabase
) : ViewModel() {
    private val repository = RepositorioAutenticacao(
        localLoginDataSource = LocalLoginDataSource(database.loginCacheDao()),
        remoteLoginDataSource = RemoteLoginDataSource()
    )
    var email by mutableStateOf("")
    var senha by mutableStateOf("")
    var emailError by mutableStateOf<String?>(null)
    var senhaError by mutableStateOf<String?>(null)
    var passwordVisible by mutableStateOf(false)
    var rememberMe by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    fun onEmailChange(value: String) {
        email = value
        emailError = null
    }
    fun onSenhaChange(value: String) {
        senha = value
        senhaError = null
    }
    fun togglePasswordVisibility() {
        passwordVisible = !passwordVisible
    }
    fun toggleRememberMe() {
        rememberMe = !rememberMe
    }
    fun login(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        emailError = null
        senhaError = null
        var valid = true
        if (email.isBlank()) {
            emailError = "E-mail é obrigatório"
            valid = false
        }
        if (senha.isBlank()) {
            senhaError = "Senha é obrigatória"
            valid = false
        }
        if (!valid) return
        isLoading = true
        viewModelScope.launch {
            try {
                repository.login(email.trim(), senha.trim())
                isLoading = false
                onSuccess()
            } catch (e: Exception) {
                isLoading = false
                senhaError = "E-mail ou senha inválidos"
                onError(e.message ?: "Erro desconhecido")
            }
        }
    }
}