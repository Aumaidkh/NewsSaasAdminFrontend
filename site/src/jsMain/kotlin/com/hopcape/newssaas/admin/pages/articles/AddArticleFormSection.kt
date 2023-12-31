package com.hopcape.newssaas.admin.pages.articles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hopcape.newssaas.admin.components.layouts.ChipsPicker
import com.hopcape.newssaas.admin.components.widgets.InputField
import com.hopcape.newssaas.admin.components.widgets.InputType
import com.hopcape.newssaas.admin.components.widgets.button.PrimaryButton
import com.hopcape.newssaas.admin.components.widgets.input_elements.DropDown
import com.hopcape.newssaas.admin.components.widgets.input_elements.LabelledSwitch
import com.hopcape.newssaas.admin.style.Constants
import com.hopcape.newssaas.admin.utils.ControlStyle
import com.hopcape.newssaas.admin.utils.HelperMethods
import com.hopcape.newssaas.admin.utils.HelperMethods.getEditor
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.forms.Switch
import com.varabyte.kobweb.silk.components.forms.SwitchSize
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px

@Composable
fun AddArticleFormSection(
    breakpoint: Breakpoint,
    category: String,
    onCategoryClick: () -> Unit,
    onLinkControlClick: () -> Unit,
    onImageControlClick: () -> Unit,
    onSubmit: () -> Unit,
) {
    var editorVisibility by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .borderRadius(8.px)
            .backgroundColor(Colors.White)
            .padding(24.px)
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .id(Resource.Id.NavigationItem.NavigationItemContainer)
                .padding(leftRight = 100.px)
                .fillMaxWidth(85.percent),
            horizontalAlignment = Alignment.Start
        ) {
            SpanText(
                modifier = Modifier
                    .fillMaxWidth()
                    .fontFamily(Constants.FONT_FAMILY)
                    .fontWeight(FontWeight.Normal)
                    .color(Colors.Black)
                    .fontSize(23.px)
                    .margin(bottom = 30.px),
                text = "Add Article"
            )
            InputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px),
                inputType = InputType.TEXT,
                label = "Article Title*",
                placeholder = "Pakistan ruled out of CWC 2013",
            )
            InputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px),
                inputType = InputType.TEXT,
                label = "Sub Title*",
                placeholder = "Can pakistan make the miraculous entry back into the world cup?",
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DropDown(
                    modifier = Modifier
                        .onClick {
                            onCategoryClick()
                        },
                    label = "Category *",
                    placeholder = category,
                    maxWidth = if (breakpoint <= Breakpoint.MD) 270 else 320
                )

                InputField(
                    maxWidth = if (breakpoint <= Breakpoint.MD) 270 else 320,
                    label = "Select Date Time *",
                    inputType = InputType.DATE,
                )
            }

            ChipsPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px)
            )

            InputField(
                modifier = Modifier
                    .margin(bottom = 24.px),
                label = "Select File *",
                inputType = InputType.IMAGE
            )

            InputField(
                modifier = Modifier
                    .margin(bottom = 24.px),
                label = "Paste URL ( Optional )",
                inputType = InputType.URL,
                placeholder = "https://"
            )


            EditorComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px),
                onEditorControlClick = { control ->
                    when(control){
                        is EditorControl.BoldControl -> {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    HelperMethods.applyStyle(
                                        text = it,
                                        style = ControlStyle.Bold(it)
                                    )
                                }
                            }

                        }
                        is EditorControl.UndoControl -> HelperMethods.undo()
                        EditorControl.ImageControl -> {
                            onImageControlClick()
                        }
                        EditorControl.ItalicControl -> {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    HelperMethods.applyStyle(
                                        text = it,
                                        style = ControlStyle.Italic(it)
                                    )
                                }
                            }
                        }
                        EditorControl.LinkControl -> {
                            onLinkControlClick()
                        }
                        EditorControl.QuotesControl -> {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    HelperMethods.applyStyle(
                                        text = it,
                                        style = ControlStyle.Quotes(it)
                                    )
                                }
                            }
                        }
                        EditorControl.RedoControl -> {}
                        EditorControl.SubtitleControl -> {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    HelperMethods.applyStyle(
                                        text = it,
                                        style = ControlStyle.Subtitle(it)
                                    )
                                }
                            }
                        }
                        EditorControl.TitleControl -> {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    HelperMethods.applyStyle(
                                        text = it,
                                        style = ControlStyle.Title(it)
                                    )
                                }
                            }
                        }
                        EditorControl.UnderlineControl -> {
                            HelperMethods.getSelectedText()?.let {
                                if (it.isNotEmpty()){
                                    HelperMethods.applyStyle(
                                        text = it,
                                        style = ControlStyle.Underline(it)
                                    )
                                }
                            }
                        }
                    }
                },
                editorVisibility = editorVisibility
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(topBottom = 24.px),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                SpanText(
                    modifier = Modifier
                        .fontFamily(Constants.FONT_FAMILY)
                        .fontWeight(FontWeight.Normal)
                        .color(Colors.DarkGray)
                        .fontSize(15.px)
                        .onClick {
                            editorVisibility = !editorVisibility
                            document.getElementById(Resource.Id.Input.EditorPreview)?.innerHTML = getEditor().value
                        }
                        .cursor(Cursor.Pointer)
                        .margin(right = 24.px),
                    text = "Preview"
                )

                PrimaryButton(
                    text = "Submit",
                    onClick = onSubmit
                )
            }
        }
    }
}


