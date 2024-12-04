package com.example.android_training.presentation.chronometer.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChronometerViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(ChronometerUIState())
    val uiState: StateFlow<ChronometerUIState> = _uiState.asStateFlow()

    fun startChronometer() {
        if (_uiState.value.isRunning) return

        _uiState.value = _uiState.value.copy(isRunning = true)
        viewModelScope.launch {
            val startTime = System.currentTimeMillis() - _uiState.value.timeInMilliseconds
            while (_uiState.value.isRunning) {
                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - startTime

                _uiState.value = _uiState.value.copy(
                    isRunning = true,
                    timeInMilliseconds = elapsedTime,
                    formattedTime = formatTime(elapsedTime)
                )
                delay(10L)
            }
        }
    }

    private fun formatTime(timeInMilliseconds: Long): String {
        val hours = (timeInMilliseconds / 3600000) % 24
        val minutes = (timeInMilliseconds / 60000) % 60
        val seconds = (timeInMilliseconds / 1000) % 60
        val milliseconds = (timeInMilliseconds % 1000) / 10

        // Formatlı zamanı döndür: "00:00:00:00"
        return String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, milliseconds)
    }

    fun pauseChronometer() {
        _uiState.value = _uiState.value.copy(
            isRunning = false ,
            timeInMilliseconds = _uiState.value.timeInMilliseconds,
        )
    }

    fun resetChronometer() {
        _uiState.value = _uiState.value.copy(
            isRunning = false,
            timeInMilliseconds = 0L,
            formattedTime = "00:00:00:00",
        )
    }
}

data class ChronometerUIState(
    val isRunning: Boolean = false,
    val timeInMilliseconds : Long = 0L,
    val formattedTime: String = "00:00:00:00",
    val loadingState: Boolean = false,
    val isHaveError: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String = "",
)