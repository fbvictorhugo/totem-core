package dev.fbvictorhugo.totemcore.ui.screens.interests

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class InterestsViewModelTest {

    @Test
    fun `when InterestClicked event is received, selectedInterests is updated`() {

        val customInterests = listOf("Kotlin", "Java", "Python")
        val initialState = InterestsUiState(interests = customInterests)
        val viewModel = InterestsViewModel(initialState = initialState)

        val interestToSelect = "Kotlin"

        viewModel.onEvent(InterestsEvent.InterestClicked(interestToSelect))

        assertTrue(viewModel.uiState.value.selectedInterests.contains(interestToSelect))
        assertTrue(viewModel.uiState.value.isInterestsSelected)
        assertEquals(1, viewModel.uiState.value.selectedInterests.size)
    }

    @Test
    fun `when an already selected interest is clicked, it should be removed`() {
        val interest = "Kotlin"
        val initialState = InterestsUiState(
            interests = listOf(interest),
            selectedInterests = listOf(interest)
        )
        val viewModel = InterestsViewModel(initialState = initialState)

        viewModel.onEvent(InterestsEvent.InterestClicked(interest))

        assertFalse(viewModel.uiState.value.selectedInterests.contains(interest))
        assertFalse(viewModel.uiState.value.isInterestsSelected)
        assertTrue(viewModel.uiState.value.selectedInterests.isEmpty())
    }
}