package dev.fbvictorhugo.totemcore.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.fbvictorhugo.totemcore.ui.components.CommonForm
import dev.fbvictorhugo.totemcore.ui.components.DrawProgressButton
import dev.fbvictorhugo.totemcore.ui.theme.AppTheme
import dev.fbvictorhugo.totemcore.ui.theme.Dimens
import dev.fbvictorhugo.totemcore.viewmodel.ConclusionEvent
import dev.fbvictorhugo.totemcore.viewmodel.ConclusionUiState
import dev.fbvictorhugo.totemcore.viewmodel.ConclusionViewModel
import org.jetbrains.compose.resources.stringResource
import totemcore.composeapp.generated.resources.Res
import totemcore.composeapp.generated.resources.button_ending_in
import totemcore.composeapp.generated.resources.final_message
import totemcore.composeapp.generated.resources.screen_conclusion_subtitle
import totemcore.composeapp.generated.resources.screen_conclusion_title

@Composable
fun ConclusionScreen(
    modifier: Modifier = Modifier,
    conclusionViewModel: ConclusionViewModel = viewModel { ConclusionViewModel() },
    onConclude: () -> Unit
) {
    val uiState by conclusionViewModel.uiState.collectAsState()

    CommonForm(
        modifier = modifier,
        title = stringResource(Res.string.screen_conclusion_title),
        subtitle = stringResource(Res.string.screen_conclusion_subtitle),
        rememberVectorPainter(image = Icons.Default.CheckCircle),
        boxIconColor = AppTheme.colors.greenCheck,
    ) {
        ConclusionContent(
            onEvent = { event ->
                if (event is ConclusionEvent.ConclusionClicked) {
                    onConclude()
                }
                conclusionViewModel.onEvent(event)
            },
            uiState = uiState
        )

        LaunchedEffect(uiState.isFinished) {
            if (uiState.isFinished) {
                onConclude()
            }
        }
    }
}

@Composable
private fun ConclusionContent(
    onEvent: (ConclusionEvent) -> Unit,
    uiState: ConclusionUiState
) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(Dimens.MessageSurface.Radius),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(Res.string.final_message),
            modifier = Modifier.padding(Dimens.MessageSurface.Padding),
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 26.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            textAlign = TextAlign.Center
        )
    }

    Spacer(modifier = Modifier.height(Dimens.SpacerBetweenComponentes))
    DrawProgressButton(
        text = stringResource(Res.string.button_ending_in, uiState.remaining),
        onClick = { onEvent(ConclusionEvent.ConclusionClicked) },
        progress = uiState.progress,
        progressColor = MaterialTheme.colorScheme.secondary
    )
}

@Preview
@Composable
fun ConclusionScreenPreview() {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ConclusionScreen(onConclude = { })
        }
    }
}
