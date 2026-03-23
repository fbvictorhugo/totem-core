package dev.fbvictorhugo.totemcore

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.fbvictorhugo.totemcore.ui.screens.interests.InterestsScreen
import dev.fbvictorhugo.totemcore.ui.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        InterestsScreen(modifier = Modifier)
    }
}