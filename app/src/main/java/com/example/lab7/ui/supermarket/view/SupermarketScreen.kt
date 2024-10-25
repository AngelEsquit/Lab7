package com.example.lab7.ui.supermarket.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lab7.navigation.AppBar
import com.example.lab7.navigation.NavigationState
import com.example.lab7.navigation.navigateTo
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SupermarketScreen(navController: NavController, viewModel: SupermarketViewModel) {

    val items = viewModel.supermarketItems.observeAsState(initial = emptyList())


    LaunchedEffect(Unit) {
        viewModel.getAllItems()
    }

    Scaffold (
        topBar = {
            AppBar(title = "Supermarket", navController = navController)
        }
    ) {
        LazyColumn (modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 90.dp)
            .padding(bottom = 30.dp)) {

            item {
                Button(onClick = { navigateTo(navController, NavigationState.SupermarketCamera.route) }) {
                Text(text = "Agregar producto") }
            }

            items(items.value) {// Elementos de la lista de productos
                it.imagePath?.let { it1 -> ItemCard(it.name, it.quantity, it1, viewModel) }
            }
        }
    }
}