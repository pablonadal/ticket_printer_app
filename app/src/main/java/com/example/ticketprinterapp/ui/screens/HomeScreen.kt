package com.example.ticketprinterapp.ui.screens

import com.example.ticketprinterapp.ui.components.BurgerItem
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ticketprinterapp.modelos.Burger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    burgers: List<Burger>,
    onEditBurger: (Burger) -> Unit,
    onPrintBurger: (Burger) -> Unit, // Nueva función para imprimir
    onAddBurger: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Gestión de Hamburguesas") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddBurger) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(burgers.size) { index ->
                BurgerItem(
                    burger = burgers[index],
                    onEditClick = onEditBurger,
                    onPrintClick = onPrintBurger // Llamado al nuevo botón
                )
            }
        }
    }
}
