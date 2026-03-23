package dev.fbvictorhugo.totemcore.ui.screens.review

import kotlin.test.Test
import kotlin.test.assertEquals

class ReviewViewModelTest {

    @Test
    fun `when ObservationsChanged event is received, uiState observations is updated`() {
        val viewModel = ReviewViewModel()
        val newText = "Nova observação para o totem"

        viewModel.onEvent(ReviewEvent.ObservationsChanged(newText))

        assertEquals(newText, viewModel.uiState.value.observations)
    }

    @Test
    fun `when multiple ObservationsChanged events are received, uiState holds the latest value`() {
        val viewModel = ReviewViewModel()
        
        viewModel.onEvent(ReviewEvent.ObservationsChanged("Primeira"))
        viewModel.onEvent(ReviewEvent.ObservationsChanged("Segunda"))

        assertEquals("Segunda", viewModel.uiState.value.observations)
    }
}
