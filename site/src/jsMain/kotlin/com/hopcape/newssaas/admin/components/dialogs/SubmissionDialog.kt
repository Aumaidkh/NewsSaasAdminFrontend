package com.hopcape.newssaas.admin.components.dialogs

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.components.dialogs.Dialog
import com.hopcape.newssaas.admin.style.Constants
import com.hopcape.newssaas.admin.style.PrimaryColor
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px


@Composable
fun SubmissionDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    Dialog(
        title = "Submit Article",
        onDismiss = onDismiss,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                SpanText(
                    modifier = Modifier
                        .fontFamily(Constants.FONT_FAMILY)
                        .fontWeight(FontWeight.Normal)
                        .color(Colors.Black)
                        .maxWidth(260.px)
                        .fontSize(14.px)
                        .margin(bottom = 24.px),
                    text = "Are you sure you want to submit the Article?"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    SpanText(
                        modifier = Modifier
                            .fontFamily(Constants.FONT_FAMILY)
                            .fontWeight(FontWeight.Normal)
                            .color(Colors.LightGray)
                            .maxWidth(100.px)
                            .fontSize(14.px)
                            .cursor(Cursor.Pointer)
                            .onClick { onCancel() }
                            .margin(bottom = 12.px),
                        text = "Cancel"
                    )


                    SpanText(
                        modifier = Modifier
                            .fontFamily(Constants.FONT_FAMILY)
                            .fontWeight(FontWeight.Normal)
                            .color(PrimaryColor.rgb)
                            .maxWidth(100.px)
                            .fontSize(14.px)
                            .cursor(Cursor.Pointer)
                            .onClick { onConfirm() }
                            .margin(bottom = 12.px),
                        text = "Confirm"
                    )
                }
            }
        }
    )

}