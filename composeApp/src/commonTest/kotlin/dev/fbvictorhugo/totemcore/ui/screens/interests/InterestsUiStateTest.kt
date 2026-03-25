package dev.fbvictorhugo.totemcore.ui.screens.interests

import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationUiState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class InterestsUiStateTest {

    @Test
    fun `isInterestsSelected It should return FALSE when required fields are empty`() {
        val state = SharedRegistrationUiState()
        assertFalse(state.isInterestsSelected)
    }

    @Test
    fun `isInterestsSelected It should return TRUE when selectedInterests are valid`() {
        val state = SharedRegistrationUiState(
            selectedInterests = listOf("First, Second")
        )
        assertTrue(state.isInterestsSelected)
    }

    @Test
    fun `getMessage It should return SINGULAR when selectedInterests equals 1`() {
        val state = SharedRegistrationUiState(
            selectedInterests = listOf("First")
        )
        assertEquals("1 item", state.getMessage("item", "itens"))
    }

    @Test
    fun `getMessage It should return PLURAL when selectedInterests more then 1`() {
        val state = SharedRegistrationUiState(
            selectedInterests = listOf("First", "Second")
        )
        assertEquals("2 itens", state.getMessage("item", "itens"))
    }

}