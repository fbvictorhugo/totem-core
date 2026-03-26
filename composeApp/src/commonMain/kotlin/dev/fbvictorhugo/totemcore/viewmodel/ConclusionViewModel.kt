package dev.fbvictorhugo.totemcore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Clock

sealed class ConclusionEvent {
    data object ConclusionClicked : ConclusionEvent()
}

data class ConclusionUiState(
    val progress: Float = 0f,
    val isFinished: Boolean = false,
    val remaining: Long = 0
)

class ConclusionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ConclusionUiState())
    val uiState: StateFlow<ConclusionUiState> = _uiState.asStateFlow()

    init {
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            val durationMillis = 10000L
            val startTime = Clock.System.now().toEpochMilliseconds()

            while (true) {
                val currentTime = Clock.System.now().toEpochMilliseconds()
                val elapsed = currentTime - startTime
                val progress = (elapsed.toFloat() / durationMillis).coerceIn(0f, 1f)
                val remainingSeconds = ((durationMillis - elapsed) / 1000) + 1

                _uiState.update {
                    it.copy(
                        progress = progress,
                        remaining = if (progress >= 1f) 0 else remainingSeconds,
                        isFinished = progress >= 1f
                    )
                }

                if (progress >= 1f) break
                delay(16L)
            }
        }
    }

    fun onEvent(event: ConclusionEvent) {
        when (event) {
            ConclusionEvent.ConclusionClicked -> {}
        }
    }
}
