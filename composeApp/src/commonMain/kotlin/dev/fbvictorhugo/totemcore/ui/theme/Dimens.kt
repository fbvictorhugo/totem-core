package dev.fbvictorhugo.totemcore.ui.theme

import androidx.compose.ui.unit.dp

object Dimens {

    private val SpacingNano = 4.dp
    private val SpacingSmall = 8.dp
    private val SpacingMedium = 16.dp
    private val SpacingLarge = 24.dp
    private val SpacingExtraLarge = 32.dp
    private val SpacingHuge = 48.dp
    private val RadiusNano = 4.dp

    private val RadiusSmall = 8.dp
    private val RadiusMedium = 16.dp
    private val RadiusLarge = 24.dp
    private val RadiusExtraLarge = 28.dp

    val SpacerBetweenFields = SpacingMedium
    val SpacerBetweenComponentes = SpacingLarge

    object MessageSurface {
        val Radius = RadiusSmall
        val Padding = SpacingMedium
    }

    object Button {
        val Height = 60.dp
        val Corner = RadiusMedium
    }

}