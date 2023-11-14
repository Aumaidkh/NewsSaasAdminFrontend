package com.hopcape.newssaas.admin.components.widgets

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.style.Constants.FONT_FAMILY
import com.hopcape.newssaas.admin.style.noBorder
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Input

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Input(
            type = InputType.Search,
            attrs = Modifier
                .fillMaxWidth()
                .height(55.px)
                .noBorder()
                .fontFamily(FONT_FAMILY)
                .fontSize(16.px)
                .borderRadius(12.px)
                .padding(leftRight = 48.px)
                .backgroundColor(Colors.White)
                .boxShadow(offsetY = 0.5.px, offsetX = 0.5.px, blurRadius = 12.px, color = Colors.LightGray)
                .toAttrs {
                        attr("placeholder","Search something...")
                },
        )

        Icon(
            modifier = Modifier
                .color(Colors.LightGray)
                .margin(12.px)
                .styleModifier {
                    property("stroke", Colors.LightGray)
                    property("fill", Colors.LightGray)
                },
            path = Resource.Icons.SearchIcon,
            size = 22
        )

    }
}