package com.hopcape.newssaas.admin.components.widgets.input_elements

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.components.dialogs.Dialog
import com.hopcape.newssaas.admin.components.widgets.Icon
import com.hopcape.newssaas.admin.style.Constants
import com.hopcape.newssaas.admin.style.InputFieldStyle
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.px

@Composable
fun DropDown(
    modifier: Modifier = Modifier,
    label: String = "Select Category",
    placeholder: String = "Select Category",
    maxWidth: Int = 320
) {
    Column {
        SpanText(
            modifier = Modifier
                .fontFamily(Constants.FONT_FAMILY)
                .fontWeight(FontWeight.Normal)
                .fontSize(14.px)
                .color(Colors.Black)
                .margin(bottom = 12.px),
            text = label
        )
        Row(
            modifier = InputFieldStyle
                .toModifier()
                .then(modifier)
                .height(45.px)
                .width(maxWidth.px)
                .padding(leftRight = 24.px, topBottom = 8.px)
                .borderRadius(8.px),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SpanText(
                modifier = Modifier
                    .fontFamily(Constants.FONT_FAMILY)
                    .fontWeight(FontWeight.Normal)
                    .fontSize(14.px)
                    .color(if (placeholder.equals("Select a Category",true)) Colors.Black.copy(alpha = 78) else Colors.Black)
                    .margin(right = 24.px),
                text = placeholder
            )
            Icon(
                path = Resource.Icons.ChevronDownIcon,
                size = 24
            )
        }
    }

}

@Composable
fun DatePicker(
    modifier: Modifier = Modifier,
    label: String = "Select Date Time",
    placeholder: String = "00:00 AM 12/12/2023",
    onClick: () -> Unit
) {
    Column {
        SpanText(
            modifier = Modifier
                .fontFamily(Constants.FONT_FAMILY)
                .fontWeight(FontWeight.Normal)
                .fontSize(14.px)
                .color(Colors.Black)
                .margin(bottom = 12.px),
            text = label
        )

    }
}

@Composable
fun DatePickerDialog(
    onDismiss: () -> Unit,
    onDatePicked: (String) -> Unit
) {
    Dialog(
        title = "Pick Date",
        onDismiss = onDismiss,
        content = {
            org.jetbrains.compose.web.dom.Input(
                type = InputType.DateTimeLocal
            )
        }
    )
}