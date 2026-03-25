package dev.fbvictorhugo.totemcore.ui.screens.review

import dev.fbvictorhugo.totemcore.model.PersonalData
import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationUiState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ReviewUiStateTest {

    @Test
    fun `should verify default values`() {
        val state = SharedRegistrationUiState()
        assertEquals("", state.observations)
        assertTrue(state.selectedInterests.isEmpty())
    }

    @Test
    fun `should verify if personal data is correctly assigned`() {
        val data = PersonalData(name = "Victor Hugo", phone = "32999999999")
        val state = SharedRegistrationUiState(personalData = data)
        assertEquals("Victor Hugo", state.personalData.name)
    }

    @Test
    fun `should verify if selected interests are correctly assigned`() {
        val interests = listOf("Fraldas", "Sofa")
        val state = SharedRegistrationUiState(selectedInterests = interests)
        assertEquals(2, state.selectedInterests.size)
    }
}
