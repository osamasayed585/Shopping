package com.example.shopping.model.data

import com.hrhera.login.model.data.LoginResponse

data class User(
    val loginResponse: LoginResponse,
    val message: String,
    val status: Boolean
        )