package com.hopcape.newssaas.admin.style

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.rgb

data class Color(
    val hex: String,
    val rgb: Color.Rgb
)
val PrimaryColor = Color(
    hex = "#0d6efd",
    rgb = rgb(13,110,253)
)
val BackgroundColor = Color(
    hex = "#f6f9ff",
    rgb = rgb(246,249,255)
)
val Mercury = Color(
    hex = "#e3e7ea",
    rgb = rgb(227,231,234)
)