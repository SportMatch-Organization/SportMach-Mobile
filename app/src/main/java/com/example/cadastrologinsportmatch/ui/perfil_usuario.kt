package com.example.cadastrologinsportmatch.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class UserProfileData(
    val nomeCompleto: String = "",
    val apelido: String = "",
    val perfil: String = "",
    val genero: String = "",
    val cpf: String = "",
    val telefone: String = "",
    val dataNascimento: String = ""
)

data class ProfileErrors(
    val nomeCompletoError: String? = null,
    val apelidoError: String? = null,
    val perfilError: String? = null,
    val generoError: String? = null,
    val cpfError: String? = null,
    val telefoneError: String? = null,
    val dataNascimentoError: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilUsuario(
    onNavigateBack: () -> Unit,
    onOnboardingComplete: () -> Unit //
) {
    var currentStep by remember { mutableStateOf(1) }
    var userProfileData by remember { mutableStateOf(UserProfileData()) }

    var errors by remember { mutableStateOf(ProfileErrors()) }
    var isLoading by remember { mutableStateOf(false) }

    val context = LocalContext.current

    fun saveProfileData() {

        val data = userProfileData
        var newErrors = ProfileErrors()
        var valido = true

        if (data.nomeCompleto.isBlank()) {
            newErrors = newErrors.copy(nomeCompletoError = "Digite o nome")
            valido = false
        }
        if (data.apelido.isBlank()) {
            newErrors = newErrors.copy(apelidoError = "Digite um apelido para o usuário")
            valido = false
        }
        if (data.cpf.isBlank()) {
            newErrors = newErrors.copy(cpfError = "Digite o CPF")
            valido = false
        } else if (!data.cpf.matches(Regex("\\d{11}"))) {
            newErrors = newErrors.copy(cpfError = "CPF inválido (deve conter 11 dígitos)")
            valido = false
        }
        if (data.dataNascimento.isBlank()) {
            newErrors = newErrors.copy(dataNascimentoError = "Digite a data de nascimento")
            valido = false
        }
        if (data.genero.isBlank()) {
            newErrors = newErrors.copy(generoError = "Digite o gênero")
            valido = false
        }
        if (data.telefone.isBlank()) {
            newErrors = newErrors.copy(telefoneError = "Digite o telefone")
            valido = false
        }
        if (data.perfil.isBlank()) {
            newErrors = newErrors.copy(perfilError = "Escolha um perfil")
            valido = false
        }
        errors = newErrors

        if (!valido) {
            Toast.makeText(context, "Por favor, corrija os erros no formulário.", Toast.LENGTH_LONG).show()
            return
        }

        isLoading = true

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid == null) {
            Toast.makeText(context, "Erro: Usuário não autenticado.", Toast.LENGTH_LONG).show()
            isLoading = false
            return
        }

        val db = Firebase.firestore

        db.collection("usuarios").document(uid)
            .set(userProfileData) //
            .addOnSuccessListener {
                isLoading = false
                Toast.makeText(context, "Perfil salvo com sucesso!", Toast.LENGTH_SHORT).show()
                onOnboardingComplete()
            }
            .addOnFailureListener { e ->
                isLoading = false
                Toast.makeText(context, "Erro ao salvar perfil: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (currentStep == 1) "Informações do perfil" else "Informações pessoais",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (currentStep == 1) {
                            onNavigateBack()
                        } else {
                            currentStep = 1
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF97316),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            when (currentStep) {
                1 -> ProfileStep1(
                    data = userProfileData,
                    errors = errors,
                    onDataChange = { userProfileData = it; errors = ProfileErrors() },
                    onNextClick = { currentStep = 2 }
                )
                2 -> ProfileStep2(
                    data = userProfileData,
                    errors = errors,
                    onDataChange = { userProfileData = it; errors = ProfileErrors() },
                    isLoading = isLoading,
                    onNextClick = {
                        saveProfileData()
                    }
                )
            }
        }
    }
}

@Composable
fun ProfileStep1(
    data: UserProfileData,
    errors: ProfileErrors,
    onDataChange: (UserProfileData) -> Unit,
    onNextClick: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(32.dp))
        Text("Nome Completo", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = data.nomeCompleto,
            onValueChange = { onDataChange(data.copy(nomeCompleto = it)) },
            placeholder = { Text("Digite seu nome completo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = errors.nomeCompletoError != null
        )
        if (errors.nomeCompletoError != null) {
            Text(
                text = errors.nomeCompletoError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Como deseja ser chamado?", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = data.apelido,
            onValueChange = { onDataChange(data.copy(apelido = it)) },
            placeholder = { Text("Digite o nome") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = errors.apelidoError != null
        )
        if (errors.apelidoError != null) {
            Text(
                text = errors.apelidoError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Perfil", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = data.perfil,
            onValueChange = { onDataChange(data.copy(perfil = it)) },
            placeholder = { Text("Escolha uma opção") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = errors.perfilError != null
        )
        if (errors.perfilError != null) {
            Text(
                text = errors.perfilError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF97316)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Próximo", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ProfileStep2(
    data: UserProfileData,
    errors: ProfileErrors,
    onDataChange: (UserProfileData) -> Unit,
    isLoading: Boolean,
    onNextClick: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(32.dp))

        Text("Gênero", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = data.genero,
            onValueChange = { onDataChange(data.copy(genero = it)) },
            placeholder = { Text("Escolha uma opção") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = errors.generoError != null
        )
        if (errors.generoError != null) {
            Text(
                text = errors.generoError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo CPF
        Text("CPF", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = data.cpf,
            onValueChange = { onDataChange(data.copy(cpf = it)) },
            placeholder = { Text("Digite seu Cpf") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = errors.cpfError != null
        )
        if (errors.cpfError != null) {
            Text(
                text = errors.cpfError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Telefone", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = data.telefone,
            onValueChange = { onDataChange(data.copy(telefone = it)) },
            placeholder = { Text("Digite seu telefone") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = errors.telefoneError != null
        )
        if (errors.telefoneError != null) {
            Text(
                text = errors.telefoneError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Data de nascimento", style = MaterialTheme.typography.labelMedium)
        OutlinedTextField(
            value = data.dataNascimento,
            onValueChange = { onDataChange(data.copy(dataNascimento = it)) },
            placeholder = { Text("DD/MM/AAAA") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            isError = errors.dataNascimentoError != null
        )
        if (errors.dataNascimentoError != null) {
            Text(
                text = errors.dataNascimentoError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.BottomCenter) {
            Button(
                onClick = onNextClick,
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(bottom = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF97316)),
                shape = RoundedCornerShape(8.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text("Próximo", fontSize = 16.sp)
                }
            }
        }
    }
}