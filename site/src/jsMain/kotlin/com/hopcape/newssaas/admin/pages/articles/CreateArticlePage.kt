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
import com.varabyte.kobweb.compose.css.functions.url
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh

@Page("create")
@Composable
fun CreateArticlePage() {
    val breakpoint = rememberBreakpoint()
    var showingCategoryDialog by remember { mutableStateOf(false) }
    var showingArticleSubmissionDialog by remember { mutableStateOf(false) }
    var showingLinkDialog by remember { mutableStateOf(false) }
    var showingImageDialog by remember { mutableStateOf(false) }
    var category by remember { mutableStateOf("Select a category") }

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
                        onLinkControlClick = {
                            if (!HelperMethods.getSelectedText().isNullOrEmpty()){
                                showingLinkDialog = !showingLinkDialog
                            }
                        },
                        onImageControlClick = {
                            showingImageDialog = true
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
                            showingImageDialog = false
                            HelperMethods.applyStyle(
                                text = "",
                                style = ControlStyle.Image(
                                    content = "",
                                    url = link,
                                    description = description
                                )
                            )
                        }
                    )
                }

            }

        }
    )
}