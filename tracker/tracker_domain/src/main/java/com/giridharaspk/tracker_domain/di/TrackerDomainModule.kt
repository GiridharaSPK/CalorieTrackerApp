package com.giridharaspk.tracker_domain.di

import com.giridharaspk.core.domain.preferences.Preferences
import com.giridharaspk.tracker_domain.repository.TrackerRepository
import com.giridharaspk.tracker_domain.use_case.CalculateMealNutrientsUseCase
import com.giridharaspk.tracker_domain.use_case.DeleteTrackedFoodUseCase
import com.giridharaspk.tracker_domain.use_case.GetFoodsForDateUseCase
import com.giridharaspk.tracker_domain.use_case.SearchFoodUseCase
import com.giridharaspk.tracker_domain.use_case.TrackFoodUseCase
import com.giridharaspk.tracker_domain.use_case.TrackerUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFoodUseCase(repository),
            searchFood = SearchFoodUseCase(repository),
            getFoodsForDate = GetFoodsForDateUseCase(repository),
            deleteTrackedFood = DeleteTrackedFoodUseCase(repository),
            calculateMealNutrients = CalculateMealNutrientsUseCase(preferences)
        )
    }
}