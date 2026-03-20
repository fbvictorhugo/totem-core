package dev.fbvictorhugo.totemcore

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import totemcore.composeapp.generated.resources.Res
import totemcore.composeapp.generated.resources.app_name
import totemcore.composeapp.generated.resources.icon_totem

fun main() = application {
    val windowState = rememberWindowState(placement = WindowPlacement.Maximized)

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = stringResource(Res.string.app_name),
        icon = painterResource(Res.drawable.icon_totem)
    ) {
        App()
    }
}