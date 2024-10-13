package com.example.therickestapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun TheRickestAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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

data class Pallet(
    val primaryTextColor: Color,
    val secondaryTextColor: Color,
    val backgroundColor: Color,
    val buttonBackgroundColor: Color,
    val characterItemBackgroundColor: Color
)

val LocalPallet = staticCompositionLocalOf {
    getPalletColor()
}

fun getPalletColor(
    isDarkTheme: Boolean = false
): Pallet {
    return if(isDarkTheme) Pallet(
        primaryTextColor =  darkPallet.primaryTextColor,
        secondaryTextColor = darkPallet.secondaryTextColor,
        backgroundColor = darkPallet.backgroundColor,
        buttonBackgroundColor = darkPallet.buttonBackgroundColor,
        characterItemBackgroundColor = darkPallet.characterItemBackgroundColor
    ) else Pallet(
        primaryTextColor =  lightPallet.primaryTextColor,
        secondaryTextColor = lightPallet.secondaryTextColor,
        backgroundColor = lightPallet.backgroundColor,
        buttonBackgroundColor = lightPallet.buttonBackgroundColor,
        characterItemBackgroundColor = lightPallet.characterItemBackgroundColor
    )
}