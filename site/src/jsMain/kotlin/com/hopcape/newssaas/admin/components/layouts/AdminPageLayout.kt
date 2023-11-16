package com.hopcape.newssaas.admin.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hopcape.newssaas.admin.components.dialogs.LogoutDialog
import com.hopcape.newssaas.admin.style.BackgroundColor
import com.hopcape.newssaas.admin.utils.Screen
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.vh

@Composable
fun AdminPageLayout(
    content: @Composable () -> Unit
) {
    val breakpoint = rememberBreakpoint()
    var showingLogoutDialog by remember { mutableStateOf(false) }
    val context = rememberPageContext()

    SidePanelView(
        breakpoint = breakpoint,
        onLogout = {
            showingLogoutDialog = !showingLogoutDialog
        },
        onLogoClick = {
            context.router.navigateTo(Screen.Home.route)
        },
        onAddArticleClick = {
            context.router.navigateTo(Screen.Create.route)
        },
        content = {
            Box(
                modifier = Modifier
                    .height(100.vh)
                    .fillMaxWidth()
                    .backgroundColor(BackgroundColor.rgb),
                contentAlignment = Alignment.TopCenter
            ) {
                content()
            }

            if (showingLogoutDialog) {
                LogoutDialog(
                    onLogout = {

                    },
                    onCancel = {

                    },
                    onDismiss = {
                        showingLogoutDialog = false
                    }
                )
            }

        }
    )
}