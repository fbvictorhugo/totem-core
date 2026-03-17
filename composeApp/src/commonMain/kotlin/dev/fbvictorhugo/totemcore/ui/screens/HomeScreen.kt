package dev.fbvictorhugo.totemcore.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fbvictorhugo.totemcore.ui.components.CommonForm
import dev.fbvictorhugo.totemcore.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import totemcore.composeapp.generated.resources.Res
import totemcore.composeapp.generated.resources.app_name
import totemcore.composeapp.generated.resources.app_subtitle
import totemcore.composeapp.generated.resources.compose_multiplatform
import totemcore.composeapp.generated.resources.estimated_time
import totemcore.composeapp.generated.resources.start_registration
import totemcore.composeapp.generated.resources.welcome_message

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    CommonForm(
        modifier = modifier,
        title = stringResource(Res.string.app_name),
        subtitle = stringResource(Res.string.app_subtitle),
        icon = painterResource(Res.drawable.compose_multiplatform)
    ) {
        HomeContent()
    }
}

@Composable
fun HomeContent() {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(Res.string.welcome_message),
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 26.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            textAlign = TextAlign.Center
        )
    }

    Spacer(modifier = Modifier.height(56.dp))

    Button(
        onClick = { /* TODO: Iniciar Cadastro */ },
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(0.8f)
            .widthIn(min = 250.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = stringResource(Res.string.start_registration),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null
            )
        }
    }

    Spacer(modifier = Modifier.height(40.dp))

    Text(
        text = stringResource(Res.string.estimated_time),
        style = MaterialTheme.typography.labelLarge.copy(
            color = MaterialTheme.colorScheme.outline,
            fontWeight = FontWeight.Medium
        )
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            HomeScreen()
        }
    }
}