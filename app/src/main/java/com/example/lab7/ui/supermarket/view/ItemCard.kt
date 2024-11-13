package com.example.lab7.ui.supermarket.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.lab7.MyApp
import com.example.lab7.database.supermarket.SupermarketItemEntity
import com.example.lab7.navigation.NavigationState
import com.example.lab7.navigation.navigateTo
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModel
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ItemCard(navController: NavController, name: String, quantity: String, path: String, viewModel: SupermarketViewModel) {
    // /storage/emulated/0/Android/data/com.example.lab7/files/20241109_232854.jpg
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = androidx.compose.ui.graphics.Color.White)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Text(
                        text = "Nombre: $name",
                        fontSize = 18.sp,
                    )
                    Text(
                        text = "Cantidad: $quantity",
                        fontSize = 18.sp,
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val painter = rememberAsyncImagePainter(
                        model = path
                    )

                    Image(
                        painter = painter,
                        contentDescription = "Imagen",
                        modifier = Modifier
                            .height(250.dp)
                    )
                }
            }
            Row {
                Button(
                    onClick = {
                        viewModel.viewModelScope.launch(Dispatchers.IO) {
                            val itemDB = viewModel.getItemByAtributes(name, quantity, path)
                            withContext(Dispatchers.Main) {
                                viewModel.selectItem(itemDB)
                                navigateTo(navController, NavigationState.EditSupermarket.route)
                            }
                        }
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 16.dp)
                ) {
                    Text(text = "Editar")
                    Icon(imageVector = Icons.Filled.Edit, contentDescription = "Editar")
                }
                Button(
                    onClick = {
                        val item = SupermarketItemEntity(
                            name = name,
                            quantity = quantity, imagePath = path
                        )
                        viewModel.deleteItem(item.imagePath!!)
                    },
                    modifier = Modifier
                        .padding(start = 16.dp, bottom = 16.dp)
                ) {
                    Text(text = "Eliminar")
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun ItemCardPreview() {
    val applicationContext = LocalContext.current.applicationContext as MyApp
    val supermarketRepository = applicationContext.supermarketRepository
    val supermarketViewModel = SupermarketViewModelFactory(supermarketRepository).create(SupermarketViewModel::class.java)
    val navController = rememberNavController()

    val name = "Leche"
    val quantity = "1"
    val path = "/storage/emulated/0/Android/data/com.example.lab7/files/20241112_150022.jpg"

    val name2 = "Pan"
    val quantity2 = "2"
    val path2 = "/storage/emulated/0/Android/data/com.example.lab7/files/20241112_150022.jpg"

    Scaffold {
        LazyColumn {
            item {
                ItemCard(navController, name, quantity, path, supermarketViewModel)
            }
            item {
                ItemCard(navController, name2, quantity2, path2, supermarketViewModel)
            }
        }
    }
}