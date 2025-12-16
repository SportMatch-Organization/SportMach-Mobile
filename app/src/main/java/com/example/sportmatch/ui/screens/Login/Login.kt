package com.example.sportmatch.ui.screens.Login
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.unit.dp
import com.example.sportmatch.data.database.SportMatchDatabase
import com.example.sportmatch.ui.login.componentes.LoginHeader
import com.example.sportmatch.ui.login.componentes.LoginInputs
import com.example.sportmatch.ui.login.componentes.LoginButtons
import com.example.sportmatch.ui.viewModel.LoginViewModel
import com.example.sportmatch.ui.viewModel.LoginViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    onLoginSuccess: () -> Unit,
    onNavigateToCadastro: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val context = LocalContext.current
    val db = remember { SportMatchDatabase.getDatabase(context) }
    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(db)
    )
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF97316)
                ),
                windowInsets = WindowInsets.statusBars
            )
        },
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            LoginHeader()
            LoginInputs(viewModel)
            LoginButtons(
                isLoading = viewModel.isLoading,
                onLoginClick = {
                    viewModel.login(
                        onSuccess = onLoginSuccess,
                        onError = { msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                        }
                    )
                },
                onNavigateToCadastro = onNavigateToCadastro
            )
        }
    }
}