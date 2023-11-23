package com.giridharaspk.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giridharaspk.core.R
import com.giridharaspk.core.domain.preferences.Preferences
import com.giridharaspk.core.domain.use_case.FilterOutDigitsUseCase
import com.giridharaspk.core.navigation.Route
import com.giridharaspk.core.util.UiEvent
import com.giridharaspk.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterDigitsUseCase: FilterOutDigitsUseCase
) : ViewModel() {

    var age by mutableStateOf("20")
        private set


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAgeEnter(age: String) {
        if (age.length <= 3) {
//            this.age = age.filter { it.isDigit() } // this is a business logic - need to be put in useCases
            this.age = filterDigitsUseCase(age)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val ageNumber = age.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvent.ShowSnackBar(
                        UiText.StringResource(R.string.error_age_cant_be_empty)
                    )
                )
                return@launch
            }
            preferences.saveAge(ageNumber)
            _uiEvent.send(UiEvent.Navigate(Route.HEIGHT))
        }
    }

}