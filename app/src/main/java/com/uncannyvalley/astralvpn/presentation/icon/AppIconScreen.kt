package com.uncannyvalley.astralvpn.presentation.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uncannyvalley.astralvpn.R
import com.uncannyvalley.astralvpn.domain.model.AppIcon
import com.uncannyvalley.astralvpn.presentation.settings.mapper.toUiModel
import com.uncannyvalley.astralvpn.presentation.theme.AstralVPNTheme

@Composable
fun AppIconScreen(
    iconOptions: List<AppIconOption>,
    selectedIcon: AppIcon,
    onIconSelected: (AppIcon) -> Unit,
    onBack: () -> Unit
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.background_stars),
                    contentScale = ContentScale.FillHeight
                )
                .padding(padding) // ?
        ) {
            // glass
            Box(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 112.dp, bottom = 86.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
                    .paint(
                        painterResource(id = R.drawable.background_glass),
                        contentScale = ContentScale.FillBounds
                    ),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(16.dp))
                        .padding(horizontal = 42.dp, vertical = 48.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Icon(
                        painter = painterResource(R.drawable.btn_return),
                        contentDescription = "Return",
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(bounded = false),
                                onClick = onBack
                            )
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Text(
                        text = stringResource(id = R.string.icon_title),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = stringResource(id = R.string.icon_text),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )

                    Spacer(modifier = Modifier.height(58.dp))

                    AppIconPicker(
                        options = iconOptions,
                        selectedIcon = selectedIcon,
                        onSelectedChanged = onIconSelected
                    )
                }
            }
        }
    }
}

@Composable
fun AppIconItem(
    option: AppIconOption,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .selectable(
                selected = selected,
                onClick = onSelect,
                role = Role.RadioButton
            )
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .border(
                    width = 1.dp,
                    color = if (selected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(option.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        RadioButton(
            selected = selected,
            onClick = onSelect
        )
    }
}

@Composable
fun AppIconPicker(
    options: List<AppIconOption>,
    selectedId: String,
    onSelectedChanged: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier
            .selectableGroup()
            .wrapContentHeight()
    ) {
        items(options) { option ->
            AppIconItem(
                option = option,
                selected = option.id == selectedId,
                onSelect = { onSelectedChanged(option.id) }
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun AppIconScreenPreview() {
    AstralVPNTheme(darkTheme = true) {
        AppIconScreen(
            onBack = {},
            iconOptions = AppIcon.entries.map { it.toUiModel() },
            selectedIcon = AppIcon.DEFAULT,
            onIconSelected = {}
        )
    }
}