package com.hopcape.newssaas.admin.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hopcape.newssaas.admin.style.NavigationItemStyle
import com.hopcape.newssaas.admin.style.SidebarColor
import com.hopcape.newssaas.admin.utils.Dimensions
import com.hopcape.newssaas.admin.utils.Resource
import com.hopcape.newssaas.admin.utils.Resource.Labels.categoriesLabel
import com.hopcape.newssaas.admin.utils.Screen
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.style.toModifier
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Composable
fun SidePanel(
    modifier: Modifier = Modifier,
    onLogoClick: () -> Unit,
    onAddArticleClick: () -> Unit,
    onLogout: () -> Unit = {}
) {
    val context = rememberPageContext()
    var categoriesExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .boxShadow(offsetX = 5.px, blurRadius = 20.px, color = Colors.DarkGray)
            .backgroundColor(Colors.Black.copy(alpha = 210))
            .width(Dimensions.SIDE_PANEL_WIDTH.px)
            .fillMaxHeight()
            .height(100.percent)
            .zIndex(9)
            .padding(topBottom = 48.px),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Image
        Image(
            modifier = Modifier
                .onClick { context.router.navigateTo(Screen.Home.route) }
                .width(150.px)
                .margin(bottom = 50.px),
            src = Resource.Images.logo
        )
        Column(
            modifier = NavigationItemStyle
                .toModifier()
                .fillMaxHeight()
                .fillMaxWidth()
                .scrollBehavior(ScrollBehavior.Smooth)
                .overflow(Overflow.Scroll),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Users Heading
            NavBarHeading(
                text = "Users",
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px)
            )

            // User Options
            NavigationItem(
                label = "All Users",
                icon = Resource.Icons.UsersIcon
            )

            NavigationItem(
                label = "Delete User",
                icon = Resource.Icons.DeleteUserIcon
            )


            NavigationItem(
                label = "Find User",
                icon = Resource.Icons.SearchIcon
            )

            // Articles Heading
            NavBarHeading(
                text = "Articles",
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(topBottom = 25.px)
            )

            NavigationItem(
                label = "View Articles",
                icon = Resource.Icons.AllArticles
            )

            NavigationItem(
                modifier = Modifier
                    .onClick { onAddArticleClick() },
                label = "Add Article",
                icon = Resource.Icons.AddArticle
            )

            NavigationItem(
                label = "Remove Article",
                icon = Resource.Icons.DeleteArticle
            )

            NavigationItem(
                label = "Update Article",
                icon = Resource.Icons.UpdateArticle
            )


            NavigationItem(
                modifier = Modifier.onClick { onLogout() },
                label = "Sign out",
                icon = Resource.Icons.LogOut
            )
        }
    }
}


val defaultCategories = listOf(
    "Breaking News",
    "Regional",
    "Sports",
    "Technology",
    "Weather",
    "Traffic",
    "International"
)