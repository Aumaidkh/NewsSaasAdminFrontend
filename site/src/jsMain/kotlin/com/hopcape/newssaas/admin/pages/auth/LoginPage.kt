package com.hopcape.newssaas.admin.pages.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.hopcape.newssaas.admin.components.widgets.InputField
import com.hopcape.newssaas.admin.components.widgets.InputType
import com.hopcape.newssaas.admin.components.widgets.Message
import com.hopcape.newssaas.admin.components.widgets.MessageBar
import com.hopcape.newssaas.admin.style.BackgroundColor
import com.hopcape.newssaas.admin.style.Constants.FONT_FAMILY
import com.hopcape.newssaas.admin.style.Mercury
import com.hopcape.newssaas.admin.style.PrimaryColor
import com.hopcape.newssaas.admin.style.Shapes
import com.hopcape.newssaas.admin.style.noBorder
import com.hopcape.newssaas.admin.utils.Resource
import com.hopcape.newssaas.admin.utils.SignInResult
import com.hopcape.newssaas.admin.utils.loginUser
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.CheckboxInput
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.set

/**
 * Saves the token into local storage*/
private fun saveToken(token: String){
    localStorage["token"] = token
}

@Page("login")
@Composable
fun LoginPage() {
    val context = rememberPageContext()
    var loading by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var message by remember { mutableStateOf<Message?>(null) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .backgroundColor(BackgroundColor.rgb),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(
                    leftRight = 50.px, top = 80.px, bottom = 54.px
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo
            Image(
                modifier = Modifier
                    .width(200.px)
                    .margin(bottom = 24.px),
                src = Resource.Images.logo,
                description = "Logo"
            )
            LoginForm(
                onLogin = {
                    scope.launch {
                        loading = true
                        val email =
                            (document.getElementById(Resource.Id.Input.EmailInput) as HTMLInputElement).value
                        val password =
                            (document.getElementById(Resource.Id.Input.PasswordInput) as HTMLInputElement).value

                        if (email.isEmpty()) {
                            loading = false
                            message = Message.Error("Email can't be empty")
                            return@launch
                        }


                        if (password.isEmpty()) {
                            loading = false
                            message = Message.Error("Password can't be empty")
                            return@launch
                        }

                        scope.launch {
                            loginUser(
                                email = email,
                                password = password
                            ).also { result ->
                                when (result) {
                                    is SignInResult.Error -> {
                                        println("Error: ${result.message}")
                                        message = Message.Error(result.message)
                                        loading = false
                                    }
                                    is SignInResult.Success -> {
                                        saveToken(result.data.token)
                                        context.router.navigateTo(
                                            pathQueryAndFragment = "/"
                                        )
                                    }
                                }
                            }
                        }

                    }

                },
                loading = loading,
                rememberMe = rememberMe,
                onCheckedChange = {
                    rememberMe = it
                }
            )
        }
    }
    if (message != null) {
        MessageBar(
            message = message!!,
            onDismiss = {
                message = null
            }
        )
    }
}

@Composable
fun LoginForm(
    onLogin: () -> Unit = {},
    loading: Boolean = false,
    rememberMe: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.px)
            .backgroundColor(Colors.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Heading
        SpanText(
            modifier = Modifier
                .color(Colors.Black)
                .fontFamily(FONT_FAMILY)
                .fontSize(25.px)
                .fontWeight(FontWeight.Medium),
            text = "Login to Your Account"
        )
        // Subheading
        SpanText(
            modifier = Modifier
                .color(Colors.Black)
                .fontFamily(FONT_FAMILY)
                .fontSize(15.px)
                .fontWeight(FontWeight.Normal)
                .margin(bottom = 24.px),
            text = "Enter your username & password to login"
        )

        InputField(
            modifier = Modifier
                .margin(bottom = 12.px),
            label = "Email",
            inputType = InputType.EMAIL,
            placeholder = "Email",
            id = Resource.Id.Input.EmailInput,
            maxWidth = 350
        )
        InputField(modifier = Modifier
            .margin(bottom = 24.px),
            label = "Password",
            inputType = InputType.PASSWORD,
            placeholder = "**********",
            id = Resource.Id.Input.PasswordInput,
            maxWidth = 350
        )
        RememberMe(
            modifier = Modifier
                .fillMaxWidth()
                .margin(bottom = 24.px),
            rememberMe = rememberMe,
            onCheckedChange = onCheckedChange
        )
        Button(
            attrs = Modifier
                .id(Resource.Id.Button.LoginButton)
                .width(350.px)
                .height(50.px)
                .noBorder()
                .borderRadius(Shapes.Small.cornerRadius.px)
                .backgroundColor(
                    if (loading) Mercury.rgb else PrimaryColor.rgb
                )
                .onClick {
                    onLogin()
                }
                .cursor(Cursor.Pointer)
                .toAttrs()
        ) {
            SpanText(
                modifier = Modifier
                    .color(Colors.White)
                    .fontFamily(FONT_FAMILY)
                    .fontSize(15.px)
                    .fontWeight(FontWeight.Normal)
                    .margin(bottom = 24.px),
                text = if (loading) "Logging in..." else "Login"
            )
        }
    }
}

@Composable
fun RememberMe(
    modifier: Modifier = Modifier,
    rememberMe: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CheckboxInput(
            attrs = Modifier
                .id(Resource.Id.CheckBox.RememberMe)
                .margin(right = 10.px)
                .size(24.px)
                .borderRadius(Shapes.Medium.cornerRadius.px)
                .onClick {
                    onCheckedChange(!rememberMe)
                }
                .toAttrs {
                    this.checked(rememberMe)
                },
            checked = rememberMe
        )
        SpanText(
            modifier = Modifier
                .color(Colors.Black)
                .fontFamily(FONT_FAMILY)
                .fontSize(15.px)
                .fontWeight(FontWeight.Normal),
            text = "Remember Me",
        )
    }
}