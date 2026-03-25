package dev.fbvictorhugo.totemcore.ui.screens.review

import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationEvent
import dev.fbvictorhugo.totemcore.viewmodel.SharedRegistrationViewModel
import kotlin.test.Test
import kotlin.test.assertEquals

class ReviewViewModelTest {

    @Test
    fun `when ObservationsChanged event is received, uiState observations is updated`() {
        val viewModel = SharedRegistrationViewModel()
        val newText = "Nova observação para o totem"

        viewModel.onEvent(SharedRegistrationEvent.ObservationsChanged(newText))

        assertEquals(newText, viewModel.uiState.value.observations)
    }

    @Test
    fun `when multiple ObservationsChanged events are received, uiState holds the latest value`() {
        val viewModel = SharedRegistrationViewModel()

        viewModel.onEvent(SharedRegistrationEvent.ObservationsChanged("Primeira"))
        viewModel.onEvent(SharedRegistrationEvent.ObservationsChanged("Segunda"))

        assertEquals("Segunda", viewModel.uiState.value.observations)
    }
}
