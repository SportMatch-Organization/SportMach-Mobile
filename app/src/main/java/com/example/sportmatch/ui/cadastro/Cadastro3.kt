package com.example.sportmatch.ui.cadastro

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.example.sportmatch.database.UserTypeConverters
import com.example.sportmatch.model.CadastroViewModel
import com.example.sportmatch.model.enumTypes.user.GeneroEnum
import java.lang.Exception
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro3(
    viewModel: CadastroViewModel,
    onNavigateToCadastro4: () -> Unit
){

    var generos = GeneroEnum.entries.map { it.toString()}
    var genero by remember { mutableStateOf(generos[0])}
    var generoLabel by remember { mutableStateOf("Escolha uma opção") }
    var cpfCnpj by remember { mutableStateOf("")}
    var telefone by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var generoExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp),
        verticalArrangement = Arrangement.Top) {

        Text(
            text = "Informações pessoais",
            color = Color.Black,
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Text("Gênero")
        Spacer(modifier = Modifier.height(8.dp))
        ExposedDropdownMenuBox(
            expanded = generoExpanded,
            onExpandedChange = { generoExpanded = it } // muda generoExtended para true
        ){
            TextField(
                value = genero,
                onValueChange = { genero = it},
                readOnly = true,

                trailingIcon = {
                    ExposedDropdownMenuDefaults
                        .TrailingIcon(expanded = generoExpanded)
                },
                modifier = Modifier.fillMaxWidth()
                    .menuAnchor(
                        type = ExposedDropdownMenuAnchorType.PrimaryEditable,
                        enabled = true
                    )
            )
            ExposedDropdownMenu(
                expanded = generoExpanded,
                onDismissRequest = {
                    generoExpanded = false
                }) {
                generos.forEach {
                    item ->
                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {
                            genero = item
                            generoExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Cpf/Cnpj")

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = cpfCnpj,
            onValueChange = { cpfCnpj = it },
            label = {
                Text("Digite seu Cpf")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Telefone")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = telefone,
            onValueChange = { telefone = it },
            label = {
                Text("Digite seu telefone")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Data de nascimento")
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = dataNascimento,
            onValueChange = { dataNascimento = it },
            label = {
                Text("DD/MM/AAAA")
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(256.dp))

        Button(
            onClick = {
                // só passa o valor se não for nulo
                viewModel.setGenero(genero)
                viewModel.setCpfCnpj(cpfCnpj)
                viewModel.setTelefone(telefone)
                // só passa o valor se não for nulo
                if (UserTypeConverters().stringToLocalDate(dataNascimento) != null){
                    viewModel.setDataNascimento(dataNascimento)
                }
                onNavigateToCadastro4()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Próximo")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro3Preview(){
    Cadastro3(viewModel {CadastroViewModel()}, {})
}