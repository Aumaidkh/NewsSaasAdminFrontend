package com.hopcape.newssaas.admin.style

import com.hopcape.newssaas.admin.utils.Resource.Id.CategoryDialog.CategoryContainer
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.hover
import org.jetbrains.compose.web.css.ms

val CategoryDialogStyle by ComponentStyle {
    base {
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .backgroundColor(Colors.White)
            .color(Colors.Black)
    }

    hover {
        Modifier
            .backgroundColor(PrimaryContainerColor.rgb)
            .color(PrimaryColor.rgb)
    }
}