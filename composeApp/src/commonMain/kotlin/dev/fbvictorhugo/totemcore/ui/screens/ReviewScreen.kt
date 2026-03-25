package dev.fbvictorhugo.totemcore.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Adjust
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.fbvictorhugo.totemcore.model.PersonalData
import dev.fbvictorhugo.totemcore.ui.components.CommonForm
import dev.fbvictorhugo.totemcore.ui.components.FormButtons
import dev.fbvictorhugo.totemcore.ui.theme.AppTheme
import dev.fbvictorhugo.totemcore.ui.theme.Dimens
import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationEvent
import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationUiState
import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationViewModel
import org.jetbrains.compose.resources.stringResource
import totemcore.composeapp.generated.resources.Res
import totemcore.composeapp.generated.resources.button_conclude
import totemcore.composeapp.generated.resources.field_email
import totemcore.composeapp.generated.resources.field_name
import totemcore.composeapp.generated.resources.field_neighborhood
import totemcore.composeapp.generated.resources.field_observations
import totemcore.composeapp.generated.resources.field_observations_hint
import totemcore.composeapp.generated.resources.field_phone
import totemcore.composeapp.generated.resources.screen_interests_title
import totemcore.composeapp.generated.resources.screen_registration_title
import totemcore.composeapp.generated.resources.screen_review_subtitle
import totemcore.composeapp.generated.resources.screen_review_title

@Composable
fun ReviewScreen(
    modifier: Modifier = Modifier,
    sharedViewModel: SharedRegistrationViewModel,
    onNavigateBack: () -> Unit,
    onFinalize: () -> Unit,
) {
    val uiState by sharedViewModel.uiState.collectAsState()

    CommonForm(
        modifier = modifier,
        title = stringResource(Res.string.screen_review_title),
        subtitle = stringResource(Res.string.screen_review_subtitle),
        icon = rememberVectorPainter(image = Icons.Default.CheckCircle),
        stepPage = 0.9f
    ) {
        ReviewContent(
            uiState = uiState,
            onEvent = { event ->
                when (event) {
                    SharedRegistrationEvent.NextClicked -> onFinalize()
                    SharedRegistrationEvent.BackClicked -> onNavigateBack()
                    else -> sharedViewModel.onEvent(event)
                }
            }
        )
    }
}

@Composable
private fun ReviewContent(
    uiState: SharedRegistrationUiState,
    onEvent: (SharedRegistrationEvent) -> Unit
) {

    Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacerBetweenComponentes)) {
        PersonalContent(uiState.personalData)
        InterestContent(uiState.selectedInterests)
        ObservationsContent(
            value = uiState.observations,
            onValueChange = { onEvent(SharedRegistrationEvent.ObservationsChanged(it)) }
        )

        FormButtons(
            nextEnabled = true,
            onBackClick = { onEvent(SharedRegistrationEvent.BackClicked) },
            onNextClick = { onEvent(SharedRegistrationEvent.NextClicked) },
            nextText = stringResource(Res.string.button_conclude),
            nextIcon = Icons.AutoMirrored.Filled.Send
        )
    }
}

@Composable
private fun PersonalContent(data: PersonalData) {
    Surface(
        shape = RoundedCornerShape(Dimens.MessageSurface.Radius),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(Dimens.MessageSurface.Padding),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacerBetweenFields)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(Dimens.SpacerBetweenFields))
                Text(
                    text = stringResource(Res.string.screen_registration_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                val columns = if (maxWidth < 600.dp) 1 else 2

                val fields = listOf(
                    Triple(Icons.Default.Person, stringResource(Res.string.field_name), data.name),
                    Triple(Icons.Default.Phone, stringResource(Res.string.field_phone), data.phone),
                    Triple(
                        Icons.Default.LocationOn,
                        stringResource(Res.string.field_neighborhood),
                        data.neighborhood
                    ),
                    Triple(Icons.Default.Mail, stringResource(Res.string.field_email), data.email)
                )

                Column(verticalArrangement = Arrangement.spacedBy(Dimens.SpacerBetweenFields)) {
                    fields.chunked(columns).forEach { rowFields ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(Dimens.SpacerBetweenFields)
                        ) {
                            rowFields.forEach { field ->
                                ReviewField(
                                    icon = field.first,
                                    label = field.second,
                                    value = field.third,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                            if (rowFields.size < columns) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun InterestContent(selectedInterests: List<String>) {
    Surface(
        shape = RoundedCornerShape(Dimens.MessageSurface.Radius),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(Dimens.MessageSurface.Padding),
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacerBetweenFields)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Adjust,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(Dimens.SpacerBetweenFields))
                Text(
                    text = stringResource(Res.string.screen_interests_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                selectedInterests.forEach { interest ->
                    Surface(
                        //  color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(Dimens.Button.Corner),
                        border = BorderStroke(
                            1.dp,
                            MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = interest,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            }

        }
    }
}

@Composable
private fun ObservationsContent(
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(Res.string.field_observations),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            placeholder = {
                Text(
                    stringResource(Res.string.field_observations_hint),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline
                )
            },
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            )
        )
    }
}

@Composable
private fun ReviewField(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview
@Composable
fun ReviewScreenPreview() {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ReviewScreen(
                sharedViewModel = SharedRegistrationViewModel(),
                onNavigateBack = {},
                onFinalize = {}
            )
        }
    }
}
