package com.example.sportmatch.ui.screens.cadastro

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.widget.Toast
import androidx.compose.material3.ButtonDefaults
import com.example.sportmatch.database.SportMatchDatabase
import com.example.sportmatch.service.AuthService // Importa o serviço do Firebase
import com.example.sportmatch.model.CadastroViewModel
import com.example.sportmatch.ui.components.CustomButton
import com.example.sportmatch.ui.theme.Orange
import kotlinx.coroutines.launch

@Composable
fun Cadastro6(
    viewModel: CadastroViewModel,
    authService: AuthService = remember { AuthService() },
    onNavigateToLogin: () -> Unit
) {

    val context = LocalContext.current
    // utilizar Coroutine (threads paralelas)
    val scope = rememberCoroutineScope()
    var isCadastroLoading by remember { mutableStateOf(false) }
    var futebol by remember { mutableStateOf("Futebol") }

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Top){

        Text(
            text = "Quais os seus esportes de interesse?",
            color = Color.Black,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(64.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange,
                contentColor = Color.White
            )
        ) {
            Text(futebol)
        }

        Spacer(modifier = Modifier.height(512.dp))

        CustomButton(
            "Cadastrar",
            {
                isCadastroLoading = true

                authService.cadastrarUsuario(viewModel.user.email, viewModel.user.senha) { sucesso, uid, erroMsg ->
                    if (sucesso) {
                        uid?.let { viewModel.setId(uid) }
                        viewModel.setEsporteInteresse(futebol)

                        scope.launch {
                            val userDao = SportMatchDatabase.getDatabase(context).userDao()
                            userDao.insert(viewModel.user)
                        }
                        isCadastroLoading = false
                        Toast.makeText(context, "Cadastro realizado com sucesso!",Toast.LENGTH_LONG).show()
                        onNavigateToLogin()
                    } else {
                        isCadastroLoading = false
                        Toast.makeText(context, "Ocorreu uma falha no cadastro: $erroMsg", Toast.LENGTH_LONG).show()
                    }
                }
            },
            backgroundColor = Orange
        )
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro6Preview(){
    Cadastro6(CadastroViewModel(), onNavigateToLogin = {})
}