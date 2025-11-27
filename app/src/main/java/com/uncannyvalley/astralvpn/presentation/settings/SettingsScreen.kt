package com.uncannyvalley.astralvpn.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uncannyvalley.astralvpn.R
import com.uncannyvalley.astralvpn.presentation.home.BottomNavBar
import com.uncannyvalley.astralvpn.presentation.navigation.Screen
import com.uncannyvalley.astralvpn.presentation.theme.AstralVPNTheme

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    onNavigateHome: () -> Unit
) {
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
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp)
                .paint(
                    painterResource(id = R.drawable.background_stars),
                    contentScale = ContentScale.FillHeight
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp, top = 112.dp, bottom = 160.dp)
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.background_glass),
                        contentScale = ContentScale.FillBounds
                    )
                    .clip(RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) { }
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
            onBack = {}
        ) { }
    }
}