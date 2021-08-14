package com.reha.casestudy.feature.jetpackcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val JetchatDarkPalette = darkColors(
    primary = Blue200,
    primaryVariant = Blue400,
    onPrimary = Color.Black,
    secondary = Yellow400,
    onSecondary = Color.Black,
    onSurface = Color.White,
    onBackground = Color.White,
    error = Red300,
    onError = Color.Black
)

private val JetchatLightPalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue800,
    onPrimary = Color.White,
    secondary = Yellow700,
    secondaryVariant = Yellow800,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    onBackground = Color.Black,
    error = Red800,
    onError = Color.White
)

@Composable
fun GithubCaseStudyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: Colors? = null,
    content: @Composable() () -> Unit
) {
    val myColors = colors ?: if (darkTheme) JetchatDarkPalette else JetchatLightPalette

    MaterialTheme(
        colors = myColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}