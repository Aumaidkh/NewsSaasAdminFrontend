package com.hopcape.newssaas.admin.pages.articles

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.components.widgets.Icon
import com.hopcape.newssaas.admin.components.widgets.button.OutlinedPrimaryButton
import com.hopcape.newssaas.admin.style.BackgroundColor
import com.hopcape.newssaas.admin.style.EditorIconStyle
import com.hopcape.newssaas.admin.style.EditorItemStyle
import com.hopcape.newssaas.admin.style.InputFieldStyle
import com.hopcape.newssaas.admin.style.NavigationItemStyle
import com.hopcape.newssaas.admin.style.noBorder
import com.hopcape.newssaas.admin.utils.ControlStyle
import com.hopcape.newssaas.admin.utils.HelperMethods.applyStyle
import com.hopcape.newssaas.admin.utils.HelperMethods.getEditor
import com.hopcape.newssaas.admin.utils.HelperMethods.getSelectedText
import com.hopcape.newssaas.admin.utils.HelperMethods.handleEnterPress
import com.hopcape.newssaas.admin.utils.Resource
import com.hopcape.newssaas.admin.utils.Resource.Icons.BoldIcon
import com.hopcape.newssaas.admin.utils.Resource.Icons.ImageIcon
import com.hopcape.newssaas.admin.utils.Resource.Icons.ItalicIcon
import com.hopcape.newssaas.admin.utils.Resource.Icons.LinkIcon
import com.hopcape.newssaas.admin.utils.Resource.Icons.QuotesIcon
import com.hopcape.newssaas.admin.utils.Resource.Icons.RedoIcon
import com.hopcape.newssaas.admin.utils.Resource.Icons.SubtitleIcon
import com.hopcape.newssaas.admin.utils.Resource.Icons.TitleIcon
import com.hopcape.newssaas.admin.utils.Resource.Icons.UnderlineIcon
import com.hopcape.newssaas.admin.utils.Resource.Icons.UndoIcon
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.Resize
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.css.Visibility
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxHeight
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.onKeyDown
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.resize
import com.varabyte.kobweb.compose.ui.modifiers.scrollBehavior
import com.varabyte.kobweb.compose.ui.modifiers.visibility
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.style.toModifier
import kotlinx.browser.document
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.TextArea

@Composable
fun EditorComponent(
    modifier: Modifier = Modifier,
    onEditorControlClick: (EditorControl) -> Unit,
    editorVisibility: Boolean = true,
    onPreview: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .margin(bottom = 8.px),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Show Editor Controls when Text area is visible
            if (editorVisibility){
                editorControls.forEach { control ->
                    Box(
                        modifier = EditorItemStyle
                            .toModifier()
                            .onClick {
                                onEditorControlClick(control)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = EditorIconStyle
                                .toModifier(),
                            path = control.icon,
                            size = if (control is EditorControl.SubtitleControl) 45 else 50
                        )
                    }
                }
            }
            Spacer()
            OutlinedPrimaryButton(
                text = if (editorVisibility) "Show Preview" else "Hide Preview",
                borderWidth = 2,
                onClick = onPreview
            )
        }
        TextArea(
            attrs = InputFieldStyle
                .toModifier()
                .id(Resource.Id.Input.Editor)
                .height(400.px)
                .maxHeight(400.px)
                .resize(Resize.None)
                .fillMaxWidth()
                .display(if (editorVisibility) DisplayStyle.Block else DisplayStyle.None)
                .onKeyDown {
                    if (it.key == "Enter"){
                        handleEnterPress()
                    }
                }
                .padding(24.px)
                .margin(bottom = 24.px)
                .toAttrs()
        )
        PreviewComponent(
            modifier = Modifier
                .display(if (editorVisibility) DisplayStyle.None else DisplayStyle.Block)
        )
    }
}

@Composable
fun PreviewComponent(
    modifier: Modifier = Modifier
) {
    Div(
        attrs = modifier
            .id(Resource.Id.Input.EditorPreview)
            .fillMaxWidth()
            .maxHeight(100.vh)
            .margin(top = 8.px)
            .padding(all = 20.px)
            .backgroundColor(Colors.White)
            .color(Colors.Black)
            .borderRadius(r = 4.px)
            .overflow(Overflow.Auto)
            .scrollBehavior(ScrollBehavior.Smooth)
            .noBorder()
            .toAttrs()
    )
}

sealed class EditorControl(
    val label: String = "",
    val icon: String,
) {
    data object BoldControl : EditorControl(icon = BoldIcon)
    data object ItalicControl : EditorControl(icon = ItalicIcon)
    data object UnderlineControl : EditorControl(icon = UnderlineIcon)
    data object LinkControl : EditorControl(icon = LinkIcon)
    data object QuotesControl : EditorControl(icon = QuotesIcon)
    data object ImageControl : EditorControl(icon = ImageIcon)
    data object TitleControl : EditorControl(icon = TitleIcon)
    data object SubtitleControl : EditorControl(icon = TitleIcon)
    data object UndoControl : EditorControl(icon = UndoIcon)
    data object RedoControl : EditorControl(icon = RedoIcon)
}

val editorControls = listOf(
    EditorControl.BoldControl,
    EditorControl.ItalicControl,
    EditorControl.UnderlineControl,
    EditorControl.LinkControl,
    EditorControl.QuotesControl,
    EditorControl.ImageControl,
    EditorControl.TitleControl,
    EditorControl.SubtitleControl,
    EditorControl.UndoControl,
    EditorControl.RedoControl,
)