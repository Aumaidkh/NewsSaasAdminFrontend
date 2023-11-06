package com.hopcape.newssaas.admin.pages.auth

data class LoginState(
    val loading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val error: String? = null
)
