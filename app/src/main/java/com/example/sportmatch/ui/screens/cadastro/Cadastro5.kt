package com.example.sportmatch.ui.screens.cadastro
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.sportmatch.api.viaCepApi.Endereco
import com.example.sportmatch.api.viaCepApi.RetrofitClient
import com.example.sportmatch.ui.viewModel.user.CadastroViewModel
import com.example.sportmatch.ui.viewModel.user.EnderecoUsuarioViewModel
import com.example.sportmatch.ui.components.CustomButton
import com.example.sportmatch.ui.theme.Orange
import retrofit2.Response

@Composable
fun Cadastro5(
    viewModel: CadastroViewModel,
    enderecoUsuarioViewModel: EnderecoUsuarioViewModel,
    onNavigateToCadastro6: () -> Unit
){
    //if { erro: true } ...
    // if { status: BadRequest }

    var estado by remember { mutableStateOf(enderecoUsuarioViewModel.enderecoApi.estado) }
    var cidade by remember { mutableStateOf(enderecoUsuarioViewModel.enderecoApi.localidade) }
    var logradouro by remember { mutableStateOf(enderecoUsuarioViewModel.enderecoApi.logradouro) }
    var bairro by remember { mutableStateOf(enderecoUsuarioViewModel.enderecoApi.bairro) }
    var numero by remember { mutableStateOf("") }
    var complemento by remember { mutableStateOf(enderecoUsuarioViewModel.enderecoApi.complemento) }
    var endereco by remember { mutableStateOf("") }
    /*LaunchedEffect(Unit){
       var enderecoApi = RetrofitClient.viaCepApi.buscarEnderecoPorCep(viewModel.user.cep)
    }*/

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
            verticalArrangement = Arrangement.Top) {
        Text(
            text = "Complete seus dados",
            color = Color.Black,
            fontSize = 24.sp
        )

        Text(
            text = "Falta pouco!",
            color = Color.Black,
            fontSize = 12.sp
        )

        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Text("Logradouro")

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = logradouro,
            onValueChange = { logradouro = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Digite seu logradouro")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.
            fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Surface(
                modifier = Modifier
                    .weight(1f)
            ) {
                Column {
                    Text("Número")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = numero,
                        onValueChange = { numero = it },
                        label = {
                            Text("n°")
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                modifier = Modifier
                    .weight(2f)
            ) {
                Column {
                    Text("Complemento")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = complemento,
                        onValueChange = { complemento = it },
                        label = {
                            Text("Complemento")
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Bairro")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = bairro,
            onValueChange = { bairro = it },
            label = {
                Text("Bairro")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Cidade")
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = cidade,
            onValueChange = { cidade = it },
            label = {
                Text("Digite sua cidade")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Estado")

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = estado,
            onValueChange = { estado = it },
            label = {
                Text("Digite seu estado")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(128.dp))

        CustomButton(
            "Próximo",
            {
                endereco = "$logradouro, $numero, $bairro, $cidade - $estado"
                viewModel.setEndereco(endereco)
                onNavigateToCadastro6()
            },
            backgroundColor = Orange
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro5Preview(){
    Cadastro5(viewModel { CadastroViewModel()}, viewModel { EnderecoUsuarioViewModel()},{})
}