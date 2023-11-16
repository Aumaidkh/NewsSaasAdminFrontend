package com.hopcape.newssaas.admin.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.hopcape.newssaas.admin.components.widgets.input_elements.LabelledSwitch
import com.hopcape.newssaas.admin.utils.Resource.Labels.breakingNewsSwitchLabel
import com.hopcape.newssaas.admin.utils.Resource.Labels.sponsoredSwitchLabel
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier


@Composable
fun ChipsPicker(
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(false) }
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        LabelledSwitch(
            label = breakingNewsSwitchLabel,
            checked = checked,
            onCheckedChange = {
                checked = it
            }
        )
        LabelledSwitch(
            label = sponsoredSwitchLabel
        )
    }
}