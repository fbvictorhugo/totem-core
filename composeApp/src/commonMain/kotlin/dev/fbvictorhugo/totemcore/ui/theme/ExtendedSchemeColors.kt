package dev.fbvictorhugo.totemcore.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColors(
    val greenCheck: Color
)

val LightExtendedColors = ExtendedColors(
    greenCheck = Color(0xFF13A368)
)

val DarkExtendedColors = ExtendedColors(
    greenCheck = Color(0xFF4CD18F)
)

val LocalExtendedColors = staticCompositionLocalOf {
    LightExtendedColors
}