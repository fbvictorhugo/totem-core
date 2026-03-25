package dev.fbvictorhugo.totemcore.ui.screens.registration

import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationEvent
import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationViewModel
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RegistrationViewModelTest {

    @Test
    fun `when NameChanged event is received, uiState name is updated`() {
        val viewModel = SharedRegistrationViewModel()
        val newName = "Victor Hugo"

        viewModel.onEvent(SharedRegistrationEvent.NameChanged(newName))

        assertEquals(newName, viewModel.uiState.value.personalData.name)
    }

    @Test
    fun `when PhoneChanged event is received, uiState phone is updated`() {
        val viewModel = SharedRegistrationViewModel()
        val newPhone = "11999999999"

        viewModel.onEvent(SharedRegistrationEvent.PhoneChanged(newPhone))

        assertEquals(newPhone, viewModel.uiState.value.personalData.phone)
    }

    @Test
    fun `when required fields are filled, isFormValid is true`() {
        val viewModel = SharedRegistrationViewModel()

        viewModel.onEvent(SharedRegistrationEvent.NameChanged("Victor"))
        viewModel.onEvent(SharedRegistrationEvent.PhoneChanged("123456"))

        assertTrue(viewModel.uiState.value.isRegistrationValid)
    }

    @Test
    fun `when required fields are empty, isFormValid is false`() {
        val viewModel = SharedRegistrationViewModel()

        viewModel.onEvent(SharedRegistrationEvent.NameChanged(""))
        viewModel.onEvent(SharedRegistrationEvent.PhoneChanged(""))

        assertFalse(viewModel.uiState.value.isRegistrationValid)
    }
}
