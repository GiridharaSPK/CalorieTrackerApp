package com.giridharaspk.core

sealed class UiEvent {
    data class Navigate(val route : String) : UiEvent()

    object NavigateUp : UiEvent()
}
