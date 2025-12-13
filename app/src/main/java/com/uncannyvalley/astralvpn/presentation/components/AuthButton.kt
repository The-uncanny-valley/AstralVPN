package com.uncannyvalley.astralvpn.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uncannyvalley.astralvpn.presentation.theme.MagentaRed
import com.uncannyvalley.astralvpn.presentation.theme.Purple
import com.uncannyvalley.astralvpn.presentation.theme.White

@Composable
fun AuthButton(
    text: String,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(8.dp)

    val backgroundModifier = if (enabled) {
        Modifier.background(
            brush = Brush.horizontalGradient(
                listOf(Purple, MagentaRed)
            ),
            shape = shape
        )
    } else {
        Modifier.background(
            color = MaterialTheme.colorScheme.errorContainer,
            shape = shape
        )
    }

    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clip(shape)
            .then(backgroundModifier)
    ) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                contentColor = White,
                disabledContentColor = White,
            )
        ) {
            Text(text, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Preview
@Composable
fun AuthButtonPreview() {
    AuthButton(
        text = "Sign up",
        enabled = true,
        onClick = {},
        modifier = Modifier,
    )
}