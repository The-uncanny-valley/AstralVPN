package com.uncannyvalley.astralvpn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.uncannyvalley.astralvpn.presentation.navigation.AstralNavHost
import com.uncannyvalley.astralvpn.presentation.theme.AstralVPNTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AstralVPNTheme {
                AstralNavHost()
            }
        }
    }
}