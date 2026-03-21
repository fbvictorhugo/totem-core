package dev.fbvictorhugo.totemcore.ui.screens.registration

sealed class RegistrationEvent {
    data class NameChanged(val value: String) : RegistrationEvent()
    data class PhoneChanged(val value: String) : RegistrationEvent()
    data class NeighborhoodChanged(val value: String) : RegistrationEvent()
    data class EmailChanged(val value: String) : RegistrationEvent()
    data object NextClicked : RegistrationEvent()
    data object BackClicked : RegistrationEvent()
}