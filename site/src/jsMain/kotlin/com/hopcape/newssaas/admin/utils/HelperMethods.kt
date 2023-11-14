package com.hopcape.newssaas.admin.utils

import kotlinx.browser.document
import org.w3c.dom.HTMLTextAreaElement

object HelperMethods {

    /**
     * Returns the Editor Instance*/
    fun getEditor() = document.getElementById(Resource.Id.Input.Editor) as HTMLTextAreaElement

    /**
     * Returns the selected range of the text*/
    fun getSelectedIntRange(): IntRange? {
        val editor = getEditor()
        val start = editor.selectionStart
        val end = editor.selectionEnd
        return if (start != null && end != null) {
            IntRange(start, (end - 1))
        } else null
    }

    /**
     * Returns the cursor selected text
     * */
    fun getSelectedText(): String? {
        val range = getSelectedIntRange()
        return if (range != null) {
            getEditor().value.substring(range)
        } else null
    }

    /**
     * Applies the
     * @param  style to the
     * @param text
     * @return [String] html content*/
    fun applyStyle(text: String,style: ControlStyle): String {
        val result = getEditor().value.replace(text,style.style)
        getEditor().value = result
        getEditor().focus()
        return style.style
    }

    /**
     * Applies undo functionality*/
    fun undo(){
        val text = getEditor().value
        val result = text.split(" ")
        getEditor().value = result.dropLast(result.size-1).joinToString { " " }
    }

    fun handleEnterPress(){
        val text = getEditor().value
        applyStyle(
            text = text,
            style = ControlStyle.LineBreak(text)
        )
    }
}