package com.uncannyvalley.astralvpn.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LanguageButton(
    language: String,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    val borderColor = if (isSelected) Color.Transparent else MaterialTheme.colorScheme.primary
    val textColor = MaterialTheme.colorScheme.onPrimary

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .height(48.dp)
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(12.dp))
            .background(color = backgroundColor, shape = RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = language,
            color = textColor,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(vertical = 12.dp),
        )
    }
}

@Preview
@Composable
fun LanguageButtonPreview() {
    LanguageButton(language = "Russian") { }
}