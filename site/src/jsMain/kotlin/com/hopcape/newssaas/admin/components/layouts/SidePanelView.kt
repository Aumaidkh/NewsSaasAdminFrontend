package com.hopcape.newssaas.admin.components.layouts

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.components.widgets.NavigationRail
import com.hopcape.newssaas.admin.components.widgets.SidePanel
import com.hopcape.newssaas.admin.components.widgets.AppBar
import com.hopcape.newssaas.admin.style.BackgroundColor
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.vh

@Composable
fun SidePanelView(
    breakpoint: Breakpoint,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .overflow(Overflow.Hidden)
            .backgroundColor(BackgroundColor.rgb),
    ) {
        when {
            breakpoint <= Breakpoint.SM -> HamburgerMenuView(content)
            breakpoint <= Breakpoint.MD -> NavigationRailView(content)
            breakpoint > Breakpoint.MD -> SideBarView(content)
        }
    }
}

@Composable
private fun HamburgerMenuView(
    content: @Composable () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(100.vh)
    ) {
        AppBar()
        content()
    }
}

@Composable
private fun NavigationRailView(
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(100.vh)
    ) {
        NavigationRail()
        content()
    }
}

@Composable
private fun SideBarView(
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(100.vh)
    ) {
        SidePanel()
        content()
    }
}