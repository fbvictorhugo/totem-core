package dev.fbvictorhugo.totemcore.ui.screens.interests

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.fbvictorhugo.totemcore.ui.components.CommonForm
import dev.fbvictorhugo.totemcore.ui.components.FormButtons
import dev.fbvictorhugo.totemcore.ui.theme.AppTheme
import dev.fbvictorhugo.totemcore.ui.theme.Dimens
import org.jetbrains.compose.resources.stringResource
import totemcore.composeapp.generated.resources.Res
import totemcore.composeapp.generated.resources.screen_interests_subtitle
import totemcore.composeapp.generated.resources.screen_interests_title
import totemcore.composeapp.generated.resources.selected_plural
import totemcore.composeapp.generated.resources.selected_singular

@Composable
fun InterestsScreen(
    modifier: Modifier = Modifier,
    interestsViewModel: InterestsViewModel = viewModel { InterestsViewModel() },
    onNavigateToReview: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    val interestsUiState by interestsViewModel.uiState.collectAsState()

    CommonForm(
        modifier = modifier,
        title = stringResource(Res.string.screen_interests_title),
        subtitle = stringResource(Res.string.screen_interests_subtitle),
        icon = rememberVectorPainter(image = Icons.Default.Adjust),
        stepPage = 0.6f
    ) {
        InterestsContent(
            uiState = interestsUiState,
            onEvent = { event ->
                when (event) {
                    InterestsEvent.NextClicked -> onNavigateToReview()
                    InterestsEvent.BackClicked -> onNavigateBack()
                    else -> interestsViewModel.onEvent(event)
                }
            }
        )

    }
}

@Composable
private fun InterestsContent(
    modifier: Modifier = Modifier,
    uiState: InterestsUiState,
    onEvent: (InterestsEvent) -> Unit
) {

    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val columns = if (maxWidth < 600.dp) 2 else 3

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacerBetweenFields)
        ) {

            uiState.interests.chunked(columns).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.SpacerBetweenFields)
                ) {
                    rowItems.forEach { interest ->
                        val isSelected = uiState.selectedInterests.contains(interest)

                        InterestChipItem(
                            interest = interest, isSelected = isSelected,
                            onClick = { onEvent(InterestsEvent.InterestClicked(interest)) }
                        )
                    }

                    if (rowItems.size < columns) {
                        repeat(columns - rowItems.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            SelectedStatus(uiState)

            Spacer(modifier = Modifier.height(Dimens.SpacerBetweenComponentes))

            FormButtons(
                nextEnabled = uiState.isInterestsSelected,
                onBackClick = { onEvent(InterestsEvent.BackClicked) },
                onNextClick = { onEvent(InterestsEvent.NextClicked) }
            )
        }
    }
}

@Composable
private fun RowScope.InterestChipItem(
    interest: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        modifier = Modifier.weight(1f).height(Dimens.Button.Height),
        selected = isSelected,
        onClick = onClick,
        label = {
            Text(
                text = interest,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            )
        },
        leadingIcon = if (isSelected) {
            {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else null,
        shape = RoundedCornerShape(Dimens.Button.Corner),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.4f
            ),
            selectedLabelColor = MaterialTheme.colorScheme.primary,
            selectedLeadingIconColor = MaterialTheme.colorScheme.primary
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderColor = MaterialTheme.colorScheme.outlineVariant,
            selectedBorderColor = MaterialTheme.colorScheme.primary,
            selectedBorderWidth = 2.dp
        )
    )
}

@Composable
private fun SelectedStatus(uiState: InterestsUiState) {
    AnimatedVisibility(
        visible = uiState.isInterestsSelected,
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {

        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(Dimens.MessageSurface.Radius),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = uiState.getMessage(
                    singular = stringResource(Res.string.selected_singular),
                    plural = stringResource(Res.string.selected_plural)
                ),
                modifier = Modifier.padding(Dimens.MessageSurface.Padding),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun InterestsScreenPreview() {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            InterestsScreen(
                onNavigateBack = {},
                onNavigateToReview = {}
            )
        }
    }
}
