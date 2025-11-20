package com.example.sportmatch.ui.screens.cadastro
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.api.viaCepApi.Endereco
import com.example.sportmatch.api.viaCepApi.RetrofitClient
import com.example.sportmatch.database.converters.UserTypeConverters
import com.example.sportmatch.ui.viewModel.user.CadastroViewModel
import com.example.sportmatch.ui.viewModel.user.EnderecoUsuarioViewModel
import com.example.sportmatch.ui.components.CustomButton
import com.example.sportmatch.ui.theme.Orange

@Composable
fun Cadastro4(
    viewModel: CadastroViewModel,
    enderecoUsuarioViewModel: EnderecoUsuarioViewModel,
    onNavigateToCadastro5: () -> Unit
){

    var enderecoApi by remember { mutableStateOf<Endereco?>(null) }
    var cep by remember { mutableStateOf("") }
    var context = LocalContext.current
    var mensagemErro by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
            verticalArrangement = Arrangement.Top) {
        Text(
            text = "Qual o seu CEP?",
            color = Color.Black,
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(64.dp)
        )

        Text("CEP")

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = cep,
            onValueChange = { cep = it },
            label = {
                Text("Digite seu CEP")
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (cep != ""){
            var validaCepNum = "[0-9]{8}".toRegex()
            var validaCepComHifem = "[0-9]{5}-[0-9]{3}".toRegex()

            if ((cep.matches(validaCepNum)) || (cep.matches(validaCepComHifem))){
                LaunchedEffect(Unit){
                    var response = RetrofitClient.viaCepApi.buscarEnderecoPorCep(cep)
                    //Pode receber tanto um endereço como uma mensagem de erro
                    if (response.isSuccessful){
                        enderecoApi = response.body()
                        preencherEndereco(enderecoUsuarioViewModel, enderecoApi)
                    } else{
                        mensagemErro = "Endereço não encontrado."
                    }
                }
            } else{
                mensagemErro = "CEP inválido."
            }
        } else{
            mensagemErro = "CEP não informado."
        }

        Spacer(modifier = Modifier.height(512.dp))

        CustomButton(
            "Próximo",
            {
                if (enderecoApi != null){
                    viewModel.setCep(cep)
                    onNavigateToCadastro5()
                }else{
                    Toast.makeText(context, mensagemErro, Toast.LENGTH_SHORT).show()
                }
            },
            backgroundColor = Orange
        )
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro4Preview(){
    Cadastro4(viewModel { CadastroViewModel()}, viewModel { EnderecoUsuarioViewModel()}, {})
}

fun preencherEndereco(enderecoUsuarioViewModel: EnderecoUsuarioViewModel, enderecoApi: Endereco?){
    enderecoUsuarioViewModel.setCep(enderecoApi?.cep)
    enderecoUsuarioViewModel.setLogradouro(enderecoApi?.logradouro)
    enderecoUsuarioViewModel.setComplemento(enderecoApi?.complemento)
    enderecoUsuarioViewModel.setUnidade(enderecoApi?.unidade)
    enderecoUsuarioViewModel.setBairro(enderecoApi?.bairro)
    enderecoUsuarioViewModel.setLocalidade(enderecoApi?.localidade)
    enderecoUsuarioViewModel.setUf(enderecoApi?.uf)
    enderecoUsuarioViewModel.setEstado(enderecoApi?.estado)
    enderecoUsuarioViewModel.setRegiao(enderecoApi?.regiao)
    enderecoUsuarioViewModel.setIbge(enderecoApi?.ibge)
    enderecoUsuarioViewModel.setGia(enderecoApi?.gia)
    enderecoUsuarioViewModel.setDdd(enderecoApi?.ddd)
    enderecoUsuarioViewModel.setSiafi(enderecoApi?.siafi)
}