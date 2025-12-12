package com.uncannyvalley.astralvpn.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable

@Composable
fun AstralNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        composable(Screen.HomeScreen.route) {
            HomeRoute(
                onNavigateSettings = { navController.navigate(Screen.SettingsScreen.route) }
            )
        }
        composable(Screen.SettingsScreen.route) {
            SettingsRoute(
                onBack = { navController.popBackStack() },
                onNavigateHome = { navController.navigate(Screen.HomeScreen.route) },
                onNavigateHelp = { navController.navigate(Screen.HelpScreen.route) },
                onNavigateAppIcon = { navController.navigate(Screen.AppIconScreen.route) },
                onNavigateProfile = { navController.navigate(Screen.ProfileScreen.route) }
            )
        }
        composable(Screen.HelpScreen.route) {
            HelpRoute(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.AppIconScreen.route) {
            AppIconRoute(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.ProfileScreen.route) {
            ProfileRoute(
                onBack = { navController.popBackStack() },
                onEditButtonClick = { TODO() },
                onGetPremiumClick = { navController.navigate(Screen.RegisterScreen.route) }
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterRoute(
                onRegisterSuccess = {
                    navController.navigate(Screen.HomeScreen.route) { // NOT HOME!
                        popUpTo(Screen.RegisterScreen.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.SuccessScreen.route) {
            SuccessRoute(
                onFinished = {
                    navController.navigate(Screen.VerificationScreen.route) {
                        popUpTo(Screen.SuccessScreen.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.VerificationScreen.route) {
            VerificationRoute(
                onVerified = {
                    navController.navigate(Screen.VerificationScreen.route) {
                        popUpTo(Screen.VerificationScreen.route) {
                            inclusive = true
                        }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}