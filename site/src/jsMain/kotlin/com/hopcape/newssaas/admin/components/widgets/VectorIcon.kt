package com.hopcape.newssaas.admin.components.widgets

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.style.Color
import com.varabyte.kobweb.compose.dom.svg.Path
import com.varabyte.kobweb.compose.dom.svg.Svg
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.px

@Composable
fun VectorIcon(
    selected: Boolean = false,
    modifier: Modifier,
    pathData: String,
    color: com.varabyte.kobweb.compose.ui.graphics.Color.Rgb
) {
    Svg(
        attrs = Modifier
            .then(modifier)
            .width(24.px)
            .height(24.px)
            .toAttrs{
                attr("viewBox","0 0 24 24")
                attr("fill","none")
            }

    ) {
        Path {
            attr("d", pathData)
            stroke(color)
            attr("stroke-width", "1")
            attr("stroke-lineCap", "round")
            attr("stroke-lineJoin", "round")
        }
    }
}
