package dev.fbvictorhugo.totemcore.navigation

import kotlinx.serialization.Serializable

sealed class Screens {
    @Serializable data object Home : Screens()
    @Serializable data object RegistrationWizard : Screens()
    @Serializable data object Registration : Screens()
    @Serializable data object Interests : Screens()
    @Serializable data object Review : Screens()
    @Serializable data object Conclusion : Screens()
}
