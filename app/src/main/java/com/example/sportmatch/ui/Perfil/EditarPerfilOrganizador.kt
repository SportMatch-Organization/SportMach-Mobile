package com.example.sportmatch.ui.screens.perfil
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sportmatch.ui.components.CampoFormulario
import com.example.sportmatch.ui.components.HeaderEdicaoFoto
import com.example.sportmatch.ui.components.TituloSecao
import com.example.sportmatch.ui.theme.TextoEscuro
import com.example.sportmatch.ui.theme.laranjaPrincipal
import com.example.sportmatch.ui.viewModel.EditarPerfilOrganizadorViewModel
import com.example.sportmatch.ui.components.CampoSenha
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilOrganizadorScreen(
    onVoltar: () -> Unit,
    onSalvar: () -> Unit,
    viewModel: EditarPerfilOrganizadorViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Editar Perfil", fontWeight = FontWeight.Bold, color = TextoEscuro) },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar", tint = TextoEscuro)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    viewModel.salvarDados()
                    onSalvar()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = laranjaPrincipal),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Salvar Alterações", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            HeaderEdicaoFoto(
                fotoUrl = uiState.fotoUrl,
                aoClicarEditar = { }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                TituloSecao("Dados da Organização:")
                CampoFormulario(
                    label = "Nome da Organização/Empresa",
                    valor = uiState.nomeOrganizacao,
                    onValorChange = { viewModel.atualizarNome(it) }
                )
                Spacer(modifier = Modifier.height(12.dp))
                CampoFormulario(
                    label = "CNPJ",
                    valor = uiState.cnpj,
                    onValorChange = { viewModel.atualizarCnpj(it) }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                TituloSecao("Alteração de senha:")
                CampoSenha(
                    label = "Senha",
                    valor = uiState.senha,
                    onValorChange = { viewModel.atualizarSenha(it) }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                TituloSecao("Contato:")
                CampoFormulario(
                    label = "E-mail comercial",
                    valor = uiState.email,
                    onValorChange = { viewModel.atualizarEmail(it) }
                )
                Spacer(modifier = Modifier.height(12.dp))
                CampoFormulario(
                    label = "Telefone/WhatsApp",
                    valor = uiState.telefone,
                    onValorChange = { viewModel.atualizarTelefone(it) }
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                TituloSecao("Endereço:")
                CampoFormulario(
                    label = "CEP",
                    valor = uiState.cep,
                    onValorChange = { viewModel.atualizarCep(it) }
                )
                Spacer(modifier = Modifier.height(12.dp))
                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                    TituloSecao("Endereço:")
                    CampoFormulario(
                        label = "Estado",
                        valor = uiState.estado,
                        onValorChange = { viewModel.atualizarEstado(it) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    CampoFormulario(
                        label = "Cidade",
                        valor = uiState.cidade,
                        onValorChange = { viewModel.atualizarCidade(it) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    CampoFormulario(
                        label = "Endereço",
                        valor = uiState.logradouro,
                        onValorChange = { viewModel.atualizarLogradouro(it) }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CampoFormulario(
                            label = "Número",
                            valor = uiState.numero,
                            onValorChange = { viewModel.atualizarNumero(it) },
                            modifier = Modifier.weight(0.3f)
                        )
                        CampoFormulario(
                            label = "Completo",
                            valor = uiState.complemento,
                            onValorChange = { viewModel.atualizarComplemento(it) },
                            modifier = Modifier.weight(0.7f)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}