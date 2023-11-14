package com.hopcape.newssaas.admin.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hopcape.newssaas.admin.style.Constants.FONT_FAMILY
import com.hopcape.newssaas.admin.style.PrimaryColor
import com.hopcape.newssaas.admin.style.PrimaryContainerColor
import com.hopcape.newssaas.admin.style.noBorder
import com.hopcape.newssaas.admin.utils.Dimensions
import com.hopcape.newssaas.admin.utils.Dimensions.TOP_BAR_HEIGHT
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.animation
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.translateX
import com.varabyte.kobweb.compose.ui.modifiers.translateY
import com.varabyte.kobweb.compose.ui.modifiers.visibility
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.animation.Keyframes
import com.varabyte.kobweb.silk.components.animation.toAnimation
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.ms
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.SearchInput

@Composable
fun AppBar(
    onStateChanged: () -> Unit = {},
) {
    var showSearchField by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .backgroundColor(PrimaryColor.rgb)
            .fillMaxWidth()
            .position(Position.Fixed)
            .zIndex(9)) {
        Row(
            modifier = Modifier
                .backgroundColor(PrimaryColor.rgb)
                .fillMaxWidth()
                .position(Position.Fixed)
                .zIndex(9)
                .height(TOP_BAR_HEIGHT.px)
                .padding(16.px),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .onClick {
                        if (showSearchField) {
                            showSearchField = false
                            return@onClick
                        }
                        // Show all the list of Menu options
                        expanded = !expanded
                    }
                    .color(Colors.White)
                    .size(24.px)
                    .margin(12.px)
                    .styleModifier {
                        property("stroke", Colors.White)
                        property("fill", Colors.White)
                    },
                path = if (showSearchField) Resource.Icons.CrossIcon else Resource.Icons.HamburgerMenu
            )
            SearchInput(
                attrs = Modifier
                    .fillMaxWidth()
                    .height(55.px)
                    .noBorder()
                    .borderRadius(8.px)
                    .backgroundColor(PrimaryContainerColor.rgb)
                    .visibility(if (showSearchField) Visibility.Visible else Visibility.Hidden)
                    .toAttrs(),
                value = ""
            )
            Icon(
                modifier = Modifier
                    .color(Colors.White)
                    .onClick {
                        showSearchField = !showSearchField
                    }
                    .size(24.px)
                    .margin(12.px)
                    .styleModifier {
                        property("stroke", Colors.White)
                        property("fill", Colors.White)
                    },
                path = Resource.Icons.SearchIcon
            )
        }
        if (expanded) {
            NavigationItemContainer()
        }
    }
}

@Composable
fun NavigationItemContainer(
    modifier: Modifier = Modifier
) {
    val ShiftRight by Keyframes {
        from { Modifier.left(0.px) }
        to { Modifier.left(200.px) }
    }

    Column(
        modifier = modifier
            .animation(ShiftRight.toAnimation(4000.ms))
    ) {
        MobileNavigationMenuItem(
            modifier = Modifier
                .margin(top = TOP_BAR_HEIGHT.px),
            label = "All Users",
            icon = Resource.Icons.UsersIcon
        )

        MobileNavigationMenuItem(
            label = "Delete User",
            icon = Resource.Icons.DeleteUserIcon
        )


        MobileNavigationMenuItem(
            label = "Find User",
            icon = Resource.Icons.SearchIcon
        )

        MobileNavigationMenuItem(
            label = "View Articles",
            icon = Resource.Icons.AllArticles
        )

        MobileNavigationMenuItem(
            label = "Add Article",
            icon = Resource.Icons.AddArticle
        )

        MobileNavigationMenuItem(
            label = "Remove Article",
            icon = Resource.Icons.DeleteArticle
        )

        MobileNavigationMenuItem(
            label = "Update Article",
            icon = Resource.Icons.UpdateArticle
        )

        MobileNavigationMenuItem(
            label = "Sign out",
            icon = Resource.Icons.LogOut
        )
    }
}
@Composable
fun MobileNavigationMenuItem(
    modifier: Modifier = Modifier,
    icon: String = "",
    label: String = "",
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(leftRight = 16.px, topBottom = 8.px),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            modifier = Modifier
                .color(Colors.White)
                .margin(12.px)
                .styleModifier {
                    property("stroke", Colors.White)
                    property("fill", Colors.White)
                },
            path = icon,
            size = 24
        )
        SpanText(
            modifier = Modifier
                .fontFamily(FONT_FAMILY)
                .fontWeight(FontWeight.Normal)
                .fontSize(22.px)
                .color(Colors.White),
            text = label,
        )
    }
}


