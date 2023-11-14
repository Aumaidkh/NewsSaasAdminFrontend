package com.hopcape.newssaas.admin.utils

const val BoldTag = "strong"

sealed class ControlStyle(val style: String) {

    class Bold(val content: String, tag: String = "strong") : ControlStyle(
        style = if (content.startsWith("<$tag>")) removeTag(tag, content) else "<$tag>$content</$tag>"
    )

    class Italic(val content: String, tag: String = "em") : ControlStyle(
        style = if (content.startsWith("<$tag>")) removeTag(tag, content) else "<$tag>$content</$tag>"
    )

    class Underline(val content: String, tag: String = "u") : ControlStyle(
        style = if (content.startsWith("<$tag>")) removeTag(tag, content) else "<$tag>$content</$tag>"
    )

    class Image(val content: String, url: String, description: String) : ControlStyle(
        style = "<img src=\"$url\" alt=\"$description\">"
    )

    class Link(
        val content: String,
        val href: String,
        val title: String,
        tag: String = "a") : ControlStyle(
        style = if (content.startsWith("<$tag>")) removeTag(tag, content) else "<$tag href=$href title=\"$title\">$content</$tag>"
    )

    class Quotes(val content: String, tag: String = "blockquote") : ControlStyle(
        style = if (content.startsWith("<$tag>")) removeTag(tag, content) else "<$tag><em>\"$content\"</em></$tag>"
    )

    class Title(val content: String, tag: String = "h3") : ControlStyle(
        style = if (content.startsWith("<$tag>")) removeTag(tag, content) else "<$tag>$content</$tag>"
    )

    class Subtitle(val content: String, tag: String = "h5") : ControlStyle(
        style = if (content.startsWith("<$tag>")) removeTag(tag, content) else "<$tag>$content</$tag>"
    )

    class LineBreak(val content: String) : ControlStyle(
        style = "$content<br>"
    )

}

fun removeTag(tag: String, content: String): String {
    val openingTag = "<$tag>"
    val closingTag = "</$tag>"
    val result1 = content.removePrefix(openingTag)
    return result1.removeSuffix(closingTag)
}
