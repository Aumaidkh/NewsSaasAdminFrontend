package com.hopcape.newssaas.admin.models.response

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val token: String
)

