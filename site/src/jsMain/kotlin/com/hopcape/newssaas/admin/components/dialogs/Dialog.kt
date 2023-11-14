package com.hopcape.newssaas.admin.components.dialogs

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.components.widgets.Icon
import com.hopcape.newssaas.admin.style.Constants
import com.hopcape.newssaas.admin.style.PrimaryColor
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Composable
fun Dialog(
    title: String,
    onDismiss: () -> Unit,
    contentPadding: Int = 24,
    titlePadding: Int = 0,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(Colors.DarkGray.copy(alpha = 99))
            .position(Position.Fixed)
            .height(100.vh),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .borderRadius(8.px),
            contentAlignment = Alignment.TopEnd
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(top = 24.px, right = 10.px)
                    .backgroundColor(Colors.White)
                    .padding(all = contentPadding.px)
                    .borderRadius(8.px)
                    .boxShadow(offsetX = 10.px,offsetY = 10.px,blurRadius = 15.px, color = Colors.DarkGray),
                horizontalAlignment = Alignment.Start
            ) {
                SpanText(
                    modifier = Modifier
                        .margin(leftRight = titlePadding.px,bottom = 16.px)
                        .width(350.px)
                        .fontFamily(Constants.FONT_FAMILY)
                        .fontWeight(FontWeight.Normal)
                        .color(Colors.Black),
                    text = title
                )

                content()

            }

            // Dismiss Icon
            Box(
                modifier = Modifier
                    .backgroundColor(PrimaryColor.rgb)
                    .onClick { onDismiss() }
                    .borderRadius(100.percent)
                    .boxShadow(offsetX = 2.px,offsetY = 2.px,blurRadius = 5.px, color = Colors.DarkGray)
                    .padding(9.px),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    size = 20,
                    path = Resource.Icons.CrossIcon,
                    modifier = Modifier
                        .styleModifier {
                            property("stroke", Colors.White)
                            property("fill", Colors.White)
                        }
                )
            }

        }

    }
}