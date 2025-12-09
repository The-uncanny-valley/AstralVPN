package com.uncannyvalley.astralvpn.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.uncannyvalley.astralvpn.presentation.home.HomeScreen
import com.uncannyvalley.astralvpn.presentation.home.HomeUiState
import com.uncannyvalley.astralvpn.presentation.home.HomeViewModel
import com.uncannyvalley.astralvpn.presentation.profile.ProfileViewModel
import com.uncannyvalley.astralvpn.presentation.screen.AppIconScreen
import com.uncannyvalley.astralvpn.presentation.screen.HelpScreen
import com.uncannyvalley.astralvpn.presentation.screen.ProfileScreen
import com.uncannyvalley.astralvpn.presentation.settings.SettingsScreen

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object SettingsScreen : Screen("settings")
    object HelpScreen : Screen("help")
    object AppIconScreen : Screen("icon")
    object ProfileScreen : Screen("profile")
}

@Composable
fun SettingsRoute(
    onBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateHelp: () -> Unit,
    onNavigateAppIcon: () -> Unit,
    onNavigateProfile: () -> Unit
) {
    SettingsScreen(
        onBack = onBack,
        onNavigateHome = onNavigateHome,
        onNavigateHelp = onNavigateHelp,
        onNavigateAppIcon = onNavigateAppIcon,
        onNavigateProfile = onNavigateProfile
    )
}

@Composable
fun HomeRoute(
    onNavigateSettings: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    HomeScreen(
        uiState = uiState,
        onMainButtonClick = {
            when (uiState) {
                is HomeUiState.Normal -> viewModel.onConnectClicked()
                is HomeUiState.Connected,
                is HomeUiState.Connecting -> viewModel.onDisconnectClicked()
                is HomeUiState.Error -> viewModel.onDisconnectClicked()
                HomeUiState.NoInternet -> viewModel.refreshConnectionStatus()
            }
        },
        onNavigateSettings = onNavigateSettings
    )
}

@Composable
fun HelpRoute(
    onBack: () -> Unit
) {
    HelpScreen(onBack = onBack)
}

@Composable
fun AppIconRoute(
    onBack: () -> Unit
) {
    AppIconScreen(onBack = onBack)
}

@Composable
fun ProfileRoute(
    onBack: () -> Unit,
    onEditButtonClick: () -> Unit,
    onGetPremiumClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()

    ProfileScreen(
        uiState = uiState.value,
        onEditButtonClick = onEditButtonClick,
        onGetPremiumClick = onGetPremiumClick,
        onBack = onBack
    )
}