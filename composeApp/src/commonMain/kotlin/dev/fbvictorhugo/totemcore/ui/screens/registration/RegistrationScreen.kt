package dev.fbvictorhugo.totemcore.ui.screens.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.fbvictorhugo.totemcore.ui.components.CommonForm
import dev.fbvictorhugo.totemcore.ui.components.FormButtons
import dev.fbvictorhugo.totemcore.ui.theme.AppTheme
import dev.fbvictorhugo.totemcore.ui.theme.Dimens
import org.jetbrains.compose.resources.stringResource
import totemcore.composeapp.generated.resources.Res
import totemcore.composeapp.generated.resources.field_email
import totemcore.composeapp.generated.resources.field_name
import totemcore.composeapp.generated.resources.field_neighborhood
import totemcore.composeapp.generated.resources.field_phone
import totemcore.composeapp.generated.resources.screen_registration_subtitle
import totemcore.composeapp.generated.resources.screen_registration_title

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    registrationViewModel: RegistrationViewModel = viewModel { RegistrationViewModel() }
) {
    val registrationUiState by registrationViewModel.uiState.collectAsState()

    CommonForm(
        modifier = modifier,
        title = stringResource(Res.string.screen_registration_title),
        subtitle = stringResource(Res.string.screen_registration_subtitle),
        icon = rememberVectorPainter(image = Icons.Default.Person),
        stepPage = 0.3f
    ) {
        RegistrationContent(
            uiState = registrationUiState,
            onEvent = { registrationViewModel.onEvent(it) }
        )
    }
}

@Composable
private fun RegistrationContent(
    modifier: Modifier = Modifier,
    uiState: RegistrationUiState,
    onEvent: (RegistrationEvent) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val firstFieldFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        firstFieldFocusRequester.requestFocus()
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimens.SpacerBetweenFields)
    ) {
        OutlinedTextField(
            value = uiState.name,
            onValueChange = { onEvent(RegistrationEvent.NameChanged(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(firstFieldFocusRequester),
            label = { RequiredLabel(stringResource(Res.string.field_name)) },
            leadingIcon = { Icon(Icons.Default.Person, null) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
            singleLine = true
        )

        OutlinedTextField(
            value = uiState.phone,
            onValueChange = { onEvent(RegistrationEvent.PhoneChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            label = { RequiredLabel(stringResource(Res.string.field_phone)) },
            leadingIcon = { Icon(Icons.Default.Phone, null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
            singleLine = true
        )

        OutlinedTextField(
            value = uiState.neighborhood,
            onValueChange = { onEvent(RegistrationEvent.NeighborhoodChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(Res.string.field_neighborhood)) },
            leadingIcon = { Icon(Icons.Default.LocationOn, null) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Next) }),
            singleLine = true
        )

        OutlinedTextField(
            value = uiState.email,
            onValueChange = { onEvent(RegistrationEvent.EmailChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(Res.string.field_email)) },
            leadingIcon = { Icon(Icons.Default.Mail, null) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(Dimens.SpacerBetweenComponentes))

        FormButtons(
            nextEnabled = uiState.isFormValid,
            onBackClick = { onEvent(RegistrationEvent.BackClicked) },
            onNextClick = { onEvent(RegistrationEvent.NextClicked) },
        )
    }
}

@Composable
private fun RequiredLabel(text: String) {
    Text(buildAnnotatedString {
        append(text)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.error)) {
            append(" *")
        }
    })
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            RegistrationContent(
                uiState = RegistrationUiState(name = "John Doe"),
                onEvent = {}
            )
        }
    }
}
