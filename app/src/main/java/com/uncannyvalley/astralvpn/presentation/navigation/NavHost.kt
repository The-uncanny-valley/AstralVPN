package com.uncannyvalley.astralvpn.presentation.navigation

import androidx.compose.runtime.Composable
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
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeRoute(
                onNavigateSettings = { navController.navigate(Screen.SettingsScreen.route) }
            )
        }
        composable(Screen.SettingsScreen.route) {
            SettingsRoute(
                onBack = { navController.popBackStack() }
            )
        }
    }
}