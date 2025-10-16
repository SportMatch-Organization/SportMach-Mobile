package com.example.cadastrologinsportmatch.ui.cadastro

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cadastrologinsportmatch.model.CreateUserDto
import com.example.cadastrologinsportmatch.ui.theme.Orange
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro1(
    onCadastro: (CreateUserDto) -> Unit,
    onNavigateToCadastro2: () -> Unit
) {
    var nome by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") } // apelido
    var cpfCnpj by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var genero by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var endereco by remember { mutableStateOf("") }

    var nomeError by remember { mutableStateOf<String?>(null) }
    var usuarioError by remember { mutableStateOf<String?>(null) }
    var cpfCnpjError by remember { mutableStateOf<String?>(null) }
    var dataNascimentoError by remember { mutableStateOf<String?>(null) }
    var generoError by remember { mutableStateOf<String?>(null) }
    var telefoneError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var senhaError by remember { mutableStateOf<String?>(null) }
    var enderecoError by remember { mutableStateOf<String?>(null) }

    // Dropdown de Perfil
    val perfis = listOf("Organizador", "Atleta", "Locador de espaço", "Patrocinador")
    var perfil by remember { mutableStateOf(perfis[0]) }
    var perfilExpanded by remember { mutableStateOf(false) }

    // Checkbox de esportes
    var esportesDisponiveis = listOf("Futebol", "Vôlei", "Basquete", "Natação", "Atletismo")
    var esportesSelecionados by remember { mutableStateOf(listOf<String>()) }

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
            value = nome,
            onValueChange = { nome = it },
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
            value = nome,
            onValueChange = { nome = it },
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
            value = nome,
            onValueChange = { nome = it },
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
            value = nome,
            onValueChange = { nome = it },
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
    Cadastro1(onCadastro = {}, onNavigateToCadastro2 = {})
}
