package com.giridharaspk.onboarding_presentation.nutrition_goal

sealed class NutritionGoalEvent {
    data class onCarbRatioChanged(val ratio: String) : NutritionGoalEvent()
    data class onFatRatioChanged(val ratio: String) : NutritionGoalEvent()
    data class onProteinRatioChanged(val ratio: String) : NutritionGoalEvent()
    data object onNextClicked : NutritionGoalEvent()
}
