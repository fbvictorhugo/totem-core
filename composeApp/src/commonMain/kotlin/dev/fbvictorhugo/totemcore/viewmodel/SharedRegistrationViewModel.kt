package dev.fbvictorhugo.totemcore.viewmodel

import androidx.lifecycle.ViewModel
import dev.fbvictorhugo.totemcore.model.PersonalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class SharedRegistrationEvent {

    // Registration
    data class NameChanged(val value: String) : SharedRegistrationEvent()
    data class PhoneChanged(val value: String) : SharedRegistrationEvent()
    data class NeighborhoodChanged(val value: String) : SharedRegistrationEvent()
    data class EmailChanged(val value: String) : SharedRegistrationEvent()

    // Interests
    data class InterestClicked(val interest: String) : SharedRegistrationEvent()

    // Review
    data class ObservationsChanged(val value: String) : SharedRegistrationEvent()

    // Comuns
    data object NextClicked : SharedRegistrationEvent()
    data object BackClicked : SharedRegistrationEvent()
}

data class SharedRegistrationUiState(
    val personalData: PersonalData = PersonalData(),
    val selectedInterests: List<String> = emptyList(),
    val observations: String = "",
    val interests: List<String> = emptyList()
) {
    val isRegistrationValid: Boolean get() = personalData.name.isNotBlank() && personalData.phone.isNotBlank()
    val isInterestsSelected: Boolean get() = selectedInterests.isNotEmpty()

    fun getMessage(singular: String, plural: String): String {
        return if (selectedInterests.size == 1)
            "${selectedInterests.size} $singular"
        else
            "${selectedInterests.size} $plural"
    }
}

class SharedRegistrationViewModel(
    initialState: SharedRegistrationUiState = SharedRegistrationUiState()
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<SharedRegistrationUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(interests = mockInterestsList) }
    }

    fun onEvent(event: SharedRegistrationEvent) {
        when (event) {
            is SharedRegistrationEvent.NameChanged -> {
                _uiState.update { it.copy(personalData = it.personalData.copy(name = event.value)) }
            }

            is SharedRegistrationEvent.PhoneChanged -> {
                _uiState.update { it.copy(personalData = it.personalData.copy(phone = event.value)) }
            }

            is SharedRegistrationEvent.NeighborhoodChanged -> {
                _uiState.update { it.copy(personalData = it.personalData.copy(neighborhood = event.value)) }
            }

            is SharedRegistrationEvent.EmailChanged -> {
                _uiState.update { it.copy(personalData = it.personalData.copy(email = event.value)) }
            }

            is SharedRegistrationEvent.InterestClicked -> {
                _uiState.update { state ->
                    val newList = if (state.selectedInterests.contains(event.interest)) {
                        state.selectedInterests - event.interest
                    } else {
                        state.selectedInterests + event.interest
                    }
                    state.copy(selectedInterests = newList)
                }
            }

            is SharedRegistrationEvent.ObservationsChanged -> {
                _uiState.update { it.copy(observations = event.value) }
            }

            else -> {}
        }
    }
}

private val mockInterestsList = listOf(
    "Roupas (Adulto)", "Roupas Infantis", "Calçados",
    "Fraldas", "Cesta Básica", "Colchão", "Sofá", "Geladeira", "Fogão",
    "Móveis em Geral", "Eletrodomésticos", "Produtos de Higiene",
    "Água Potável", "Produtos de Limpeza", "Cobertores", "Material de Construção"
)
