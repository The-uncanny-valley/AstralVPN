package com.uncannyvalley.astralvpn.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uncannyvalley.astralvpn.R
import com.uncannyvalley.astralvpn.R.string.app_icon
import com.uncannyvalley.astralvpn.R.string.help
import com.uncannyvalley.astralvpn.R.string.profile
import com.uncannyvalley.astralvpn.R.string.split_tunneling
import com.uncannyvalley.astralvpn.R.string.theme
import com.uncannyvalley.astralvpn.presentation.home.BottomNavBar
import com.uncannyvalley.astralvpn.presentation.navigation.Screen
import com.uncannyvalley.astralvpn.presentation.theme.AstralVPNTheme
import com.uncannyvalley.astralvpn.presentation.theme.LightBackgroundGradient

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateHelp: () -> Unit,
    onNavigateAppIcon: () -> Unit,
    onNavigateProfile: () -> Unit
) {
    val isDarkTheme =
        MaterialTheme.colorScheme.background.luminance() < 0.5f

    Scaffold(
        bottomBar = {
            BottomNavBar(
                current = Screen.SettingsScreen.route,
                onHomeClick = onNavigateHome,
                onSettingsClick = {}
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (isDarkTheme) {
                        Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .paint(
                                painterResource(id = R.drawable.background_stars),
                                contentScale = ContentScale.FillHeight
                            )
                    } else {
                        Modifier
                            .background(LightBackgroundGradient)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            val configuration = LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp
            val screenWidth = configuration.screenWidthDp.dp

            Box(
                modifier = Modifier
                    .padding(
                        start = screenWidth * 0.06f,
                        end = screenWidth * 0.06f,
                        top = screenHeight * 0.12f,
                        bottom = screenHeight * 0.1f
                    )
                    .fillMaxSize()
                    .padding(padding)
                    .paint(
                        painterResource(id = R.drawable.background_glass),
                        contentScale = ContentScale.FillBounds
                    )
                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 48.dp, vertical = 48.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    val settingsActions = mapOf(
                        profile to onNavigateProfile,
                        split_tunneling to {},
                        theme to {},
                        app_icon to onNavigateAppIcon,
                        help to onNavigateHelp
                    )

                    settingsActions.forEach { (id, action) ->
                        SettingsRow(
                            title = stringResource(id),
                            onClick = action
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SettingsScreenPreview() {
    AstralVPNTheme(darkTheme = true) {
        SettingsScreen(
            onBack = {},
            onNavigateHome = {},
            onNavigateHelp = {},
            onNavigateAppIcon = {},
            onNavigateProfile = {}
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SettingsScreenPreview_Light() {
    AstralVPNTheme(darkTheme = false) {
        SettingsScreen(
            onBack = {},
            onNavigateHome = {},
            onNavigateHelp = {},
            onNavigateAppIcon = {},
            onNavigateProfile = {}
        )
    }
}