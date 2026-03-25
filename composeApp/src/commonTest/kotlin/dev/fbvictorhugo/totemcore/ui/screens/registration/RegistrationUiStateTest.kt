package dev.fbvictorhugo.totemcore.ui.screens.registration

import dev.fbvictorhugo.totemcore.model.PersonalData
import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationUiState
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegistrationUiStateTest {

    @Test
    fun `isFormValid It should return FALSE when required fields are empty`() {
        val data = PersonalData(
            name = "", phone = "",
            neighborhood = "", email = "",
        )

        val state = SharedRegistrationUiState(data)
        assertFalse(state.isRegistrationValid)
    }

    @Test
    fun `isFormValid It should return FALSE when required fields are empty and other not empty`() {
        val data = PersonalData(
            name = "", phone = "",
            neighborhood = "Centro", email = "",
        )

        val state = SharedRegistrationUiState(data)
        assertFalse(state.isRegistrationValid)
    }

    @Test
    fun `isFormValid It should return FALSE when only the name is filled in`() {
        val data = PersonalData(name = "Victor", phone = "")

        val state = SharedRegistrationUiState(data)
        assertFalse(state.isRegistrationValid)
    }

    @Test
    fun `isFormValid It should return FALSE when only the phone number is filled in`() {
        val data = PersonalData(name = "", phone = "3299999999")

        val state = SharedRegistrationUiState(data)
        assertFalse(state.isRegistrationValid)
    }

    @Test
    fun `isFormValid It should return TRUE when name and phone number are filled in`() {

        val data = PersonalData(name = "Victor", phone = "3299999999")

        val state = SharedRegistrationUiState(data)
        assertTrue(state.isRegistrationValid)
    }
}