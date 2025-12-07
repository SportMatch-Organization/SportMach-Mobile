package com.example.sportmatch.ui.competicoes

//import com.example.sportmatch.ui.components.CustomDateTimePicker
/*
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CadastroCompeticao3(viewModel: CampeonatoViewModel = viewModel(), onBefore: () -> Unit) {
    val scrollState = rememberScrollState()
    var carregando by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        IconButton(onClick = { onBefore() }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = Color.Red,
                contentDescription = "Voltar"
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            CustomText("Revise os dados", TextType.HEADLINE, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(60.dp))
            ReviseSeusDados(viewModel)
            Spacer(modifier = Modifier.height(34.dp))
            CustomButton(
                text = "Cadastrar",
                onClick = {
                    carregando = true
                    scope.launch {
                        try {
                            viewModel.salvarCompeticao()
                            carregando = false
                            Toast.makeText(context, "Competição cadastrada com sucesso!", Toast.LENGTH_LONG).show()
                        } catch (error: Exception) {
                            carregando = false
                            Toast.makeText(context, "Falha no Cadastro: ${error.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                },
                enabled = !carregando
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                text = "Voltar",
                onClick = { onBefore() },
                modifier = Modifier
                    .fillMaxWidth().height(50.dp)
                    .border(2.dp, StrokeBt, shape = RoundedCornerShape(4.dp)),
                backgroundColor = Color.White,
                textColor = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.height(40.dp))

        }
    }
}*/