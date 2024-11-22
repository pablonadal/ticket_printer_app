package com.example.ticketprinterapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ticketprinterapp.modelos.Burger

@Composable
fun BurgerItem(
    burger: Burger,
    onEditClick: (Burger) -> Unit,
    onPrintClick: (Burger) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Información de la hamburguesa
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = burger.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "Ingredientes: ${burger.ingredients}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(text = "Precio: $${burger.price}", style = MaterialTheme.typography.bodyMedium)
            }

            // Botones para Editar e Imprimir
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { onEditClick(burger) },
                    modifier = Modifier.width(100.dp) // Ancho fijo
                ) {
                    Text("Editar")
                }
                Button(
                    onClick = { onPrintClick(burger) },
                    modifier = Modifier.width(110.dp) // Ancho fijo
                ) {
                    Text("Imprimir")
                }
            }
        }
    }
}
