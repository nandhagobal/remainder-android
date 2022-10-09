package com.tw.remainder.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun RemainderTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = lightColors(primary = Color(0xFF0f9bba), primaryVariant = Color.Transparent),
        content = content
    )
}