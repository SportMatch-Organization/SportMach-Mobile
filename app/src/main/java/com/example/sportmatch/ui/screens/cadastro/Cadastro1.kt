package com.example.sportmatch.ui.screens.cadastro
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.model.CadastroViewModel
import com.example.sportmatch.model.CreateUserDto
import com.example.sportmatch.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro1(
    viewModel: CadastroViewModel,
    onNavigateToCadastro2: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var repetirEmail by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var repetirSenha by remember { mutableStateOf("") }

    var nomeError by remember { mutableStateOf<String?>(null) }
    var usuarioError by remember { mutableStateOf<String?>(null) }
    var cpfCnpjError by remember { mutableStateOf<String?>(null) }
    var dataNascimentoError by remember { mutableStateOf<String?>(null) }
    var generoError by remember { mutableStateOf<String?>(null) }
    var telefoneError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var senhaError by remember { mutableStateOf<String?>(null) }
    var enderecoError by remember { mutableStateOf<String?>(null) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Cadastre-se",
            color = Color.Black,
            fontSize = 24.sp)

        Spacer(modifier = Modifier
            .height(32.dp)
        )

        Text("Informe seu E-mail e crie uma senha",
            style = MaterialTheme
                .typography
                .titleLarge)
        Spacer(modifier = Modifier
            .height(16.dp))

        Text("E-mail",
            style = MaterialTheme
                .typography
                .titleLarge)
        Spacer(modifier = Modifier
            .height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Digite seu email") },
            modifier = Modifier.fillMaxWidth()
        )
        if (nomeError != null)
            Text(
                nomeError!!,
                color = Color.Red
            )

        Spacer(
            modifier = Modifier
                .height(12.dp)
        )


        Text("Repita o E-mail",
            style = MaterialTheme
                .typography
                .titleLarge)

        Spacer(modifier = Modifier
            .height(16.dp))

        TextField(
            value = repetirEmail,
            onValueChange = { repetirEmail = it },
            label = { Text("Digite seu email") },
            modifier = Modifier.fillMaxWidth())
        if (nomeError != null)
            Text(
                nomeError!!,
                color = Color.Red
            )

        Spacer(modifier = Modifier
            .height(16.dp))

        Text("Crie uma senha",
            style = MaterialTheme
                .typography
                .titleLarge)
        Spacer(modifier = Modifier
            .height(16.dp))

        TextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Digite sua senha") },
            modifier = Modifier.fillMaxWidth())
        if (nomeError != null)
            Text(
                nomeError!!,
                color = Color.Red
            )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Repita a senha",
            style = MaterialTheme
                .typography
                .titleLarge)
        Spacer(modifier = Modifier
            .height(16.dp))

        TextField(
            value = repetirSenha,
            onValueChange = { repetirSenha = it },
            label = { Text("Digite sua senha") },
            modifier = Modifier.fillMaxWidth())
        if (nomeError != null)
            Text(
                nomeError!!,
                color = Color.Red
            )

        Spacer(
            modifier = Modifier
                .height(128.dp)
        )

        Button(
            onClick = {
                viewModel.setEmail(email)
                viewModel.setSenha(senha)
                onNavigateToCadastro2()
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Orange)
        ) {
            Text("Próximo")
        }
    }
}

@Preview
@Composable
fun Cadastro1Preview() {
    Cadastro1(viewModel { CadastroViewModel() }, onNavigateToCadastro2 = {})
}
