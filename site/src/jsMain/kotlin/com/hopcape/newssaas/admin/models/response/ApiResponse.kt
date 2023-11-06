package com.hopcape.newssaas.admin.models.response

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<out T>(
    val data: T? = null,
    val message: String? = null
)
