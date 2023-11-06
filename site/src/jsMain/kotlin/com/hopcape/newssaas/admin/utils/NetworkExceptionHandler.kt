package com.hopcape.newssaas.admin.utils

import com.hopcape.newssaas.admin.models.response.ErrorResponse
import com.varabyte.kobweb.compose.http.ResponseException
import kotlinx.serialization.json.Json

fun Exception.evaluateMessage(): String{
    return when(this){
        is ResponseException -> {
            val error = "${this.bodyBytes?.decodeToString()?.split("}")?.first()}}"
            val message = Json.decodeFromString<ErrorResponse>(error)
            message.message
        }
        else -> this.message.toString()
    }
}