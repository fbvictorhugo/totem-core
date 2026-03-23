package dev.fbvictorhugo.totemcore.ui.screens.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.fbvictorhugo.totemcore.ui.components.CommonForm
import dev.fbvictorhugo.totemcore.ui.components.FormButtons
import dev.fbvictorhugo.totemcore.ui.theme.AppTheme
import dev.fbvictorhugo.totemcore.ui.theme.Dimens
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import totemcore.composeapp.generated.resources.Res
import totemcore.composeapp.generated.resources.app_name
import totemcore.composeapp.generated.resources.app_subtitle
import totemcore.composeapp.generated.resources.estimated_time
import totemcore.composeapp.generated.resources.icon_totem
import totemcore.composeapp.generated.resources.start_registration
import totemcore.composeapp.generated.resources.welcome_message

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel { HomeViewModel() }
) {
    CommonForm(
        modifier = modifier,
        title = stringResource(Res.string.app_name),
        subtitle = stringResource(Res.string.app_subtitle),
        icon = painterResource(Res.drawable.icon_totem)
    ) {
        HomeContent(onEvent = homeViewModel::onEvent)
    }
}

@Composable
private fun HomeContent(onEvent: (HomeEvent) -> Unit) {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        shape = RoundedCornerShape(Dimens.MessageSurface.Radius),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(Res.string.welcome_message),
            modifier = Modifier.padding(Dimens.MessageSurface.Padding),
            style = MaterialTheme.typography.bodyLarge.copy(
                lineHeight = 26.sp,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            textAlign = TextAlign.Center
        )
    }

    Spacer(modifier = Modifier.height(Dimens.SpacerBetweenComponentes))

    FormButtons(
        nextEnabled = true,
        onBackClick = null,
        onNextClick = { onEvent(HomeEvent.StartRegistrationClicked) },
        nextText = stringResource(Res.string.start_registration),
    )

    Spacer(modifier = Modifier.height(Dimens.SpacerBetweenFields))

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
