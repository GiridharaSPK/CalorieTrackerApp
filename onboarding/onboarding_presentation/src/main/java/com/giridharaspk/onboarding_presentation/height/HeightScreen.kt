package com.giridharaspk.onboarding_presentation.height

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.giridharaspk.core.R
import com.giridharaspk.core.util.UiEvent
import com.giridharaspk.core_ui.LocalSpacing
import com.giridharaspk.onboarding_presentation.components.ActionButton
import com.giridharaspk.onboarding_presentation.components.UnitTextField

@Composable
fun HeightScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: HeightViewModel = hiltViewModel()
    //scopes viewModel to the gender screen rather than navGraph of navHost
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) { // effect is to collect events received from viewModel
        // key1 = true - launch coroutines once - to listen to changes
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }

                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_height),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(LocalSpacing.current.spaceMedium))
            UnitTextField(
                value = viewModel.height,
                onValueChange = viewModel::onHeightEnter,
                unit = stringResource(id = R.string.cm)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}