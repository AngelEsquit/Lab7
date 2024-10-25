package com.example.lab7.ui.supermarket.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.lab7.MyApp
import com.example.lab7.R
import com.example.lab7.navigation.AppBar
import com.example.lab7.navigation.NavigationState
import com.example.lab7.navigation.navigateTo
import com.example.lab7.ui.categories.viewmodel.MealsCategoriesViewModel
import com.example.lab7.ui.supermarket.repositories.SupermarketRepository
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModel
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModelFactory

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

            items(items.value) {
                it.imagePath?.let { it1 -> ShowImage(it.name, it.quantity, it1, viewModel) }
            }
        }
    }
}