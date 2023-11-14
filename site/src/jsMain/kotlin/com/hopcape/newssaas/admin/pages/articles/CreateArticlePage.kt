package com.hopcape.newssaas.admin.pages.articles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hopcape.newssaas.admin.components.dialogs.LinkPickerDialog
import com.hopcape.newssaas.admin.components.dialogs.SubmissionDialog
import com.hopcape.newssaas.admin.components.layouts.SidePanelView
import com.hopcape.newssaas.admin.style.BackgroundColor
import com.hopcape.newssaas.admin.utils.ControlStyle
import com.hopcape.newssaas.admin.utils.Dimensions
import com.hopcape.newssaas.admin.utils.HelperMethods
import com.hopcape.newssaas.admin.utils.HelperMethods.applyStyle
import com.hopcape.newssaas.admin.utils.HelperMethods.getEditor
import com.hopcape.newssaas.admin.utils.Resource
import com.hopcape.newssaas.admin.utils.Stack
import com.hopcape.newssaas.admin.utils.pop
import com.hopcape.newssaas.admin.utils.push
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.classNames
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.browser.document
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import org.w3c.dom.HTMLInputElement

@Page("create")
@Composable
fun CreateArticlePage() {
    val breakpoint = rememberBreakpoint()
    var showingCategoryDialog by remember { mutableStateOf(false) }
    var showingArticleSubmissionDialog by remember { mutableStateOf(false) }
    var showingLinkDialog by remember { mutableStateOf(false) }
    var showingImageDialog by remember { mutableStateOf(false) }
    var category by remember { mutableStateOf("Select a category") }

    val undoStack: Stack<String> = remember {
        mutableListOf()
    }

    SidePanelView(
        breakpoint = breakpoint,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(100.vh)
                    .backgroundColor(BackgroundColor.rgb)
                    .styleModifier {
                        property("overflow-y","auto")
                    },
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .maxWidth(Dimensions.PAGE_WIDTH.px)
                        .padding(24.px),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AddArticleFormSection(
                        category = category,
                        onCategoryClick = {
                            showingCategoryDialog = !showingCategoryDialog
                        },
                        onSubmit = {
                            showingArticleSubmissionDialog = true
                        },
                        breakpoint = breakpoint,
                        onBoldClick = {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    applyStyle(
                                        text = it,
                                        style = ControlStyle.Bold(it)
                                    ).also { result ->
                                        undoStack.push(result)
                                    }
                                }
                            }
                        },
                        onItalicClick = {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    applyStyle(
                                        text = it,
                                        style = ControlStyle.Italic(it)
                                    ).also { result ->
                                        undoStack.push(result)
                                    }
                                }
                            }
                        },
                        onUnderlineClick = {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    applyStyle(
                                        text = it,
                                        style = ControlStyle.Underline(it)
                                    ).also { result ->
                                        undoStack.push(result)
                                    }
                                }
                            }
                        },
                        onQuotesClick = {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    applyStyle(
                                        text = it,
                                        style = ControlStyle.Quotes(it)
                                    ).also { result ->
                                        undoStack.push(result)
                                    }
                                }
                            }
                        },
                        onLinkClick = {
                            if (!HelperMethods.getSelectedText().isNullOrEmpty()){
                                showingLinkDialog = !showingLinkDialog
                            }
                        },
                        onImageClick = {
                            showingImageDialog = true
                        },
                        onTitleClick = {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    applyStyle(
                                        text = it,
                                        style = ControlStyle.Title(it)
                                    ).also { result ->
                                        undoStack.push(result)
                                    }
                                }
                            }
                        },
                        onSubtitleClick = {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    applyStyle(
                                        text = it,
                                        style = ControlStyle.Subtitle(it)
                                    ).also { result ->
                                        undoStack.push(result)
                                    }
                                }
                            }
                        },
                        onUndoClick = {
                            val contentToRemove = undoStack.pop()
                            contentToRemove?.let {
                                removeFromTextArea(contentToRemove)
                            }
                        },
                        onRedoClick = {
                            // Future Release: Add this feature in upcoming releases
                        },
                        onEnterPress = {
                            val text = getEditor().value
                            applyStyle(
                                text = text,
                                style = ControlStyle.LineBreak(content = text)
                            )
                        },
                        onVideoClick = {
                            // Future Release Add this feature in some upcoming releases
                        }
                    )
                }

                if (showingCategoryDialog) {
                    CategoryDialog(
                        onDismiss = {
                            showingCategoryDialog = false
                        },
                        onCategorySelected = {
                            showingCategoryDialog = false
                            category = it
                        }
                    )
                }
                if (showingArticleSubmissionDialog) {
                    SubmissionDialog(
                        onDismiss = {
                            showingArticleSubmissionDialog = false
                        },
                        onCancel = {
                            showingArticleSubmissionDialog = false
                        },
                        onConfirm = {
                            showingArticleSubmissionDialog = false
                        }
                    )
                }

                if (showingLinkDialog) {
                    LinkPickerDialog(
                        onDismiss = {
                            showingLinkDialog = false
                        },
                        onSubmit = { title, link ->
                            showingLinkDialog = false
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    HelperMethods.applyStyle(
                                        text = it,
                                        style = ControlStyle.Link(
                                            content = it,
                                            href = link,
                                            title = title
                                        )
                                    )
                                }
                            }
                        }
                    )
                }

                if (showingImageDialog) {
                    LinkPickerDialog(
                        label1 = "Alternate Text *",
                        placeHolder1 = "Google Image",
                        label2 = "URL *",
                        buttonText = "Add Image",
                        onDismiss = {
                            showingImageDialog = false
                        },
                        onSubmit = { description, link ->
                            val currentContent = getEditor().value
                            val imageHtml = ControlStyle.Image(
                                content = "",
                                url = link,
                                description = description
                            ).style.also {
                                undoStack.push(it)
                            }
                            val newContent = currentContent + imageHtml
                            getEditor().value = newContent             }
                    )
                }

            }

        }
    )
}


fun printMessage(message: String, id: String){
    (document.getElementById(id) as HTMLInputElement).value = message
}

private fun removeFromTextArea(htmlContent: String?){
    htmlContent?.let {
        val text = getInnerHtmlText(htmlContent)
        val textAreaContent = getEditor().value
        val replacedText = textAreaContent.replace(it,text)
        getEditor().value = replacedText
    }
}

private fun getInnerHtmlText(htmlContent: String): String {
    val regex = Regex("<.*?>(\"?)([^<]*)\\1<\\/.*?>")
    val matchResult = regex.find(htmlContent)
    return matchResult?.groupValues?.get(1) ?: ""
}
