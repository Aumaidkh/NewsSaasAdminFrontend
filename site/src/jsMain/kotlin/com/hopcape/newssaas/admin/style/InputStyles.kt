package com.hopcape.newssaas.admin.style

import com.hopcape.newssaas.admin.style.Constants.FONT_FAMILY
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.focus
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

val InputFieldStyle by ComponentStyle {
    base {
        Modifier
            .border(
                width = 0.2.px,
                style = LineStyle.Solid,
                color = Mercury.rgb
            )
            .fontFamily(FONT_FAMILY)
            .fontWeight(FontWeight.Normal)
            .fontSize(14.px)
            .borderRadius(Shapes.Small.cornerRadius.px)
            .backgroundColor(Colors.White)
            .color(Colors.Black)
            .outline(
                width = 0.2.px,
                style = LineStyle.Solid,
                color = Mercury.rgb
            )
            .transition(
                CSSTransition(
                    property = "border", duration = 300.ms
                )
            )
    }
    focus {
        Modifier.border(
            width = 1.px,
            style = LineStyle.Solid,
            color = PrimaryColor.rgb
        )
            .outline(
                width = 1.px,
                style = LineStyle.Solid,
                color = PrimaryColor.rgb
            )
    }
}