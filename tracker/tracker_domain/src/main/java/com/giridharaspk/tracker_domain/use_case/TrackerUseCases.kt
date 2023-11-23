package com.giridharaspk.tracker_domain.use_case

data class TrackerUseCases( //Wrapper for all useCases in tracker_domain
    val trackFood: TrackFoodUseCase,
    val searchFood: SearchFoodUseCase,
    val getFoodsForDate: GetFoodsForDateUseCase,
    val deleteTrackedFood: DeleteTrackedFoodUseCase,
    val calculateMealNutrients: CalculateMealNutrientsUseCase
)