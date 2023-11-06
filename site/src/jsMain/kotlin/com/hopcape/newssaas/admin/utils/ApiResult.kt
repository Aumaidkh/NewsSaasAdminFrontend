package com.hopcape.newssaas.admin.utils

import com.hopcape.newssaas.admin.models.response.SignInResponse
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject

@Serializable
sealed class ApiResult<out T>{
    @Serializable
    @SerialName("success")
    data class Success<out T>(val data: T): ApiResult<T>()

    @SerialName("error")
    @Serializable
    data class Error(val message: String): ApiResult<Nothing>()
}


@Serializable(SignInResultSerializer::class)
sealed class SignInResult{
    @Serializable
    data class Success(val data: SignInResponse): SignInResult()

    @Serializable
    data class Error(val message: String): SignInResult()
}

object SignInResultSerializer: JsonContentPolymorphicSerializer<SignInResult>(SignInResult::class){
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<SignInResult> = when {
        "data" in element.jsonObject -> SignInResult.Success.serializer()
        "message" in element.jsonObject-> SignInResult.Error.serializer()
        else -> SignInResult.Error.serializer()
    }
}




