package com.fetch.onboarding_domain

import com.giridharaspk.core.R
import com.giridharaspk.core.util.UiText

class NutritionGoalValidationUseCase {
    operator fun invoke(
        carbsRatioText: String,
        proteinRatioText: String,
        fatRatioText: String
    ): ValidationResult {
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatRatio = fatRatioText.toIntOrNull()

        if (carbsRatio == null || proteinRatio == null || fatRatio == null)
            return ValidationResult.Error(
                UiText.StringResource(
                    R.string.error_invalid_values
                )
            )
        if (carbsRatio + proteinRatio + fatRatio != 100) {
            return ValidationResult.Error(
                UiText.StringResource(R.string.error_not_100_percent)
            )
        }

        return ValidationResult.Success(
            carbsRatio.toInt() / 100f,
            fatRatio.toInt() / 100f,
            proteinRatio.toInt() / 100f
        )
    }

    sealed class ValidationResult {
        data class Success(
            val carbsRatio: Float,
            val fatRatio: Float,
            val proteinRatio: Float
        ) : ValidationResult()

        data class Error(
            val errorMessage: UiText
        ) : ValidationResult()
    }

}