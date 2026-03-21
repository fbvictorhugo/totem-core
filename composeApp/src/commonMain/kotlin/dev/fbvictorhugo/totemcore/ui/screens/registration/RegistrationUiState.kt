package dev.fbvictorhugo.totemcore.ui.screens.registration

data class RegistrationUiState(
    val name: String = "",
    val phone: String = "",
    val neighborhood: String = "",
    val email: String = "",
) {
    val isFormValid: Boolean get() = name.isNotBlank() && phone.isNotBlank()
}
