package com.example.android_training.presentation.similar.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_training.core.Resource
import com.example.android_training.domain.model.movie_model.Movie
import com.example.android_training.domain.usecase.GetSimilarMoviesUseCase
import com.example.android_training.domain.usecase.GetSimilarSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SimilarMoviesViewModel @Inject constructor(
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SimilarMoviesScreenUIState())
    val uiState: StateFlow<SimilarMoviesScreenUIState> = _uiState.asStateFlow()

    fun getSimilarMovies(movieId: Long) {
        getSimilarMoviesUseCase(movieId).onEach {
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
                            movies = it.data?.results ?: emptyList(),
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

    data class SimilarMoviesScreenUIState(
        val loadingState: Boolean = false,
        val isHaveError: Boolean = false,
        val isSuccess: Boolean = false,
        val errorMessage: String = "",
        val movies: List<Movie> = emptyList(),
    )
}