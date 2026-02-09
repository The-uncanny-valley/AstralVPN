package com.uncannyvalley.astralvpn.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Puce,
    onPrimary = SilverChalice,
    background = EerieBlack,
    onBackground = White,
    surface = SonicSilver,
    primaryContainer = Purple,
    onPrimaryContainer = White,
    secondaryContainer = Nero,
    onSecondaryContainer = White,
    outline = SteelDust,
    outlineVariant = Cyan,
    error = LazerScarlet,
    errorContainer = SonicSilver,
    onErrorContainer = LazerScarlet,
    onSurface = MountbattenPink,
    onSurfaceVariant = GullGray,
    secondary = MagentaRed,
    onSecondary = White,
    tertiary = Purple
)

private val LightColorScheme = lightColorScheme(
    primary = Puce,
    onBackground = White,
    onSurface = Puce
)

@Composable
fun AstralVPNTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}