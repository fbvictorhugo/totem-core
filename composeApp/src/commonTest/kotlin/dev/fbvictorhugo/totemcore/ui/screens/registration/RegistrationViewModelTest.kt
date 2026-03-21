package dev.fbvictorhugo.totemcore.ui.screens.registration

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegistrationViewModelTest {

    @Test
    fun `when NameChanged event is received, uiState name is updated`() {
        val viewModel = RegistrationViewModel()
        val newName = "Victor Hugo"

        viewModel.onEvent(RegistrationEvent.NameChanged(newName))

        assertEquals(newName, viewModel.uiState.value.name)
    }

    @Test
    fun `when PhoneChanged event is received, uiState phone is updated`() {
        val viewModel = RegistrationViewModel()
        val newPhone = "11999999999"

        viewModel.onEvent(RegistrationEvent.PhoneChanged(newPhone))

        assertEquals(newPhone, viewModel.uiState.value.phone)
    }

    @Test
    fun `when required fields are filled, isFormValid is true`() {
        val viewModel = RegistrationViewModel()

        viewModel.onEvent(RegistrationEvent.NameChanged("Victor"))
        viewModel.onEvent(RegistrationEvent.PhoneChanged("123456"))

        assertTrue(viewModel.uiState.value.isFormValid)
    }

    @Test
    fun `when required fields are empty, isFormValid is false`() {
        val viewModel = RegistrationViewModel()

        viewModel.onEvent(RegistrationEvent.NameChanged(""))
        viewModel.onEvent(RegistrationEvent.PhoneChanged(""))

        assertFalse(viewModel.uiState.value.isFormValid)
    }
}
