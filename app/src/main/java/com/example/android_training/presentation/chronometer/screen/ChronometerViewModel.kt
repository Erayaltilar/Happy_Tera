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
            while (_uiState.value.isRunning) {
                delay(10L)
                _uiState.value = _uiState.value.copy(timeInMilliseconds = _uiState.value.timeInMilliseconds + 10)
            }
        }
    }

    fun pauseChronometer() {
        _uiState.value = _uiState.value.copy(
            isRunning = false ,
            timeInMilliseconds = _uiState.value.timeInMilliseconds

        )
    }

    fun resetChronometer() {
        _uiState.value = _uiState.value.copy(isRunning = false, timeInMilliseconds = 0)
    }
}

data class ChronometerUIState(
    val isRunning: Boolean = false,
    val timeInMilliseconds : Int = 0,
    val loadingState: Boolean = false,
    val isHaveError: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String = "",
)