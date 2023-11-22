package com.giridharaspk.calorieTracker.navigation

import androidx.navigation.NavController
import com.giridharaspk.core.util.UiEvent

fun NavController.navigate(event : UiEvent.Navigate){
    this.navigate(event.route)
}