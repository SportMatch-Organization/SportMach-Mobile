package com.example.cadastrologinsportmatch.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.cadastrologinsportmatch.model.CreateUserDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(onCadastro: (CreateUserDto) -> Unit) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Cadastro",
            style = MaterialTheme
                .typography
                .titleLarge)
        Spacer(modifier = Modifier
                .height(16.dp))

        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome completo") })
        if (nomeError != null)
            Text(
                nomeError!!,
                color = Color.Red
            )

        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = {
                Text(
                    "Usuário (apelido)") }
        )
        if (usuarioError != null)
            Text(
                usuarioError!!,
                color = Color.Red
            )

        TextField(
            value = cpfCnpj,
            onValueChange = { cpfCnpj = it },
            label = { Text("CPF ou CNPJ") }
        )
        if (cpfCnpjError != null)
            Text(cpfCnpjError!!,
                color = Color.Red
            )

        TextField(
            value = dataNascimento,
            onValueChange = { dataNascimento = it },
            label = { Text("Data de Nascimento") }
        )
        if (dataNascimentoError != null)
            Text(
                dataNascimentoError!!,
                color = Color.Red
            )

        TextField(
            value = genero,
            onValueChange = { genero = it },
            label = { Text("Gênero") }
        )
        if (generoError != null)
            Text(generoError!!,
                color = Color.Red
            )

        TextField(
            value = telefone,
            onValueChange = { telefone = it },
            label = { Text("Telefone") }
        )
        if (telefoneError != null)
            Text(telefoneError!!,
                color = Color.Red
            )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") }
        )
        if (emailError != null)
            Text(emailError!!,
                color = Color.Red
            )

        TextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation()
        )
        if (senhaError != null)
            Text(
                senhaError!!,
                color = Color.Red
            )

        TextField(
            value = endereco,
            onValueChange = { endereco = it },
            label = { Text("Endereço") }
        )
        if (enderecoError != null)
            Text(
                enderecoError!!,
                color = Color.Red
            )

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        // Dropdown Perfil
        ExposedDropdownMenuBox(
            expanded = perfilExpanded,
            onExpandedChange = { perfilExpanded = it }) {
            TextField(
                value = perfil,
                onValueChange = {},
                readOnly = true,
                label = { Text("Perfil") },
                trailingIcon = { ExposedDropdownMenuDefaults
                    .TrailingIcon(expanded = perfilExpanded) },
                modifier = Modifier
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = perfilExpanded,
                onDismissRequest = { perfilExpanded = false }) {
                perfis.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            perfil = item
                            perfilExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier
            .height(16.dp))

        // Checkboxes de esportes
        Text("Esportes de Interesse (opcional)")
        esportesDisponiveis
            .forEach { esporte ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .toggleable(
                        value = esportesSelecionados.contains(esporte),
                        onValueChange = { checked ->
                            esportesSelecionados = if (checked) {
                                esportesSelecionados + esporte
                            } else {
                                esportesSelecionados - esporte
                            }
                        }
                    )
            ) {
                Checkbox(
                    checked = esportesSelecionados.contains(esporte),
                    onCheckedChange = null
                )
                Text(esporte)
            }
        }

        Spacer(modifier = Modifier
            .height(24.dp))

        Button(
            onClick = {
                var valido = true

                if (nome.isBlank()) {
                    nomeError = "Digite o nome";
                    valido = false
                } else {
                    nomeError = null
                }
                if (usuario.isBlank()) {
                    usuarioError = "Digite um apelido para o usuário";
                    valido = false
                } else {
                    usuarioError = null
                }

                if (cpfCnpj.isBlank()) {
                    cpfCnpjError = "Digite CPF ou CNPJ";
                    valido = false
                } else if
                        (!(cpfCnpj.matches(Regex("\\d{11}")) || cpfCnpj.matches(Regex("\\d{14}"))))
                {
                    cpfCnpjError = "CPF ou CNPJ inválido"; valido = false
                } else {
                    cpfCnpjError = null
                }

                if (dataNascimento.isBlank()) {
                    dataNascimentoError = "Digite a data de nascimento";
                    valido = false
                } else {
                    dataNascimentoError = null
                }
                if (genero.isBlank()) {
                    generoError = "Digite o gênero";
                    valido = false
                } else {
                    generoError = null
                }
                if (telefone.isBlank()) {
                    telefoneError = "Digite o telefone";
                    valido = false
                } else {
                    telefoneError = null
                }

                if (email.isBlank()) {
                    emailError = "Digite o e-mail";
                    valido = false
                }
                else if
                        (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailError = "E-mail inválido";
                    valido = false
                } else {
                    emailError = null
                }

                if (senha.isBlank()) {
                    senhaError = "Digite a senha";
                    valido = false
                }
                else if (senha.length < 8) {
                    senhaError = "Senha deve ter no mínimo 8 caracteres";
                    valido = false
                } else if
                        (senha.length > 12) {
                    senhaError = "Senha deve ter no máximo 12 caracteres";
                    valido = false
                }
                else if (!senha.contains(Regex("[!@#\$%^&*(),.?\":{}|<>]"))) {
                    senhaError = "Senha deve conter ao menos 1 caractere especial";
                    valido = false
                } else {
                    senhaError = null
                }

                if (endereco.isBlank()) {
                    enderecoError = "Digite o endereço"; valido = false
                } else {
                    enderecoError = null
                }

                if (valido) {
                    val user = CreateUserDto(
                        nome,
                        cpfCnpj,
                        dataNascimento,
                        genero,
                        telefone,
                        email,
                        senha,
                        endereco,
                        perfil.lowercase(),
                        esportesSelecionados
                    )
                    onCadastro(user)
                }

            },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("Cadastrar")
        }
    }
}
