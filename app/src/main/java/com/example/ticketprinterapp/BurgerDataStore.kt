package com.example.ticketprinterapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.ticketprinterapp.modelos.Burger
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream

// Archivo donde se guardarán los datos
private const val DATASTORE_FILE_NAME = "burgers.json"

// DataStore configurado para guardar hamburguesas
val Context.burgerDataStore: DataStore<List<Burger>> by dataStore(
    fileName = DATASTORE_FILE_NAME,
    serializer = BurgerSerializer
)

// Serializador para guardar y leer las hamburguesas como JSON
object BurgerSerializer : Serializer<List<Burger>> {
    private val gson = Gson()
    private val type = object : TypeToken<List<Burger>>() {}.type

    override val defaultValue: List<Burger> = emptyList()

    override suspend fun readFrom(input: InputStream): List<Burger> {
        return try {
            val json = input.readBytes().decodeToString()
            gson.fromJson(json, type) ?: defaultValue
        } catch (e: Exception) {
            defaultValue
        }
    }

    override suspend fun writeTo(t: List<Burger>, output: OutputStream) {
        withContext(Dispatchers.IO) { // Garantiza un contexto seguro para E/S
            val json = gson.toJson(t)
            output.write(json.encodeToByteArray())
        }
    }
}
