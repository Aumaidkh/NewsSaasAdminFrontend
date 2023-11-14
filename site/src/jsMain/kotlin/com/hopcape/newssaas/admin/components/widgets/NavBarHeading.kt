package com.hopcape.newssaas.admin.components.widgets

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.style.Constants.FONT_FAMILY
import com.hopcape.newssaas.admin.style.NavigationItemStyle
import com.hopcape.newssaas.admin.utils.Resource
import com.hopcape.newssaas.admin.utils.Resource.Id.NavigationItem.NavigationItemContainer
import com.hopcape.newssaas.admin.utils.Resource.Id.NavigationItem.NavigationItemIcon
import com.hopcape.newssaas.admin.utils.Resource.Id.NavigationItem.NavigationItemRow
import com.hopcape.newssaas.admin.utils.Resource.Id.NavigationItem.NavigationItemText
import com.hopcape.newssaas.admin.utils.Resource.Id.NavigationItem.SvgParent
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.SVGStrokeLineCap
import com.varabyte.kobweb.compose.dom.svg.SVGStrokeLineJoin
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.ArrowForwardIcon
import com.varabyte.kobweb.silk.components.icons.ChevronRightIcon
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun NavBarHeading(text: String, modifier: Modifier = Modifier) {
    SpanText(
        modifier = modifier
            .padding(leftRight = 24.px)
            .fontFamily(FONT_FAMILY)
            .fontWeight(FontWeight.Medium)
            .fontSize(13.px)
            .color(Colors.LightGray.copy(alpha = 158)),
        text = text
    )
}

@Composable
fun NavigationItem(
    modifier: Modifier = Modifier,
    label: String,
    icon: String
) {
    Box(
        modifier = NavigationItemStyle
            .toModifier()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .id(NavigationItemContainer)
                .then(modifier)
                .fillMaxWidth()
                .padding(leftRight = 24.px, topBottom = 12.px)
                .cursor(Cursor.Pointer),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .margin(right = 24.px),
                path = icon,
                size = 24
            )
            SpanText(
                modifier = Modifier
                    .id(NavigationItemText)
                    .fillMaxWidth()
                    .fontFamily(FONT_FAMILY)
                    .fontWeight(FontWeight.Normal)
                    .fontSize(14.px),
                text = label
            )
            Icon(
                modifier = Modifier
                    .id(NavigationItemIcon),
                size = 18,
                path = Resource.Icons.ChevronRightIcon
            )
        }
    }


}

@Composable
fun Icon(
    modifier: Modifier = Modifier,
    path: String,
    size: Int = 32
) {
    Svg(
        attrs = modifier
            .id(SvgParent)
            .width(size.px)
            .height(size.px)
            .toAttrs {
                attr("viewBox", "0 0 18 18")
            }

    ) {
        Path {
            id(NavigationItemIcon)
            d(path)
            strokeWidth(0.4)
            strokeLineCap(SVGStrokeLineCap.Round)
            strokeLineJoin(SVGStrokeLineJoin.Round)
        }
    }
}