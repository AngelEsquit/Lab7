package com.example.lab7.ui.supermarket.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lab7.MyApp
import com.example.lab7.database.supermarket.SupermarketItemEntity
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModel
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModelFactory


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ItemCard(name: String, quantity: String, path: String, viewModel: SupermarketViewModel) {
    // /storage/emulated/0/Android/data/com.example.lab7/files/20241109_232854.jpg
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Column {
            Button(
                onClick = {
                    val item = SupermarketItemEntity(
                        name = name,
                        quantity = quantity, imagePath = path
                    )
                    viewModel.deleteItem(item.imagePath!!)
                },
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
            ) {
                Text(text = "Eliminar")
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .background(color = androidx.compose.ui.graphics.Color.White)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                ) {
                    Text(text = "Nombre: $name")
                    Text(text = "Cantidad: $quantity")
                }

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(color = androidx.compose.ui.graphics.Color.Blue),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Imagen")

                    val painter = rememberAsyncImagePainter(
                        model = path
                    )

                    Image(
                        painter = painter,
                        contentDescription = "Imagen",
                        modifier = Modifier
                            .height(100.dp)
                    )
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

    val name = "Leche"
    val quantity = "1"
    val path = "/storage/emulated/0/Android/data/com.example.lab7/files/20241112_150022.jpg"

    val name2 = "Pan"
    val quantity2 = "2"
    val path2 = "/storage/emulated/0/Android/data/com.example.lab7/files/20241112_150022.jpg"

    Scaffold {
        LazyColumn {
            item {
                ItemCard(name, quantity, path, supermarketViewModel)
            }
            item {
                ItemCard(name2, quantity2, path2, supermarketViewModel)
            }
        }
    }
}