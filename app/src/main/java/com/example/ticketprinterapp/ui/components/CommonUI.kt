package com.example.ticketprinterapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Botón personalizado reutilizable
@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = text)
    }
}

// Separador horizontal
@Composable
fun HorizontalDivider(modifier: Modifier = Modifier) {
    Divider(
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
        thickness = 1.dp,
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    )
}

// Título central reutilizable
@Composable
fun CenteredTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.titleLarge)
    }
}
