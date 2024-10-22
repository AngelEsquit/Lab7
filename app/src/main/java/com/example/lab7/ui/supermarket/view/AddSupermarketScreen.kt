package com.example.lab7.ui.supermarket.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lab7.database.supermarket.SupermarketItemEntity
import com.example.lab7.navigation.AppBar
import com.example.lab7.navigation.NavigationState
import com.example.lab7.navigation.navigateTo
import com.example.lab7.ui.supermarket.repositories.SupermarketRepository
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModel
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModelFactory

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SupermarketScreen(navController: NavController, photoPath: String = null.toString(), viewModel: SupermarketViewModel = viewModel()) {
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }

    Scaffold (
        topBar = {
            AppBar(title = "Supermarket", navController = navController)
        }
    ) {
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 90.dp)
            .padding(bottom = 30.dp)){
            item { // Nombre
                Box {
                    TextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        label = { Text("Ingrese el nombre") },
                        placeholder = { Text("Escribe aquí...") },
                        isError = itemName.isEmpty(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        singleLine = true
                    )
                }
            }

            item { // Cantidad
                Box (modifier = Modifier.padding(top = 32.dp)) {
                    TextField(
                        value = quantity,
                        onValueChange = { quantity = it },
                        label = { Text("Ingrese la cantidad") },
                        placeholder = { Text("Escribe aquí...") },
                        isError = quantity.isEmpty(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                }
            }

            item {
                Box(modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
                    Button(onClick = { navigateTo(navController, NavigationState.Camera.route) }) {
                        Text("Abrir cámara")
                    }
                }
            }

            item { // Botón
                Box (modifier = Modifier.padding(top = 32.dp)) {
                    Button(onClick = {
                        val item = SupermarketItemEntity(itemName,
                            quantity, photoPath)
                        viewModel.insertItem(item)
                        navigateTo(navController, NavigationState.Supermarket.route)

                    }) {
                        Text("Agregar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SupermarketScreenPreview() {
    SupermarketScreen(navController = rememberNavController())
}