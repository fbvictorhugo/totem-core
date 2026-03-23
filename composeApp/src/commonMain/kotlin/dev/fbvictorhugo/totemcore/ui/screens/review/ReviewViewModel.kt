package dev.fbvictorhugo.totemcore.ui.screens.review

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class ReviewEvent {
    data class ObservationsChanged(val value: String) : ReviewEvent()
    data object NextClicked : ReviewEvent()
    data object BackClicked : ReviewEvent()
}

class ReviewViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ReviewUiState())
    val uiState: StateFlow<ReviewUiState> = _uiState.asStateFlow()

    fun onEvent(event: ReviewEvent) {
        when (event) {


            is ReviewEvent.ObservationsChanged -> _uiState.update { it.copy(observations = event.value) }

            ReviewEvent.NextClicked -> { /* TODO */
            }

            ReviewEvent.BackClicked -> { /* TODO */
            }

        }
    }
}