package com.example.ticketprinterapp

import kotlinx.coroutines.flow.firstOrNull
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import com.example.ticketprinterapp.modelos.Burger
import com.example.ticketprinterapp.ui.screens.EditBurgerScreen
import com.example.ticketprinterapp.ui.screens.HomeScreen
import com.example.ticketprinterapp.ui.theme.TicketPrinterAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Estado para la lista de hamburguesas
        val burgers = mutableStateListOf<Burger>()

        // Cargar hamburguesas al inicio
        lifecycleScope.launch {
            val storedBurgers = applicationContext.burgerDataStore.data.firstOrNull() ?: emptyList()
            burgers.addAll(storedBurgers)
        }

        setContent {
            var selectedBurger by remember { mutableStateOf<Burger?>(null) }
            var showEditDialog by remember { mutableStateOf(false) }

            TicketPrinterAppTheme {
                HomeScreen(
                    burgers = burgers,
                    onEditBurger = { burger ->
                        selectedBurger = burger
                        showEditDialog = true
                    },
                    onPrintBurger = { burger ->
                        println("Imprimir: ${burger.name}")
                    },
                    onAddBurger = {
                        selectedBurger = Burger(0, "", "", 0.0)
                        showEditDialog = true
                    }
                )

                if (showEditDialog && selectedBurger != null) {
                    EditBurgerScreen(
                        burger = selectedBurger!!,
                        onDismiss = { showEditDialog = false },
                        onSave = { updatedBurger ->
                            lifecycleScope.launch {
                                if (updatedBurger.id == 0) {
                                    updatedBurger.id = (burgers.maxOfOrNull { it.id } ?: 0) + 1
                                    burgers.add(updatedBurger)
                                } else {
                                    val index = burgers.indexOfFirst { it.id == updatedBurger.id }
                                    if (index != -1) burgers[index] = updatedBurger
                                }
                                // Guardar hamburguesas actualizadas
                                applicationContext.burgerDataStore.updateData { burgers }
                            }
                            showEditDialog = false
                        }
                    )
                }
            }
        }
    }
}
