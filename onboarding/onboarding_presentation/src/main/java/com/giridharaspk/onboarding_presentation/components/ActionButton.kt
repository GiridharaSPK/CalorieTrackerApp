package com.giridharaspk.onboarding_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.giridharaspk.core_ui.LocalSpacing

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    Button(
        onClick = { onClick() },
        enabled = isEnabled,
        modifier = modifier,
        shape = RoundedCornerShape(30.dp)
    ) {
        Text(
            text = text,
            style = textStyle,
            color = Color.White,
            modifier = Modifier.padding(LocalSpacing.current.spaceSmall)
        )
    }
}