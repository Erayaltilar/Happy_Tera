package com.happy_tera.presentation.similar.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happy_tera.core.Resource
import com.happy_tera.domain.model.series_model.Series
import com.happy_tera.domain.usecase.GetSimilarSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SimilarSeriesViewModel @Inject constructor(
    private val getSimilarSeriesUseCase: GetSimilarSeriesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SimilarSeriesScreenUIState())
    val uiState: StateFlow<SimilarSeriesScreenUIState> = _uiState.asStateFlow()

    fun getSimilarSeries(seriesId: Long) {
        getSimilarSeriesUseCase(seriesId).onEach {
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
                            series = it.data?.results ?: emptyList(),
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = false,
                            isHaveError = true,
                            isSuccess = false,
                            errorMessage = it.errorMessage.orEmpty(),
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


    data class SimilarSeriesScreenUIState(
        val loadingState: Boolean = false,
        val isHaveError: Boolean = false,
        val isSuccess: Boolean = false,
        val errorMessage: String = "",
        val series: List<Series> = emptyList(),
    )
}