package com.example.android_training.presentation.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_training.core.Resource
import com.example.android_training.domain.model.movie_model.Movie
import com.example.android_training.domain.model.series_model.Series
import com.example.android_training.domain.usecase.GetDiscoverMoviesUseCase
import com.example.android_training.domain.usecase.GetDiscoverSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomepageScreenViewModel @Inject constructor(
    private val getDiscoverMoviesUseCase: GetDiscoverMoviesUseCase,
    private val getDiscoverSeriesUseCase: GetDiscoverSeriesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomepageScreenUIState())
    val uiState: StateFlow<HomepageScreenUIState> = _uiState.asStateFlow()


    init {
        getDiscoverMovies()
        getDiscoverSeries()
    }

    private fun getDiscoverSeries() {
        getDiscoverSeriesUseCase().onEach {
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
                            discoverSeries = it.data?.results,
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = false,
                            isHaveError = true,
                            isSuccess = false,
                            errorMessage = it.errorMessage.toString(),
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getDiscoverMovies() {
        getDiscoverMoviesUseCase().onEach {
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
                            discoverMovies = it.data?.results,
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = false,
                            isHaveError = true,
                            isSuccess = false,
                            errorMessage = it.errorMessage.toString(),
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    data class HomepageScreenUIState(
        val loadingState: Boolean = false,
        val isHaveError: Boolean = false,
        val isSuccess: Boolean = false,
        val errorMessage: String = "",
        val discoverMovies: List<Movie>? = null,
        val discoverSeries: List<Series>? = null,
    )
}