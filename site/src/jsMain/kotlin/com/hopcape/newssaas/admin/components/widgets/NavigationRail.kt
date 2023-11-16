package com.hopcape.newssaas.admin.components.widgets

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.style.Constants.FONT_FAMILY
import com.hopcape.newssaas.admin.style.NavigationItemStyle
import com.hopcape.newssaas.admin.style.Teritiary
import com.hopcape.newssaas.admin.utils.Dimensions.NAVIGATION_RAIL_WIDTH
import com.hopcape.newssaas.admin.utils.Resource
import com.hopcape.newssaas.admin.utils.Resource.Id.NavigationItem.NavigationRailItemContainer
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

/**
 * Shows all the navigation options
 * without news categories*/
@Composable
fun NavigationRail(
    onLogout: () -> Unit,
    onLogoClick: () -> Unit,
    onAddArticleClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .height(100.vh)
            .position(Position.Fixed)
            .boxShadow(offsetX = 5.px, blurRadius = 20.px, color = Colors.DarkGray)
            .backgroundColor(Colors.White)
            .zIndex(9)
            .width(NAVIGATION_RAIL_WIDTH.px)
            .padding(8.px),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .onClick { onLogoClick() }
                .backgroundColor(Teritiary.rgb)
                .borderRadius(4.px)
                .padding(leftRight = 27.px, topBottom = 17.px)
                .margin(bottom = 50.px),
            contentAlignment = Alignment.Center
        ) {
            SpanText(
                modifier = Modifier
                    .color(Colors.White)
                    .fontFamily(FONT_FAMILY)
                    .fontWeight(FontWeight.Medium)
                    .fontSize(20.px),
                text = "A",
            )
        }

        Column(
            modifier = NavigationItemStyle
                .toModifier()
                .overflow(Overflow.Scroll)
                .scrollBehavior(ScrollBehavior.Smooth)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // User Options
            NavigationRailItem(
                icon = Resource.Icons.UsersIcon,
            )

            NavigationRailItem(
                icon = Resource.Icons.DeleteUserIcon,
            )


            NavigationRailItem(
                icon = Resource.Icons.SearchIcon,
            )

            NavigationRailItem(
                icon = Resource.Icons.AllArticles,
            )

            NavigationRailItem(
                modifier = Modifier
                    .onClick { onAddArticleClick() },
                icon = Resource.Icons.AddArticle,
            )

            NavigationRailItem(
                icon = Resource.Icons.DeleteArticle,
            )

            NavigationRailItem(
                icon = Resource.Icons.UpdateArticle,
            )

            NavigationRailItem(
                modifier = Modifier.onClick { onLogout() },
                icon = Resource.Icons.LogOut,
            )
        }
    }
}

/**
 * Individual Navigation Item */
@Composable
fun NavigationRailItem(
    modifier: Modifier = Modifier,
    icon: String = Resource.Icons.SearchIcon
) {
    Box(
        modifier = NavigationItemStyle
            .toModifier()
            .then(modifier)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .id(NavigationRailItemContainer)
                .borderRadius(4.px)
                .padding(leftRight = 24.px, topBottom = 24.px)
                .margin(bottom = 12.px),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                size = 20,
                path = icon
            )
        }
    }
}

