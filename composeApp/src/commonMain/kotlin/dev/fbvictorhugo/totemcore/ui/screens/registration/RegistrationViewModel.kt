package dev.fbvictorhugo.totemcore.ui.screens.registration

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class RegistrationEvent {
    data class NameChanged(val value: String) : RegistrationEvent()
    data class PhoneChanged(val value: String) : RegistrationEvent()
    data class NeighborhoodChanged(val value: String) : RegistrationEvent()
    data class EmailChanged(val value: String) : RegistrationEvent()
    data object NextClicked : RegistrationEvent()
    data object BackClicked : RegistrationEvent()
}

class RegistrationViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()

    fun onEvent(event: RegistrationEvent) {
        when (event) {
            is RegistrationEvent.NameChanged -> _uiState.update { it.copy(name = event.value) }
            is RegistrationEvent.PhoneChanged -> _uiState.update { it.copy(phone = event.value) }
            is RegistrationEvent.NeighborhoodChanged -> _uiState.update { it.copy(neighborhood = event.value) }
            is RegistrationEvent.EmailChanged -> _uiState.update { it.copy(email = event.value) }

            RegistrationEvent.NextClicked -> { /* TODO */
            }

            RegistrationEvent.BackClicked -> { /* TODO */
            }
        }
    }

}