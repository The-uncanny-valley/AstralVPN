package com.uncannyvalley.astralvpn.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uncannyvalley.astralvpn.R
import com.uncannyvalley.astralvpn.presentation.navigation.Screen
import com.uncannyvalley.astralvpn.presentation.theme.AstralVPNTheme

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onMainButtonClick: () -> Unit,
    onNavigateSettings: () -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(
                current = Screen.HomeScreen.route,
                onHomeClick = { /* already here */ },
                onSettingsClick = onNavigateSettings
            )
        },
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
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            MainButton(
                uiState = uiState,
                onClick = onMainButtonClick
            )
        }

    }
}

@Composable
fun MainButton(
    uiState: HomeUiState,
    onClick: () -> Unit
) {
    val icon = when (uiState) {
        is HomeUiState.Normal -> R.drawable.btn_on
        is HomeUiState.Connected -> R.drawable.ic_connected
        is HomeUiState.Connecting -> R.drawable.ic_connecting
        is HomeUiState.NoInternet -> R.drawable.ic_no_internet
        is HomeUiState.Error -> R.drawable.ic_error
    }
    val painter = painterResource(id = icon)

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .aspectRatio(1f),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RectangleShape,
        elevation = null
    ) {
        Image(
            painter = painter,
            contentDescription = "Main action",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Inside
        )
    }
}

@Composable
fun BottomNavBar(
    current: String,
    onHomeClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 52.dp, end = 92.dp, start = 92.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val homeSelected = current == Screen.HomeScreen.route
        val settingsSelected = current == Screen.SettingsScreen.route

        val interactionSource = remember { MutableInteractionSource() }

        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(RectangleShape)
                .clickable(
                    indication = ripple(bounded = false),
                    interactionSource = interactionSource,
                    onClick = onHomeClick
                ),
            contentAlignment = Alignment.Center
        ) {
            val icon = if (homeSelected) R.drawable.ic_home_selected else R.drawable.ic_home_unselected
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.fillMaxSize()
            )
        }

        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(RectangleShape)
                .clickable(onClick = onSettingsClick),
            contentAlignment = Alignment.Center
        ) {
            val icon = if (settingsSelected) R.drawable.ic_settings_selected else R.drawable.ic_settings_unselected
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(
    name = "Home Screen - Normal",
    showBackground = true
)
@Composable
fun HomeScreenPreview_Normal() {
    AstralVPNTheme(darkTheme = true) {
        HomeScreen(
            uiState = HomeUiState.Normal,
            onMainButtonClick = {},
            onNavigateSettings = {}
        )
    }
}

@Preview(
    name = "Home Screen - Connecting",
    showBackground = true
)
@Composable
fun HomeScreenPreview_Connecting() {
    AstralVPNTheme(darkTheme = true) {
        HomeScreen(
            uiState = HomeUiState.Connecting,
            onMainButtonClick = {},
            onNavigateSettings = {}
        )
    }
}

@Preview(
    name = "Home Screen - Connected",
    showBackground = true
)
@Composable
fun HomeScreenPreview_Connected() {
    AstralVPNTheme(darkTheme = true) {
        HomeScreen(
            uiState = HomeUiState.Connected,
            onMainButtonClick = {},
            onNavigateSettings = {}
        )
    }
}

@Preview(
    name = "Home Screen - Error",
    showBackground = true
)
@Composable
fun HomeScreenPreview_Error() {
    AstralVPNTheme(darkTheme = true) {
        HomeScreen(
            uiState = HomeUiState.Error("E"),
            onMainButtonClick = {},
            onNavigateSettings = {}
        )
    }
}

@Preview(
    name = "Home Screen - NoInternet",
    showBackground = true
)
@Composable
fun HomeScreenPreview_NoInternet() {
    AstralVPNTheme(darkTheme = true) {
        HomeScreen(
            uiState = HomeUiState.NoInternet,
            onMainButtonClick = {},
            onNavigateSettings = {}
        )
    }
}