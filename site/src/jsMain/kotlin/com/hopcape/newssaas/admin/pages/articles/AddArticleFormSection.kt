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
import com.hopcape.newssaas.admin.style.Constants
import com.hopcape.newssaas.admin.utils.HelperMethods.getEditor
import com.hopcape.newssaas.admin.utils.Resource
import com.hopcape.newssaas.admin.utils.Resource.Id.Input.articleSubtitle
import com.hopcape.newssaas.admin.utils.Resource.Id.Input.articleTitle
import com.hopcape.newssaas.admin.utils.Resource.Labels.addArticleFormTitleLabel
import com.hopcape.newssaas.admin.utils.Resource.Labels.articleSubtitleLabel
import com.hopcape.newssaas.admin.utils.Resource.Labels.articleTitleLabel
import com.hopcape.newssaas.admin.utils.Resource.Labels.categorySelectorLabel
import com.hopcape.newssaas.admin.utils.Resource.Labels.selectDateAndTimeLabel
import com.hopcape.newssaas.admin.utils.Resource.Labels.submitArticleButtonLabel
import com.hopcape.newssaas.admin.utils.Resource.Labels.thumbnailFilePickerLabel
import com.hopcape.newssaas.admin.utils.Resource.Labels.thumbnailPickerEnterUrlLabel
import com.hopcape.newssaas.admin.utils.Resource.Labels.thumbnailPickerUploadLabel
import com.hopcape.newssaas.admin.utils.Resource.Labels.thumbnailUrlTitleLabel
import com.hopcape.newssaas.admin.utils.Resource.PlaceHolders
import com.hopcape.newssaas.admin.utils.Resource.PlaceHolders.articleSubtitlePlaceholder
import com.hopcape.newssaas.admin.utils.Resource.PlaceHolders.articleTitlePlaceholder
import com.hopcape.newssaas.admin.utils.Resource.PlaceHolders.thumbnailUrlPlaceholder
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
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.w3c.files.File

@Composable
fun AddArticleFormSection(
    breakpoint: Breakpoint,
    category: String,
    onCategoryClick: () -> Unit,
    onEnterPress: () -> Unit,
    onUndoClick: () -> Unit,
    onRedoClick: () -> Unit,
    onBoldClick: () -> Unit,
    onItalicClick: () -> Unit,
    onUnderlineClick: () -> Unit,
    onImageClick: () -> Unit,
    onLinkClick: () -> Unit,
    onTitleClick: () -> Unit,
    onSubtitleClick: () -> Unit,
    onQuotesClick: () -> Unit,
    onSubmit: () -> Unit,
    onVideoClick: () -> Unit,
) {
    var editorVisibility by remember { mutableStateOf(true) }
    var thumbnailPickerMode = remember{ mutableStateOf<ThumbnailMode>(ThumbnailMode.Url(""))}
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.px)
            ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .borderRadius(8.px)
                .id(Resource.Id.NavigationItem.NavigationItemContainer)
                .padding(100.px)
                .backgroundColor(Colors.White)
                .fillMaxWidth(75.percent),
            horizontalAlignment = Alignment.Start
        ) {
            SpanText(
                modifier = Modifier
                    .id(Resource.Id.Input.FormTitle)
                    .fillMaxWidth()
                    .fontFamily(Constants.FONT_FAMILY)
                    .fontWeight(FontWeight.Normal)
                    .color(Colors.Black)
                    .fontSize(23.px)
                    .margin(bottom = 30.px),
                text = addArticleFormTitleLabel
            )
            InputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px),
                inputType = InputType.TEXT,
                label = articleTitleLabel,
                placeholder = articleTitlePlaceholder,
                id = articleTitle
            )
            InputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px),
                inputType = InputType.TEXT,
                label = articleSubtitleLabel,
                placeholder = articleSubtitlePlaceholder,
                id = articleSubtitle
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
                    label = categorySelectorLabel,
                    placeholder = category,
                    maxWidth = if (breakpoint <= Breakpoint.MD) 220 else 270
                )

                InputField(
                    maxWidth = if (breakpoint <= Breakpoint.MD) 270 else 320,
                    label = selectDateAndTimeLabel,
                    inputType = InputType.DATE,
                )
            }

            ChipsPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px)
            )

            ThumbnailPicker(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px),
                mode = thumbnailPickerMode.value,
                onClick = {
                    thumbnailPickerMode.value = if (thumbnailPickerMode.value is ThumbnailMode.Url) ThumbnailMode.ImageFile() else ThumbnailMode.Url()
                }
            )

            EditorComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .margin(bottom = 24.px),
                onPreview = {
                    editorVisibility = !editorVisibility
                    document.getElementById(Resource.Id.Input.EditorPreview)?.innerHTML = getEditor().value
                },
                onUndoClick = onUndoClick,
                onRedoClick = onRedoClick,
                onBoldClick = onBoldClick,
                onItalicClick = onItalicClick,
                onUnderlineClick = onUnderlineClick,
                onQuotesClick = onQuotesClick,
                onLinkClick = onLinkClick,
                onImageClick =onImageClick,
                onTitleClick = onTitleClick,
                onSubtitleClick = onSubtitleClick,
                editorVisibility = editorVisibility,
                onVideoClick = onVideoClick,
                onEnterPress = onEnterPress
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(topBottom = 24.px),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                PrimaryButton(
                    text = submitArticleButtonLabel,
                    onClick = onSubmit
                )
            }
        }
    }
}

@Composable
fun ThumbnailPicker(
    modifier: Modifier = Modifier,
    mode: ThumbnailMode,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        when(mode){
            is ThumbnailMode.Url -> {
                InputField(
                    modifier = Modifier
                        .fillMaxWidth(85.percent),
                    label = thumbnailUrlTitleLabel,
                    inputType = InputType.URL,
                    placeholder = thumbnailUrlPlaceholder
                )
            }
            is ThumbnailMode.ImageFile -> {
                InputField(
                    modifier = Modifier
                        .fillMaxWidth(85.percent),
                    label = thumbnailFilePickerLabel,
                    inputType = InputType.IMAGE
                )

            }
        }

        PrimaryButton(
            modifier = Modifier
                .fillMaxWidth(25.percent)
                .margin(left = 24.px),
            text = if (mode is ThumbnailMode.Url) thumbnailPickerUploadLabel else thumbnailPickerEnterUrlLabel,
            onClick = onClick,
            height = 45
        )
    }
}
sealed class ThumbnailMode{
    data class Url(val url: String? = null): ThumbnailMode()
    data class ImageFile(val file: File? = null): ThumbnailMode()
}


