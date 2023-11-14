package com.hopcape.newssaas.admin.components.widgets.input_elements

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.style.Constants
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.silk.components.forms.Switch
import com.varabyte.kobweb.silk.components.forms.SwitchSize
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px


@Composable
fun LabelledSwitch(
    label: String = "Breaking News",
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Switch(
            modifier = Modifier
                .margin(right = 24.px),
            checked = checked,
            onCheckedChange = onCheckedChange,
            size = SwitchSize.LG
        )
        SpanText(
            modifier = Modifier
                .fontFamily(Constants.FONT_FAMILY)
                .fontWeight(FontWeight.Normal)
                .color(Colors.Black)
                .fontSize(14.px)
                .margin(bottom = 12.px),
            text = label
        )
    }
}