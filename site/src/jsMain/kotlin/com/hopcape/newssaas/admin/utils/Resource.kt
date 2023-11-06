package com.hopcape.newssaas.admin.utils

object Resource {
    object Images{
        const val logo = "/kobweb-logo.png"
    }

    object Id {
        object Input{
            const val EmailInput = "emailInput"
            const val PasswordInput = "passwordInput"
        }

        object Button {
            const val LoginButton = "loginButton"
        }

        object CheckBox {
            const val RememberMe = "rememberMe"
        }
    }

    object Icons {
        const val CrossIcon = "M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z"
        const val WarningIcon = "M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zm0-384c13.3 0 24 10.7 24 24V264c0 13.3-10.7 24-24 24s-24-10.7-24-24V152c0-13.3 10.7-24 24-24zM224 352a32 32 0 1 1 64 0 32 32 0 1 1 -64 0z"
    }

    object Routes {
        private val BASE_URL = "http://localhost:8080"
        val SIGN_IN_ROUTE = "$BASE_URL/user/signin"
    }
}