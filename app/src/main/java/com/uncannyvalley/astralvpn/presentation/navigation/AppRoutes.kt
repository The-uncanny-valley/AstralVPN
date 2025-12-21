package com.uncannyvalley.astralvpn.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.uncannyvalley.astralvpn.presentation.auth.login.LoginScreen
import com.uncannyvalley.astralvpn.presentation.auth.login.LoginViewModel
import com.uncannyvalley.astralvpn.presentation.auth.register.RegisterScreen
import com.uncannyvalley.astralvpn.presentation.auth.register.RegisterViewModel
import com.uncannyvalley.astralvpn.presentation.auth.register.SuccessScreen
import com.uncannyvalley.astralvpn.presentation.auth.register.VerificationEvent
import com.uncannyvalley.astralvpn.presentation.auth.register.VerificationScreen
import com.uncannyvalley.astralvpn.presentation.auth.register.VerificationViewModel
import com.uncannyvalley.astralvpn.presentation.home.HomeScreen
import com.uncannyvalley.astralvpn.presentation.home.HomeUiState
import com.uncannyvalley.astralvpn.presentation.home.HomeViewModel
import com.uncannyvalley.astralvpn.presentation.profile.ProfileViewModel
import com.uncannyvalley.astralvpn.presentation.screen.AppIconScreen
import com.uncannyvalley.astralvpn.presentation.screen.HelpScreen
import com.uncannyvalley.astralvpn.presentation.screen.ProfileScreen
import com.uncannyvalley.astralvpn.presentation.settings.SettingsScreen
import kotlinx.coroutines.delay

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home")
    object SettingsScreen : Screen("settings")
    object HelpScreen : Screen("help")
    object AppIconScreen : Screen("icon")
    object ProfileScreen : Screen("profile")
    object RegisterScreen : Screen("register")
    object VerificationScreen : Screen("verification?email={email}") {
        fun createRoute(email: String) = "verification?email=$email"
    }
    object LoginScreen : Screen("login")

    object SuccessScreen : Screen("success")
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

@Composable
fun RegisterRoute(
    onBack: () -> Unit,
    onLoginClick: () -> Unit,
    onContinueWithoutRegistration: () -> Unit,
    onRegisterSuccess: (String) -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    RegisterScreen(
        viewModel = viewModel,
        onRegisterSuccess = {
            onRegisterSuccess(viewModel.uiState.value.email)
        },
        onBack = onBack,
        onLoginClick = onLoginClick,
        onContinueWithoutRegistration = onContinueWithoutRegistration
    )
}

@Composable
fun LoginRoute(
    onBack: () -> Unit,
    onRegisterClick: () -> Unit,
    onContinueWithoutRegistration: () -> Unit,
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    LoginScreen(
        viewModel = viewModel,
        onLoginSuccess = { onLoginSuccess() },
        onBack = onBack,
        onContinueWithoutRegistration = onContinueWithoutRegistration,
        onRegisterClick = onRegisterClick
    )
}

@Composable
fun VerificationRoute(
    email: String,
    onBack: () -> Unit,
    viewModel: VerificationViewModel = hiltViewModel(),
    onVerified: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(viewModel.events) {
        viewModel.events.collect { event ->
            if (event is VerificationEvent.Success) {
                onVerified()
            }
        }
    }

    VerificationScreen(
        uiState = uiState,
        email = email,
        onCodeChange = viewModel::onCodeChange,
        onVerifyClick = viewModel::onVerifyClick,
        onBack = onBack
    )
}

@Composable
fun SuccessRoute(
    onFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(1500)
        onFinished()
    }

    SuccessScreen()
}