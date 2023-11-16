package com.hopcape.newssaas.admin.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hopcape.newssaas.admin.components.dialogs.LogoutDialog
import com.hopcape.newssaas.admin.components.layouts.AdminPageLayout
import com.hopcape.newssaas.admin.components.layouts.SidePanelView
import com.hopcape.newssaas.admin.components.widgets.SearchBar
import com.hopcape.newssaas.admin.components.widgets.cards.StatisticsOverviewCardView
import com.hopcape.newssaas.admin.style.BackgroundColor
import com.hopcape.newssaas.admin.utils.Dimensions
import com.hopcape.newssaas.admin.utils.Dimensions.NAVIGATION_RAIL_WIDTH
import com.hopcape.newssaas.admin.utils.Dimensions.PAGE_WIDTH
import com.hopcape.newssaas.admin.utils.Dimensions.SIDE_PANEL_WIDTH
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh


@Page
@Composable
fun HomePage() {
    val breakpoint = rememberBreakpoint()
    AdminPageLayout {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .maxWidth(PAGE_WIDTH.px)
                .padding(24.px),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                modifier = Modifier
                    .margin(bottom = 30.px)
                    .fillMaxWidth(breakpoint.searchFieldWidthPercent())
            )

            StatisticsOverviewCardView(
                modifier = Modifier
                    .fillMaxWidth(),
                onQuickActionClick = {}
            )
        }
    }
}


fun Breakpoint.searchFieldWidthPercent() =
    when {
        this <= Breakpoint.MD -> 50.percent
        else -> 60.percent
    }





