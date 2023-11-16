package com.hopcape.newssaas.admin.components.widgets.cards

import com.hopcape.newssaas.admin.style.Constants.FONT_FAMILY
import com.hopcape.newssaas.admin.style.PrimaryColor
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.scale
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.hover
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.px

val StatisticsOverviewCardStyle by ComponentStyle {
    base {
        Modifier
            .transition(CSSTransition(TransitionProperty.All, duration = 300.ms))
    }
    hover {
        Modifier
            .scale(1.11)
    }
}

val StatisticsOverviewTextStyle by ComponentStyle {
    base {
        Modifier
            .fillMaxWidth()
            .margin(left = 70.px)
            .color(Colors.DarkGray)
            .fontFamily(FONT_FAMILY)
            .fontSize(13.px)
    }
}

val StatisticsOverviewButtonStyle by ComponentStyle {
    cssRule(" > #${ButtonContainer} > #${Resource.Id.NavigationItem.SvgParent}"){
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .color(Colors.DarkGray)
            .styleModifier {
                property("stroke",Colors.DarkGray)
                property("fill",Colors.DarkGray)
            }

    }

    cssRule(":hover > #${ButtonContainer} > #${Resource.Id.NavigationItem.SvgParent}"){
        Modifier
            .styleModifier {
                property("stroke", PrimaryColor.rgb)
                property("fill", PrimaryColor.rgb)
            }

    }

    cssRule(" > #${ButtonContainer} > #${ButtonText}"){
        Modifier
            .fillMaxWidth()
            .textAlign(TextAlign.Start)
            .fontFamily(FONT_FAMILY)
            .fontSize(13.px)
            .margin(left = 8.px)
            .color(Colors.DarkGray)
            .cursor(Cursor.Pointer)
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))


    }

    cssRule(":hover > #${ButtonContainer} > #${ButtonText}"){
        Modifier
            .color(PrimaryColor.rgb)

    }
}

