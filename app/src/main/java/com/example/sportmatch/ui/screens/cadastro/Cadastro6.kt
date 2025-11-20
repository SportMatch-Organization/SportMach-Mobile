package com.example.sportmatch.ui.screens.cadastro

import android.annotation.SuppressLint
import android.os.Build
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
import androidx.annotation.RequiresApi
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import com.example.sportmatch.database.SportMatchDatabase
import com.example.sportmatch.database.entities.user.EsportesInteresse
import com.example.sportmatch.service.AuthService // Importa o serviço do Firebase
import com.example.sportmatch.ui.viewModel.user.CadastroViewModel
import com.example.sportmatch.ui.components.CustomButton
import com.example.sportmatch.ui.theme.Azul2E3E4B
import com.example.sportmatch.ui.theme.Orange
import com.example.sportmatch.ui.viewModel.EspacoEsportivoViewModel
import com.example.sportmatch.ui.viewModel.user.EsportesInteresseViewModel
import kotlinx.coroutines.launch

@Composable
fun Cadastro6(
    viewModel: CadastroViewModel,
    esportesInteresseViewModel: EsportesInteresseViewModel,
    authService: AuthService = remember { AuthService() },
    onNavigateToLogin: () -> Unit
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isCadastroLoading by remember { mutableStateOf(false) }

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

        FlowRow(modifier = Modifier.fillMaxWidth()){
            for (esporte in esportesInteresseViewModel.esportesInteresse){
                SportsOfInterestButton(esportesInteresseViewModel, esporte)
            }
        }

        Spacer(modifier = Modifier.height(256.dp))

        CustomButton(
            "Cadastrar",
            {
                isCadastroLoading = true

                authService.cadastrarUsuario(viewModel.user.email, viewModel.user.senha) { sucesso, uid, erroMsg ->
                    if (sucesso) {
                        uid?.let { viewModel.setId(uid) }

                        scope.launch {
                            val userDao = SportMatchDatabase.getDatabase(context).userDao()
                            userDao.insert(viewModel.user)
                            for(esporteSelecionado in esportesInteresseViewModel.esportesInteresseSelecionados){
                                SportMatchDatabase.getDatabase(context).esportesInteresseDao().insert(
                                    EsportesInteresse(idUsuario = viewModel.user.id, esportesInteresse = esporteSelecionado))
                            }
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

@Composable
fun SportsOfInterestButton(esportesInteresseViewModel: EsportesInteresseViewModel, esporte: String) {
    Button(
        modifier = Modifier.padding(4.dp),
        onClick = {
            esportesInteresseViewModel.esportesInteresseSelecionados.add(esporte)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Azul2E3E4B
        )

    ) {
        Text(esporte)
    }
}


/*@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Cadastro6Preview(){
    Cadastro6(CadastroViewModel(), EspacoEsportivoViewModel(),  onNavigateToLogin = {})
}*/