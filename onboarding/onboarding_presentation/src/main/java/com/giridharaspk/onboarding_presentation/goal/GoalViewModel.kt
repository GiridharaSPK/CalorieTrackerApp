package com.giridharaspk.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giridharaspk.core.domain.model.GoalType
import com.giridharaspk.core.domain.preferences.Preferences
import com.giridharaspk.core.navigation.Route
import com.giridharaspk.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {
    var selectedGoal by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    //todo check - exp with stateflow, sharedFlow
    private val _uiEvent = Channel<UiEvent> { }
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalSelected(goalType: GoalType) {
        selectedGoal = goalType
    }

    fun onNextClick() {
        //sending an event into channel is suspending operation
        viewModelScope.launch {
            preferences.saveGoalType(selectedGoal)
            _uiEvent.send(UiEvent.Navigate(Route.NUTRITION_GOAL))
        }
    }
}