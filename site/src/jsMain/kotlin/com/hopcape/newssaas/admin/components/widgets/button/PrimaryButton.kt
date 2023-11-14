package com.hopcape.newssaas.admin.components.widgets.button

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.style.Constants
import com.hopcape.newssaas.admin.style.PrimaryColor
import com.hopcape.newssaas.admin.style.noBorder
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.borderWidth
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    height: Int = 55,
    onClick: () -> Unit
) {
    Button(
        attrs = modifier
            .backgroundColor(PrimaryColor.rgb)
            .noBorder()
            .onClick { onClick() }
            .borderRadius(8.px)
            .height(height.px)
            .cursor(Cursor.Pointer)
            .padding(leftRight = 30.px, topBottom = 5.px)
            .toAttrs()
    ){
        SpanText(
            modifier = Modifier
                .fontFamily(Constants.FONT_FAMILY)
                .fontWeight(FontWeight.Normal)
                .color(Colors.White)
                .fontSize(14.px)
                .margin(bottom = 12.px),
            text = text
        )
    }
}

@Composable
fun OutlinedPrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    height: Int = 55,
    onClick: () -> Unit
) {
    Button(
        attrs = modifier
            .backgroundColor(Colors.White)
            .onClick { onClick() }
            .borderRadius(8.px)
            .border(
                width = 1.px,
                style = LineStyle.Solid,
                color = PrimaryColor.rgb
            )
            .height(height.px)
            .cursor(Cursor.Pointer)
            .padding(leftRight = 30.px, topBottom = 5.px)
            .toAttrs()
    ){
        SpanText(
            modifier = Modifier
                .fontFamily(Constants.FONT_FAMILY)
                .fontWeight(FontWeight.Normal)
                .color(PrimaryColor.rgb)
                .fontSize(14.px)
                .margin(bottom = 12.px),
            text = text
        )
    }
}