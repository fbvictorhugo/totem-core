package dev.fbvictorhugo.totemcore.viewmodel

import androidx.lifecycle.ViewModel

sealed class HomeEvent {
    data object StartRegistrationClicked : HomeEvent()
}

class HomeViewModel : ViewModel() {

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.StartRegistrationClicked -> {}
        }
    }
}
