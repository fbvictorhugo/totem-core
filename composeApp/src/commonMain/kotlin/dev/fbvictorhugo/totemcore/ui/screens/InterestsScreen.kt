package dev.fbvictorhugo.totemcore.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.fbvictorhugo.totemcore.ui.components.CommonForm
import dev.fbvictorhugo.totemcore.ui.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import totemcore.composeapp.generated.resources.Res
import totemcore.composeapp.generated.resources.compose_multiplatform
import totemcore.composeapp.generated.resources.screen_interests_subtitle
import totemcore.composeapp.generated.resources.screen_interests_title

val interestsList = listOf(
    "Roupas (Adulto)", "Roupas Infantis", "Calçados",
    "Fraldas", "Cesta Básica", "Colchão", "Sofá", "Geladeira", "Fogão",
    "Móveis em Geral", "Eletrodomésticos", "Produtos de Higiene",
    "Água Potável", "Produtos de Limpeza", "Cobertores", "Material de Construção"
)

@Composable
fun InterestsScreen(modifier: Modifier = Modifier) {
    CommonForm(
        modifier = modifier,
        title = stringResource(Res.string.screen_interests_title),
        subtitle = stringResource(Res.string.screen_interests_subtitle),
        icon = painterResource(Res.drawable.compose_multiplatform),
        stepPage = 0.6f
    ) {
        InterestsContent(interestsList)
    }
}


@Composable
fun InterestsContent(interestsList: List<String>) {

    val selectedInterests = remember { mutableStateListOf<String>() }
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val columns = if (maxWidth < 600.dp) 2 else 3

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            interestsList.chunked(columns).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { interest ->
                        val isSelected = selectedInterests.contains(interest)

                        FilterChip(
                            modifier = Modifier
                                .weight(1f)
                                .height(64.dp),
                            selected = isSelected,
                            onClick = {
                                if (isSelected) selectedInterests.remove(interest)
                                else selectedInterests.add(interest)
                            },
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
                            shape = RoundedCornerShape(12.dp),
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

                    if (rowItems.size < columns) {
                        repeat(columns - rowItems.size) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            SelectedStatus(selectedInterests)

            Spacer(modifier = Modifier.height(12.dp))

            FormButtons(selectedInterests.isNotEmpty())
        }
    }
}

@Composable
private fun SelectedStatus(selectedInterests: SnapshotStateList<String>) {
    AnimatedVisibility(
        visible = selectedInterests.isNotEmpty(),
        enter = expandVertically() + fadeIn(),
        exit = shrinkVertically() + fadeOut()
    ) {

        Surface(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${selectedInterests.size} ${if (selectedInterests.size == 1) "Selecionado" else "Selecionados"}",
                modifier = Modifier.padding(12.dp),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.primary
                ),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun FormButtons(isFormValid: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(
            modifier = Modifier.height(60.dp).weight(1f),
            onClick = {/* TODO */ },
            shape = RoundedCornerShape(12.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                Text("Voltar")
            }
        }

        Button(
            modifier = Modifier.height(60.dp).weight(1f),
            onClick = { /* TODO */ },
            enabled = isFormValid,
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Próximo",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
fun InterestsScreenPreview() {
    AppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            InterestsScreen()
        }
    }
}
