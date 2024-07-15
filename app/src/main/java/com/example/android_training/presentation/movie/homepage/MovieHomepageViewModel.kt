package com.example.android_training.presentation.movie.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_training.core.Resource
import com.example.android_training.domain.model.message_model.Message
import com.example.android_training.domain.model.movie_model.Movie
import com.example.android_training.domain.usecase.GetRandomMessageUseCase
import com.example.android_training.domain.usecase.GetRandomMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieHomepageViewModel @Inject constructor(
    private val getRandomMoviesUseCase: GetRandomMoviesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieHomepageScreenUIState())
    val uiState: StateFlow<MovieHomepageScreenUIState> = _uiState.asStateFlow()

    init {
        getRandomMovies()
    }

    private fun getRandomMovies() {
        getRandomMoviesUseCase().onEach {
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
                            randomMovies = it.data,
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = false,
                            isHaveError = true,
                            isSuccess = false,
                            errorMessage = it.message.toString(),
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    data class MovieHomepageScreenUIState(
        val loadingState: Boolean = false,
        val isHaveError: Boolean = false,
        val isSuccess: Boolean = false,
        val errorMessage: String = "",
        val randomMovies: List<Movie>? = null,
    )
}