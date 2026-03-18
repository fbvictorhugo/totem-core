package dev.fbvictorhugo.totemcore.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ForwardToInbox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.fbvictorhugo.totemcore.ui.theme.AppTheme
import dev.fbvictorhugo.totemcore.ui.theme.Dimens
import org.jetbrains.compose.resources.stringResource
import totemcore.composeapp.generated.resources.Res
import totemcore.composeapp.generated.resources.button_back
import totemcore.composeapp.generated.resources.button_forward

@Composable
fun FormButtons(
    modifier: Modifier = Modifier,
    nextEnabled: Boolean = true,
    onBackClick: (() -> Unit)?,
    onNextClick: (() -> Unit)?,
    nextText: String = stringResource(Res.string.button_forward),
    nextIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowForward

) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.SpacerBetweenFields)
    ) {
        onBackClick?.let {
            OutlinedButton(
                modifier = Modifier.height(Dimens.Button.Height).weight(1f),
                onClick = it,
                shape = RoundedCornerShape(Dimens.Button.Corner)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    Text(stringResource(Res.string.button_back))
                }
            }
        }

        onNextClick?.let {
            Button(
                modifier = Modifier.height(Dimens.Button.Height).weight(1f),
                onClick = it,
                enabled = nextEnabled,
                shape = RoundedCornerShape(Dimens.Button.Corner),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = nextText,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Icon(imageVector = nextIcon, contentDescription = null)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {
    AppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(40.dp)
        ) {
            FormButtons(
                nextEnabled = false,
                onBackClick = { },
                onNextClick = {},
                nextText = "Vai",
                nextIcon = Icons.AutoMirrored.Filled.ForwardToInbox
            )
        }
    }
}
