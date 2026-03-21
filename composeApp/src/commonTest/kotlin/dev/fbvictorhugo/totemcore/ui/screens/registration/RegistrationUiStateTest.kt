package dev.fbvictorhugo.totemcore.ui.screens.registration

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegistrationUiStateTest {

    @Test
    fun `isFormValid It should return FALSE when required fields are empty`() {
        val state = RegistrationUiState(name = "", phone = "")
        assertFalse(state.isFormValid)
    }

    @Test
    fun `isFormValid It should return FALSE when only the name is filled in`() {
        val state = RegistrationUiState(name = "Victor", phone = "")
        assertFalse(state.isFormValid)
    }

    @Test
    fun `isFormValid It should return FALSE when only the phone number is filled in`() {
        val state = RegistrationUiState(name = "", phone = "3299999999")
        assertFalse(state.isFormValid)
    }

    @Test
    fun `isFormValid It should return TRUE when name and phone number are filled in`() {
        val state = RegistrationUiState(name = "Victor", phone = "3299999999")
        assertTrue(state.isFormValid)
    }
}