package com.hopcape.newssaas.admin.style

import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.hover
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

val EditorIconStyle by ComponentStyle {
    base {
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All,500.ms))
            .padding(8.px)
            .styleModifier {
                property("stroke",Colors.Black.copy(alpha = 180))
                property("fill",Colors.Black.copy(alpha = 180))
            }
    }
    hover {
        Modifier
            .styleModifier {
                property("stroke", PrimaryColor.rgb)
                property("fill",PrimaryColor.rgb)
            }
    }
}

val EditorItemStyle by ComponentStyle {
    base {
        Modifier
            .backgroundColor(Colors.White)
            .transition(CSSTransition(property = TransitionProperty.All,500.ms))
    }
    hover {
        Modifier
            .backgroundColor(PrimaryContainerColor.rgb)
    }
}