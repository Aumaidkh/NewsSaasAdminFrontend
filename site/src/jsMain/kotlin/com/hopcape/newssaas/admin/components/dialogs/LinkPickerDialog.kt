package com.hopcape.newssaas.admin.components.dialogs

import androidx.compose.runtime.Composable
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
                    id = LinkTitleId
                )

                InputField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .margin(bottom = 32.px),
                    inputType = InputType.URL,
                    label = label2,
                    placeholder = placeHolder2,
                    id = LinkUrlId
                )

                PrimaryButton(
                    text = buttonText,
                    height = 50,
                    onClick = {
                        val title = (document.getElementById(LinkTitleId) as HTMLInputElement).value
                        val url = (document.getElementById(LinkUrlId) as HTMLInputElement).value
                        val validatedUrl = parseUrl(url)
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

private fun parseUrl(url: String): String {
    if (!url.startsWith("https://")){
        return "https://$url"
    }
    if (!url.startsWith("https://www.")){
        return "https://www.$url"
    }
    return url
}

