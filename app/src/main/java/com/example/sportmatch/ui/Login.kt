package com.example.sportmatch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.sportmatch.model.LoginUserDto


@Composable
fun Login(
    onLogin: (LoginUserDto) -> Unit,
    onNavigateToCadastro: () -> Unit
    ){
    var email by remember {mutableStateOf("")}
    var senha by remember {mutableStateOf("")}
    var emailError by remember {mutableStateOf<String?>(null)}
    var senhaError by remember {mutableStateOf<String?>(null)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "SportMatch",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )
       TextField(
           value = email,
           onValueChange = {email = it},
           label = {Text("E-mail")},
           modifier = Modifier
               .fillMaxWidth()
               .padding(bottom = 16.dp)
       )
           if (emailError!= null){
               Text(
                   text = emailError!!,
                   color = Color.Red,
                   style = MaterialTheme.typography.bodySmall,
                   modifier = Modifier
                       .align(Alignment.Start)
                       .padding(start = 8.dp, top = 4.dp)
               )
       }
       TextField(
           value = senha,
           onValueChange = {senha = it},
           label = {Text("Senha")},
           visualTransformation = PasswordVisualTransformation(),
           modifier = Modifier
               .fillMaxWidth()
               .padding(bottom = 24.dp)
       )
           if (senhaError!= null) {
               Text(
                   text = senhaError!!,
                   color = Color.Red,
                   style = MaterialTheme.typography.bodySmall,
                   modifier = Modifier
                       .align(Alignment.Start)
                       .padding(start = 8.dp, top = 4.dp)
               )
           }
        Button(
            onClick = {
                var valid = true
                if (email.isBlank()){
                    emailError = "Digite o e-mail para fazer login"
                    valid = false
                }else if(
                    !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                ){
                    emailError = "Formato de e-mail inválido!"
                    valid = false
                }else{
                    emailError = null
                }
                if (senha.isBlank()){
                    senhaError = "A senha é obrigatóia"
                    valid = false
                }else if (senha.length<8){
                    senhaError = "A senha deve ter pelo menos 8 caracteres"
                    valid = false
                }else if (senha.length>12){
                    senhaError = "A senha deve ter no máximo 12 caracteres"
                    valid = false
                }else if (!senha.contains(Regex("[!@#\$%^&*(),.?\":{}|<>]"))) {
                    senhaError = "A senha deve conter pelo menos um caractere especial"
                    valid = false
                } else{
                    senhaError = null
                }
                if(valid){
                    val loginDto = LoginUserDto(email = email, senha = senha)
                    onLogin(loginDto)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ){
            Text(text = "Entrar")
        }
        Text(
            text = "Clique aqui para se cadastrar",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable{
                //direcionar para a tela de cadastro
                onNavigateToCadastro()
            }
        )
    }
}



























/*package com.example.cadastrologinsportmatch.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.cadastrologinsportmatch.model.LoginUserDto

@Composable
fun Login(onLogin: (LoginUserDto) -> Unit) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { onLogin(LoginUserDto(email, senha)) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }
    }
}
*/
