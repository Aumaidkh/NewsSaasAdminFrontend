package com.hopcape.newssaas.admin.style

import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.outline
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.focus
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

val InputFieldStyle by ComponentStyle {
    base {
        Modifier.border(
            width = 0.2.px,
            style = LineStyle.Solid,
            color = Mercury.rgb
        )
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