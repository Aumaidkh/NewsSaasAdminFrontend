package com.hopcape.newssaas.admin.components.dialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.hopcape.newssaas.admin.components.widgets.InputField
import com.hopcape.newssaas.admin.components.widgets.InputType
import com.hopcape.newssaas.admin.components.widgets.button.PrimaryButton
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.margin
import kotlinx.browser.document
import org.jetbrains.compose.web.css.px
import org.w3c.dom.HTMLInputElement

const val LinkTitleId = "linkTitle"
const val LinkUrlId = "linkUrl"

@Composable
fun LinkPickerDialog(
    label1: String = "Title *",
    placeHolder1: String = "Enter title here",
    label2: String = "Url *",
    placeHolder2: String = "https://",
    buttonText: String = "Add Link",
    onDismiss: () -> Unit,
    onSubmit: (title: String,url: String) -> Unit
) {
    var errorState = rememberErrorState()
    Dialog(
        title = "Add Link",
        onDismiss = onDismiss,
        contentPadding = 32,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {

                InputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .margin(bottom = 14.px),
                    inputType = InputType.TEXT,
                    label = label1,
                    placeholder = placeHolder1,
                    id = LinkTitleId,
                    error = errorState.value.field1Error
                )

                InputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .margin(bottom = 32.px),
                    inputType = InputType.URL,
                    label = label2,
                    placeholder = placeHolder2,
                    id = LinkUrlId,
                    error = errorState.value.field2Error
                )

                PrimaryButton(
                    text = buttonText,
                    height = 50,
                    onClick = {
                        // Reset Error State, we don't want the errors messages
                        // to be there even when the error are fixed
                        errorState.value = LinkPickerErrorState()
                        val title = (document.getElementById(LinkTitleId) as HTMLInputElement).value
                        val url = (document.getElementById(LinkUrlId) as HTMLInputElement).value
                        if (title.isEmpty()) {
                            errorState.value = errorState.value.copy(
                                field1Error = "$label1 can't be empty"
                            )
                            return@PrimaryButton
                        }

                        if (url.isEmpty()) {
                            errorState.value = errorState.value.copy(
                                field2Error = "$label2 can't be empty"
                            )
                            return@PrimaryButton
                        }

                        if (url.contains(" ")){
                            errorState.value = errorState.value.copy(
                                field2Error = "Invalid $label2"
                            )
                            return@PrimaryButton
                        }

                        val validatedUrl = parseUrl(url)
                        onDismiss()
                        onSubmit(
                            title,
                            validatedUrl
                        )
                    }
                )
            }
        }
    )
}

/**
 * Wrapper around link picker
 * error state
 * @property field1Error - error message on field one
 * @property field2Error - error message on field two*/
data class LinkPickerErrorState(
    val field1Error: String? = null,
    val field2Error: String? = null
)


@Composable
fun rememberErrorState() =
    remember {
        mutableStateOf(LinkPickerErrorState())
    }


private fun parseUrl(url: String): String {
    if (!url.startsWith("https://")) {
        return "https://$url"
    }
    return url
}

