package com.hopcape.newssaas.admin.components.widgets

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.style.BackgroundColor
import com.hopcape.newssaas.admin.style.Constants.FONT_FAMILY
import com.hopcape.newssaas.admin.style.InputFieldStyle
import com.hopcape.newssaas.admin.style.Mercury
import com.hopcape.newssaas.admin.style.Shapes
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.FontWeight
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
    PASSWORD()
}

fun InputType.toInputType() =
    when(this){
        InputType.EMAIL -> org.jetbrains.compose.web.attributes.InputType.Email
        InputType.PASSWORD -> org.jetbrains.compose.web.attributes.InputType.Password
    }

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    inputType: InputType = InputType.EMAIL,
    label: String = "Email",
    placeholder: String = "Email",
    id: String = Resource.Id.Input.EmailInput
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        SpanText(
            modifier = Modifier
                .fontFamily(FONT_FAMILY)
                .fontWeight(FontWeight.Normal)
                .color(Colors.Black)
                .margin(bottom = 12.px),
            text = label
        )
        Input(
            type = inputType.toInputType(),
            attrs = InputFieldStyle
                .toModifier()
                .id(id)
                .height(50.px)
                .width(350.px)
                .padding(leftRight = 24.px, topBottom = 12.px)
                .borderRadius(Shapes.Small.cornerRadius.px)
                .backgroundColor(Colors.White)
                .color(Colors.Black)
                .fontFamily(FONT_FAMILY)
                .fontWeight(FontWeight.Normal)
                .fontSize(16.px)
                .toAttrs{
                    attr("placeholder",placeholder)
                }
        )
    }
}