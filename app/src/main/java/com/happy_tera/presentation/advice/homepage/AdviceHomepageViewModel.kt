package com.happy_tera.presentation.advice.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happy_tera.core.Resource
import com.happy_tera.domain.model.message_model.Message
import com.happy_tera.domain.usecase.GetRandomMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AdviceHomepageViewModel @Inject constructor(
    private val getRandomMessageUseCase: GetRandomMessageUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AdviceHomepageScreenUIState())
    val uiState: StateFlow<AdviceHomepageScreenUIState> = _uiState.asStateFlow()

    init {
        getRandomMessage()
    }

    fun getRandomMessage() {
        getRandomMessageUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = true,
                            isHaveError = false,
                            isSuccess = false,
                        )
                    }
                }

                is Resource.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = false,
                            isHaveError = false,
                            isSuccess = true,
                            randomMessage = it.data,
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = false,
                            isHaveError = true,
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    data class AdviceHomepageScreenUIState(
        val loadingState: Boolean = false,
        val isHaveError: Boolean = false,
        val isSuccess: Boolean = false,
        val errorMessage: String = "",
        val randomMessage: Message? = null,
    )
}