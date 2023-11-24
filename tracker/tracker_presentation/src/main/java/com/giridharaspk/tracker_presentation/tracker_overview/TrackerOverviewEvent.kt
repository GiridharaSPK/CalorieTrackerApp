package com.giridharaspk.tracker_presentation.tracker_overview

import com.giridharaspk.tracker_domain.model.TrackableFood
import com.giridharaspk.tracker_domain.model.TrackedFood
import com.giridharaspk.tracker_presentation.model.Meal


sealed class TrackerOverviewEvent {
    data object OnNextDayClicked : TrackerOverviewEvent()
    data object OnPreviousDayClicked : TrackerOverviewEvent()
    data class OnToggleMealClicked(val meal: Meal) : TrackerOverviewEvent()
    data class OnDeleteTrackedFoodClicked(val trackedFood: TrackedFood) : TrackerOverviewEvent()
    data class OnAddFoodClicked(val meal: Meal) : TrackerOverviewEvent()
}

