package com.fetch.onboarding_domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingModule {

    @Provides
    @ViewModelScoped
    fun providesNutritionGoalValidationUseCase() : NutritionGoalValidationUseCase {
        return NutritionGoalValidationUseCase()
    }
}