package com.example.android_training.presentation.homepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_training.core.Resource
import com.example.android_training.domain.model.Food
import com.example.android_training.domain.usecase.GetAllFoodsUseCase
import com.example.android_training.domain.usecase.GetFoodByIdUseCase
import com.example.android_training.domain.usecase.GetFoodByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val getAllFoodsUseCase: GetAllFoodsUseCase,
    private val getFoodByIdUseCase: GetFoodByIdUseCase,
    private val getFoodByNameUseCase: GetFoodByNameUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomepageScreenUIState())
    val uiState: StateFlow<HomepageScreenUIState> = _uiState.asStateFlow()

    init {
        //getFoodList()
        //getFoodById()
        //getFoodByName()
    }

    private fun getFoodList() {
        getAllFoodsUseCase().onEach {
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
                            foodList = it.data ?: emptyList(),
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = false,
                            isHaveError = true,
                            errorMessage = it.message.orEmpty(),
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getFoodById(id: String = "27906327547693") {
        getFoodByIdUseCase(id).onEach {
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
                            food = it.data,
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            loadingState = false,
                            isHaveError = true,
                            errorMessage = it.message.orEmpty(),
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getFoodByName(query: String) {
        getFoodByNameUseCase(query).onEach {
            when (it) {
                is Resource.Loading -> {
                    _uiState.update { state ->
                        state.copy(
                            isSearchLoading = true,
                            isSearchError = false,
                            isSearchSuccess = false,
                        )
                    }
                }

                is Resource.Success -> {
                    _uiState.update { state ->
                        state.copy(
                            isSearchLoading = false,
                            isSearchError = false,
                            isSearchSuccess = true,
                            foodList = it.data ?: emptyList(),
                        )
                    }
                }

                is Resource.Error -> {
                    _uiState.update { state ->
                        state.copy(
                            isSearchLoading = false,
                            isSearchError = true,
                            errorMessage = it.message.orEmpty(),
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
        val foodList: List<Food> = emptyList(),
        val isSearchLoading: Boolean = false,
        val isSearchSuccess : Boolean = false,
        val isSearchError: Boolean = false,
        val food: Food? = null,
    )
}