package com.example.cadastrologinsportmatch.ui.cadastro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cadastrologinsportmatch.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro2(onNavigateToCadastro3: () -> Unit){

    // Dropdown de Perfil
    val perfis = listOf("Organizador", "Atleta", "Locador", "Patrocinador")
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
            value = "",
            onValueChange = { },
            label = { Text("Digite seu nome completo/Razão social") },
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Como deseja ser chamado?",
            color = Color.Black,
            fontSize = 24.sp
        )

        TextField(
            value = "",
            onValueChange = { },
            label = { Text("Digite o nome") },
            modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Perfil",
            color = Color.Black,
            fontSize = 24.sp
        )

        ExposedDropdownMenuBox(
            expanded = true,
            onExpandedChange = {}) {
            TextField(
                value = "",
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Escolha uma opção")
                },

                trailingIcon = {
                    ExposedDropdownMenuDefaults
                        .TrailingIcon(expanded = true)
                },
                modifier = Modifier.fillMaxWidth()
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

        Button(
            onClick = {
                onNavigateToCadastro3()
            },
            modifier = Modifier
                .fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = Orange,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Próximo"
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro2Preview(){
    Cadastro2({})
}