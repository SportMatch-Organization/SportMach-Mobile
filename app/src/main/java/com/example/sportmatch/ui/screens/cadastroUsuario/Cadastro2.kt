package com.example.sportmatch.ui.screens.cadastroUsuario
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.ui.viewModel.user.CadastroViewModel
import com.example.sportmatch.database.entities.user.PerfilEnum
import com.example.sportmatch.ui.components.CustomButton
import com.example.sportmatch.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro2(
    viewModel: CadastroViewModel,
    onNavigateToCadastro3: () -> Unit
){

    // Dropdown de Perfil
    var nome by remember { mutableStateOf("") }
    var usuario by remember { mutableStateOf("") }
    var perfilLabel by remember {mutableStateOf("Escolha uma opção")}
    var perfis = PerfilEnum.entries.map { it.toString() }
    var perfil by remember { mutableStateOf(perfis[0]) }
    var perfilExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Top) {

        Text(
            text = "Informações do perfil",
            color = Color.Black,
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Text(
            text = "Nome completo/Razão social",
            color = Color.Black,
            fontSize = 24.sp
        )

        TextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Digite seu nome completo/Razão social") },
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Como deseja ser chamado?",
            color = Color.Black,
            fontSize = 24.sp
        )

        TextField(
            value = usuario,
            onValueChange = { usuario = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Digite o nome")
            }
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Perfil",
            color = Color.Black,
            fontSize = 24.sp
        )

        ExposedDropdownMenuBox(
            expanded = perfilExpanded,
            onExpandedChange = {perfilExpanded = true}) {
            TextField(
                value = perfil,
                onValueChange = { perfil = it },
                readOnly = true,

                trailingIcon = {
                    ExposedDropdownMenuDefaults
                        .TrailingIcon(expanded = false)
                },
                label = {perfilLabel},
                modifier = Modifier.fillMaxWidth()
                    .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                    enabled = true
                    )
            )
            ExposedDropdownMenu(
                expanded = perfilExpanded,
                onDismissRequest = { perfilExpanded = false }) {
                perfis.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {
                            perfil = item
                            perfilExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(256.dp)
        )

        CustomButton(
            "Próximo",
            {
                viewModel.setNome(nome)
                viewModel.setUsuario(usuario)
                viewModel.setPerfil(perfil)
                onNavigateToCadastro3()
            },
            backgroundColor = Orange
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro2Preview(){
    Cadastro2(viewModel { CadastroViewModel() }, onNavigateToCadastro3 = {})
}