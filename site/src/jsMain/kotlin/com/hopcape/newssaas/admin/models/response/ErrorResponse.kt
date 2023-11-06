package com.hopcape.newssaas.admin.models.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val message: String
)


