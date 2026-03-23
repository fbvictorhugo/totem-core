package dev.fbvictorhugo.totemcore

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.fbvictorhugo.totemcore.navigation.TotemNavHost
import dev.fbvictorhugo.totemcore.ui.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme {
        Scaffold { innerPadding ->
            TotemNavHost(modifier = Modifier.padding(innerPadding))
        }
    }
}