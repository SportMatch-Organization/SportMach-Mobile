package com.example.sportmatch.ui.state

import com.example.sportmatch.data.database.entities.user.Endereco

data class UserAddressState(
    val endereco: Endereco = Endereco()
)