package dev.fbvictorhugo.totemcore.ui.screens.review

import dev.fbvictorhugo.totemcore.model.PersonalData

data class ReviewUiState(
    val observations: String = "",
    val selectedInterests: List<String> = emptyList(),
    val personalData: PersonalData = PersonalData()
)