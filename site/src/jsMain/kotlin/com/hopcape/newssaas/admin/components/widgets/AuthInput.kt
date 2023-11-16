package com.hopcape.newssaas.admin.components.widgets

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.style.BackgroundColor
import com.hopcape.newssaas.admin.style.Constants.FONT_FAMILY
import com.hopcape.newssaas.admin.style.InputFieldStyle
import com.hopcape.newssaas.admin.style.Mercury
import com.hopcape.newssaas.admin.style.Shapes
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.MaxWidth
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Input

enum class InputType(
    val icon: String? = null
) {
    EMAIL(),
    PASSWORD(),
    TEXT(),
    DATE(),
    IMAGE,
    URL
}

fun InputType.toInputType() =
    when(this){
        InputType.EMAIL -> org.jetbrains.compose.web.attributes.InputType.Email
        InputType.PASSWORD -> org.jetbrains.compose.web.attributes.InputType.Password
        InputType.TEXT -> org.jetbrains.compose.web.attributes.InputType.Text
        InputType.DATE -> org.jetbrains.compose.web.attributes.InputType.DateTimeLocal
        InputType.IMAGE -> org.jetbrains.compose.web.attributes.InputType.File
        InputType.URL -> org.jetbrains.compose.web.attributes.InputType.Url
    }

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    inputType: InputType = InputType.EMAIL,
    label: String = "Email",
    placeholder: String = "Email",
    id: String = Resource.Id.Input.EmailInput,
    maxWidth: Int? = null,
    error: String? = null
) {
    Column(
        modifier = modifier
            .thenIf(
                condition = maxWidth == null,
                other = Modifier
                    .fillMaxWidth()
            )
            .thenIf(
                condition = maxWidth != null,
                other = Modifier
                    .width(maxWidth?.px ?: 350.px)
            )
    ) {
        SpanText(
            modifier = Modifier
                .fontFamily(FONT_FAMILY)
                .fontWeight(FontWeight.Normal)
                .color(Colors.Black)
                .fontSize(14.px)
                .margin(bottom = 12.px),
            text = label
        )
        Input(
            type = inputType.toInputType(),
            attrs = InputFieldStyle
                .toModifier()
                .fillMaxWidth()
                .id(id)
                .height(45.px)
                .padding(leftRight = 24.px, topBottom = 12.px)
                .toAttrs{
                    attr("placeholder",placeholder)
                }
        )
        if (error != null){
            SpanText(
                modifier = Modifier
                    .fontFamily(FONT_FAMILY)
                    .fontWeight(FontWeight.Normal)
                    .color(Colors.Red)
                    .fontSize(12.px)
                    .margin(bottom = 12.px),
                text = error
            )
        }
    }
}