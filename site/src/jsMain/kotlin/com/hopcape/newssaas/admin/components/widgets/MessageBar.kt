package com.hopcape.newssaas.admin.components.widgets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.hopcape.newssaas.admin.style.BackgroundColor
import com.hopcape.newssaas.admin.style.Constants.FONT_FAMILY
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxHeight
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.icons.CheckIcon
import com.varabyte.kobweb.silk.components.icons.CloseIcon
import com.varabyte.kobweb.silk.components.icons.SquareIcon
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

sealed class Message(open val message: String){
    data class Success(override val message: String): Message(message)
    data class Error(override val message: String): Message(message)
    data class Info(override val message: String): Message(message)

    fun status() =
        when(this){
            is Error -> "Error"
            is Info -> "Info"
            is Success -> "Success"
        }

    fun color() =
        when(this){
            is Error -> Colors.Red
            is Info -> Colors.Yellow
            is Success -> Colors.Green
        }

    @Composable
    fun icon() =
        when(this){
            is Error -> CloseIcon(
                modifier = Modifier
                    .size(48.px)
                    .margin(40.px)
                    .color(this.color())
            )
            is Info -> SquareIcon(
                modifier = Modifier
                    .size(48.px)
                    .margin(40.px)
                    .color(this.color())
            )
            is Success -> CheckIcon(
                modifier = Modifier
                    .size(48.px)
                    .margin(40.px)
                    .color(this.color())
            )
        }
}

@Composable
fun MessageBar(
    message: Message,
    onDismiss: () -> Unit
) {
    LaunchedEffect(key1 = Unit){
        delay(3000)
        onDismiss()
    }
    Box(
        modifier = Modifier
            .backgroundColor(Colors.Black.copy(alpha = 60))
            .fillMaxSize()
            .onClick {
                onDismiss()
            },
        contentAlignment = Alignment.BottomEnd
    ) {
        // Actual Bar
        Row(
            modifier = Modifier
                .margin(64.px)
                .padding(right = 48.px)
                .height(120.px)
                .borderRadius(8.px)
                .backgroundColor(Colors.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Strip
            Box(
                modifier = Modifier
                    .borderRadius(topLeft = 8.px, bottomLeft = 8.px)
                    .fillMaxHeight()
                    .width(15.px)
                    .backgroundColor(message.color())
            )
            // Icon
            message.icon()
            // State
            // Message
            Column(
                modifier = Modifier
                    .padding(all = 24.px),
                horizontalAlignment = Alignment.Start
            ) {
                SpanText(
                    modifier = Modifier
                        .fontFamily(FONT_FAMILY)
                        .fontWeight(FontWeight.Medium)
                        .fontSize(25.px)
                        .color(message.color()),
                    text = message.status()
                )
                SpanText(
                    modifier = Modifier
                        .fontFamily(FONT_FAMILY)
                        .fontWeight(FontWeight.Medium)
                        .fontSize(18.px)
                        .color(Colors.Black),
                    text = message.message
                )
            }
        }
    }
}