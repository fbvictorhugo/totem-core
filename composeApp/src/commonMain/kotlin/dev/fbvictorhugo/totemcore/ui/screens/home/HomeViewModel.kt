package dev.fbvictorhugo.totemcore.ui.screens.home

import androidx.lifecycle.ViewModel

sealed class HomeEvent {
    data object StartRegistrationClicked : HomeEvent()
}

class HomeViewModel : ViewModel() {

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.StartRegistrationClicked -> {
                /* TODO */

            }
        }
    }
}
