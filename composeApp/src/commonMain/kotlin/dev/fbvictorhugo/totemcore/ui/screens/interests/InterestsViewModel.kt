package dev.fbvictorhugo.totemcore.ui.screens.interests

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class InterestsEvent {
    data class InterestClicked(val interest: String) : InterestsEvent()
    data object NextClicked : InterestsEvent()
    data object BackClicked : InterestsEvent()
}

class InterestsViewModel(
    initialState: InterestsUiState = InterestsUiState()
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<InterestsUiState> = _uiState.asStateFlow()

    fun onEvent(event: InterestsEvent) {
        when (event) {
            is InterestsEvent.InterestClicked -> {
                _uiState.update { state ->
                    val currentSelected = state.selectedInterests
                    val newSelected = if (currentSelected.contains(event.interest)) {
                        currentSelected - event.interest
                    } else {
                        currentSelected + event.interest
                    }
                    state.copy(selectedInterests = newSelected)
                }
            }

            InterestsEvent.NextClicked -> { /* TODO */
            }

            InterestsEvent.BackClicked -> { /* TODO */
            }
        }
    }
}
