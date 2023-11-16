package com.hopcape.newssaas.admin.components.widgets.cards

import androidx.compose.runtime.Composable
import com.hopcape.newssaas.admin.components.widgets.Icon
import com.hopcape.newssaas.admin.style.PrimaryColor
import com.hopcape.newssaas.admin.style.Shapes
import com.hopcape.newssaas.admin.utils.Resource
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.silk.components.layout.HorizontalDivider
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.CSSColorValue
import org.jetbrains.compose.web.css.px

const val ButtonContainer = "buttonContainer"
const val ButtonText = "buttonText"

@Composable
fun StatisticsOverviewCard(
    modifier: Modifier = Modifier,
    model: StatisticsOverviewCardModel,
    onQuickActionClick: (StatisticsOverviewCardType) -> Unit
) {
    Box(
        modifier = StatisticsOverviewCardStyle
            .toModifier()
            .then(modifier)
            .padding(leftRight = 14.px, bottom = 14.px),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = StatisticsOverviewButtonStyle
                .toModifier()
                .margin(top = 12.px)
                .padding(14.px)
                .fillMaxWidth()
                .backgroundColor(Colors.White)
                .borderRadius(Shapes.Medium.cornerRadius.px),
            horizontalAlignment = Alignment.Start
        ) {
            SpanText(
                modifier = StatisticsOverviewTextStyle
                    .toModifier(),
                text = model.label
            )
            SpanText(
                modifier = StatisticsOverviewTextStyle
                    .toModifier()
                    .color(Colors.Black)
                    .fontSize(18.px)
                    .fontWeight(FontWeight.Medium),
                text = model.value
            )

            HorizontalDivider()

            Row(
                modifier = Modifier
                    .id(ButtonContainer)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    path = model.buttonIcon,
                    size = 20
                )
                SpanText(
                    modifier = Modifier
                        .id(ButtonText)
                        .onClick { onQuickActionClick(model.type) },
                    text = model.quickActionButtonText
                )
            }

        }

        Box(
            modifier = Modifier
                .margin(leftRight = 14.px)
                .backgroundColor(model.color)
                .borderRadius(Shapes.Medium.cornerRadius.px)
                .padding(16.px),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                path = model.icon,
                tint = "#FFFFFF",
                size = 24
            )
        }
    }
}

@Composable
fun StatisticsOverviewCardView(
    modifier: Modifier = Modifier,
    onQuickActionClick: (StatisticsOverviewCardType) -> Unit
) {
    SimpleGrid(
        modifier = modifier,
        numColumns = numColumns(base = 1, sm = 1, md = 2, xl = 4),
    ){
        staticsOverviewCards.forEach { card ->
            StatisticsOverviewCard(
                modifier = Modifier
                    .margin(leftRight = 4.px),
                model = card,
                onQuickActionClick = onQuickActionClick
            )
        }
    }
}


data class StatisticsOverviewCardModel(
    val icon: String,
    val label: String,
    val value: String,
    val quickActionButtonText: String,
    val buttonIcon: String = Resource.Icons.RedoIcon,
    val color: CSSColorValue,
    val type: StatisticsOverviewCardType = StatisticsOverviewCardType.PublishedArticles
)

val staticsOverviewCards = listOf(
    StatisticsOverviewCardModel(
        icon = Resource.Icons.AllArticles,
        label = "Published Articles",
        value = "3200",
        quickActionButtonText = "View All",
        color = PrimaryColor.rgb,
        type = StatisticsOverviewCardType.PublishedArticles
    ),
    StatisticsOverviewCardModel(
        icon = Resource.Icons.UsersIcon,
        label = "Active Users",
        value = "320",
        quickActionButtonText = "Manage",
        color = Colors.Orange,
        type = StatisticsOverviewCardType.ActiveUsers
    ),
    StatisticsOverviewCardModel(
        icon = Resource.Icons.QuotesIcon,
        label = "Total Comments",
        value = "1100",
        quickActionButtonText = "Manage",
        color = Colors.Green,
        type = StatisticsOverviewCardType.TotalComments
    ),
    StatisticsOverviewCardModel(
        icon = Resource.Icons.DollarIcon,
        label = "Total Revenue",
        value = "$1200",
        quickActionButtonText = "Withdraw",
        color = Colors.Purple,
        buttonIcon = Resource.Icons.CashIcon,
        type = StatisticsOverviewCardType.TotalRevenue
    )
)

sealed interface StatisticsOverviewCardType {
    data object PublishedArticles: StatisticsOverviewCardType
    data object ActiveUsers: StatisticsOverviewCardType
    data object TotalComments: StatisticsOverviewCardType
    data object TotalRevenue: StatisticsOverviewCardType
}