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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lab7.database.supermarket.SupermarketItemEntity
import com.example.lab7.navigation.AppBar
import com.example.lab7.navigation.NavigationState
import com.example.lab7.navigation.navigateTo
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditSupermarket (navController: NavController, viewModel: SupermarketViewModel) {
    var itemName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var imagePath by remember { mutableStateOf("") }
    var item by remember { mutableStateOf(SupermarketItemEntity(name = viewModel.getSelectedItem()?.name ?: "", quantity = viewModel.getSelectedItem()?.quantity ?: "", imagePath = viewModel.getSelectedItem()?.imagePath ?: "")) }

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
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)) {
                    Button(onClick = { navigateTo(navController, NavigationState.CameraEdit.route, NavigationState.AddSupermarket.route) }) {
                        Text("Abrir cámara")
                    }
                }
            }

            item { // Botón
                Box (modifier = Modifier.padding(top = 32.dp)) {
                    Button(onClick = {
                        item = SupermarketItemEntity(id = viewModel.getSelectedItem()?.id!!,
                            name = itemName,
                            quantity = quantity, imagePath = viewModel.getNewItem()?.imagePath ?: ""
                        )
                        viewModel.updateItem(item)
                        navigateTo(navController, NavigationState.Supermarket.route)
                    }) {
                        Text("Guardar")
                    }
                }
            }
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun SupermarketScreenPreview() {
    val navController = rememberNavController()
    SupermarketScreen(navController = navController, viewModel = viewModel { SupermarketViewModel(supermarketRepository) })
}*/