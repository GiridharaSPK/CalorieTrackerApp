package com.giridharaspk.onboarding_presentation.nutrition_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fetch.onboarding_domain.NutritionGoalValidationUseCase
import com.giridharaspk.core.domain.preferences.Preferences
import com.giridharaspk.core.domain.use_case.FilterOutDigitsUseCase
import com.giridharaspk.core.navigation.Route
import com.giridharaspk.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutritionGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigitsUseCase,
    private val validateNutrition: NutritionGoalValidationUseCase,
) : ViewModel() {
    var nutritionUiState by mutableStateOf(NutritionGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutritionGoalEvent) {
        when (event) {
            is NutritionGoalEvent.onCarbRatioChanged -> {
                nutritionUiState = nutritionUiState.copy(
                    carbsRatio = filterOutDigits(event.ratio)
                )
            }

            is NutritionGoalEvent.onFatRatioChanged -> {
                nutritionUiState = nutritionUiState.copy(
                    fatRatio = filterOutDigits(event.ratio)
                )
            }

            is NutritionGoalEvent.onProteinRatioChanged -> {
                nutritionUiState = nutritionUiState.copy(
                    proteinRatio = filterOutDigits(event.ratio)
                )
            }

            is NutritionGoalEvent.onNextClicked -> {
                viewModelScope.launch {
                    val result = validateNutrition(
                        nutritionUiState.carbsRatio,
                        nutritionUiState.fatRatio,
                        nutritionUiState.proteinRatio
                    )
                    when (result) {
                        is NutritionGoalValidationUseCase.ValidationResult.Error -> {
                            viewModelScope.launch {
                                _uiEvent.send(UiEvent.ShowSnackBar(result.errorMessage))
                            }
                        }

                        is NutritionGoalValidationUseCase.ValidationResult.Success -> {
                            viewModelScope.launch {
                                preferences.apply {
                                    saveCarbRatio(result.carbsRatio)
                                    saveFatRatio(result.fatRatio)
                                    saveProteinRatio(result.proteinRatio)
                                }
                                viewModelScope.launch {
                                    _uiEvent.send(
                                        UiEvent.Navigate(Route.TRACKER_OVERVIEW)
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}