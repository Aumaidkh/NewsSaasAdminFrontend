package com.hopcape.newssaas.admin.pages.articles

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.components.dialogs.Dialog
import com.hopcape.newssaas.admin.components.widgets.defaultCategories
import com.hopcape.newssaas.admin.style.CategoryDialogStyle
import com.hopcape.newssaas.admin.style.Constants
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.px


@Composable
fun CategoryDialog(
    onDismiss: () -> Unit,
    onCategorySelected: (String) -> Unit
) {
    Dialog(
        title = "Select Category *",
        onDismiss = onDismiss,
        titlePadding = 12,
        contentPadding = 32,
        content = {
            defaultCategories.forEach { category ->
                SpanText(
                    modifier = CategoryDialogStyle
                        .toModifier()
                        .fillMaxWidth()
                        .id(Resource.Id.CategoryDialog.CategoryContainer)
                        .onClick {
                            onCategorySelected(category)
                        }
                        .fontFamily(Constants.FONT_FAMILY)
                        .fontSize(14.px)
                        .fontWeight(FontWeight.Normal)
                        .padding(leftRight = 24.px,topBottom = 8.px)
                        .cursor(Cursor.Pointer),
                    text = category
                )
            }
        }
    )

}