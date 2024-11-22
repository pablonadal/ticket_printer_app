package com.example.ticketprinterapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ticketprinterapp.modelos.Burger

@Composable
fun EditBurgerScreen(
    burger: Burger,
    onDismiss: () -> Unit,
    onSave: (Burger) -> Unit
) {
    // Estado local para los campos del formulario
    var name by remember { mutableStateOf(burger.name) }
    var ingredients by remember { mutableStateOf(burger.ingredients) }
    var price by remember { mutableStateOf(burger.price.toString()) }

    // Contenedor principal
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Editar Hamburguesa") },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Campo de texto: Nombre de la hamburguesa
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Campo de texto: Ingredientes
                OutlinedTextField(
                    value = ingredients,
                    onValueChange = { ingredients = it },
                    label = { Text("Ingredientes") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Campo de texto: Precio
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    label = { Text("Precio") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                // Guardar cambios
                val updatedPrice = price.toDoubleOrNull() ?: 0.0
                onSave(burger.copy(name = name, ingredients = ingredients, price = updatedPrice))
            }) {
                Text("Guardar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
