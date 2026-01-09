package com.uncannyvalley.astralvpn.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uncannyvalley.astralvpn.presentation.theme.AstralVPNTheme
import com.uncannyvalley.astralvpn.presentation.theme.White

@Composable
fun SubscribeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(8.dp)

    val backgroundModifier = Modifier.background(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = shape
    )

    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clip(shape)
            .border(
                shape = shape,
                color = Color.White,
                width = 2.dp
            )
            .then(backgroundModifier)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .height(58.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = White
            )
        ) {
            Text(text, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Preview
@Composable
fun SubscribeButtonPreview() {
    AstralVPNTheme(darkTheme = true) {
        SubscribeButton(
            text = "Subscribe",
            onClick = {},
            modifier = Modifier,
        )
    }
}