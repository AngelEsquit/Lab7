package com.example.lab7.ui.supermarket.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.lab7.navigation.AppBar
import com.example.lab7.navigation.NavigationState
import com.example.lab7.navigation.navigateTo
import com.example.lab7.ui.categories.viewmodel.MealsCategoriesViewModel
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SupermarketScreen(navController: NavController, viewModel: SupermarketViewModel = viewModel()) {

    /*val items = viewModel.supermarketItems.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.getAllItems()
    }*/

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
                    Text(text = "Agregar producto")
                }
            }

<<<<<<< Updated upstream
            /*items(items.value) { item ->
                Card {
                    Row {
                        Text(text = item.name)
                    }
                    Row {
                        Text(text = item.quantity)
                    }
                    Row {
                        Box {
                            Image(
                                painter = rememberImagePainter(
                                    data = item.imagePath
                                ),
                                contentDescription = "Imagen del teléfono",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }*/
=======
            items(items.value) {
                it.imagePath?.let { it1 -> showImage(it.name, it.quantity, it1, viewModel) }
            }
>>>>>>> Stashed changes
        }
    }
}