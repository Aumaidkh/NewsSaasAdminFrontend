package com.hopcape.newssaas.admin.utils

import com.hopcape.newssaas.admin.models.request.SignInRequest
import com.hopcape.newssaas.admin.models.response.ApiResponse
import com.hopcape.newssaas.admin.models.response.ErrorResponse
import com.hopcape.newssaas.admin.models.response.SignInResponse
import com.hopcape.newssaas.admin.utils.Resource.Routes.SIGN_IN_ROUTE
import com.varabyte.kobweb.compose.http.ResponseException
import com.varabyte.kobweb.compose.http.http
import kotlinx.browser.window
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

suspend fun loginUser(
    email: String,
    password: String
):SignInResult {
    return try {
        val jsonResponse = window.http.post(
            headers = hashMapOf(
                "Content-Type" to "application/json"
            ),
            resource = SIGN_IN_ROUTE,
            body = Json.encodeToString(SignInRequest(email, password)).encodeToByteArray()
        ).decodeToString()
        jsonResponse.let {
            Json.decodeFromString(jsonResponse)
        }
    }
    catch (e: ResponseException){
        e.printStackTrace()
        SignInResult.Error(e.evaluateMessage())
    }
    catch (e: Exception){
        e.printStackTrace()
        SignInResult.Error(e.evaluateMessage())
    }
}