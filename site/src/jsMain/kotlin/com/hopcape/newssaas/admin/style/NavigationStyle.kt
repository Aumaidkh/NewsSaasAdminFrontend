package com.hopcape.newssaas.admin.style

import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import org.jetbrains.compose.web.css.ms

val NavigationItemStyle by ComponentStyle {
    cssRule(" > #${Resource.Id.NavigationItem.NavigationItemContainer} > #${Resource.Id.NavigationItem.NavigationItemText}"){
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .color(Colors.Black.copy(alpha = 98))
    }

    cssRule(":hover > #${Resource.Id.NavigationItem.NavigationItemContainer} > #${Resource.Id.NavigationItem.NavigationItemText}"){
        Modifier
            .color(PrimaryColor.rgb)
    }

    cssRule(" > #${Resource.Id.NavigationItem.NavigationItemContainer} > #${Resource.Id.NavigationItem.SvgParent} > #${Resource.Id.NavigationItem.NavigationItemIcon}"){
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .styleModifier {
                property("stroke",Colors.Black.copy(alpha = 98))
                property("fill",Colors.Black.copy(alpha = 98))
            }
    }

    cssRule(":hover > #${Resource.Id.NavigationItem.NavigationItemContainer} > #${Resource.Id.NavigationItem.SvgParent} > #${Resource.Id.NavigationItem.NavigationItemIcon}"){
        Modifier
            .styleModifier {
                property("stroke", PrimaryColor.rgb)
                property("fill", PrimaryColor.rgb)
            }
    }

    cssRule(" > #${Resource.Id.NavigationItem.NavigationRailItemContainer} > #${Resource.Id.NavigationItem.SvgParent} > #${Resource.Id.NavigationItem.NavigationItemIcon}"){
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .styleModifier {
                property("stroke",Colors.Black.copy(alpha = 98))
                property("fill",Colors.Black.copy(alpha = 98))
            }
    }

    cssRule(":hover > #${Resource.Id.NavigationItem.NavigationRailItemContainer} > #${Resource.Id.NavigationItem.SvgParent} > #${Resource.Id.NavigationItem.NavigationItemIcon}"){
        Modifier
            .styleModifier {
                property("stroke",Colors.White)
                property("fill", Colors.White)
            }
    }

    cssRule(" > #${Resource.Id.NavigationItem.NavigationItemContainer}"){
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .backgroundColor(Colors.Transparent)

    }

    cssRule(":hover > #${Resource.Id.NavigationItem.NavigationItemContainer}"){
        Modifier
            .backgroundColor(PrimaryContainerColor.rgb)
    }

    cssRule("::-webkit-scrollbar"){
        Modifier
            .styleModifier {
                property("display","none")
            }
    }

    cssRule(" > #${Resource.Id.NavigationItem.NavigationRailItemContainer}"){
        Modifier
            .transition(CSSTransition(property = TransitionProperty.All, duration = 300.ms))
            .backgroundColor(Colors.Transparent)

    }

    cssRule(":hover > #${Resource.Id.NavigationItem.NavigationRailItemContainer}"){
        Modifier
            .backgroundColor(PrimaryColor.rgb)
    }

}