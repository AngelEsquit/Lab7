package com.example.lab7.ui.supermarket.view

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.lab7.MyApp
import com.example.lab7.database.supermarket.SupermarketItemEntity
import com.example.lab7.navigation.NavigationState
import com.example.lab7.navigation.navigateTo
import com.example.lab7.ui.supermarket.repositories.SupermarketRepository
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModel
import com.example.lab7.ui.supermarket.viewmodel.SupermarketViewModelFactory


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ShowImage(name: String, quantity: String, path: String, viewModel: SupermarketViewModel) {
    // /storage/emulated/0/Android/data/com.example.lab7/files/20241109_232854.jpg
    Card(
        modifier = Modifier
            .heightIn(min = 150.dp)
            .fillMaxWidth()
    ) {
        Column {
            Button(onClick = {
                val item = SupermarketItemEntity(
                    name = name,
                    quantity = quantity, imagePath = path
                )
                viewModel.deleteItem(item.imagePath!!)
            }) {

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
                        .fillMaxWidth()
                        .background(color = androidx.compose.ui.graphics.Color.Blue)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Imagen")

                    val painter = rememberAsyncImagePainter(
                        model = path
                    )

                    Image(
                        painter = painter,
                        contentDescription = "Imagen cargada desde el path",
                        modifier = Modifier.width(200.dp)
                    )

                    /*Image(
                        painter = rememberAsyncImagePainter(
                            model = item.imagePath
                        ),
                        contentDescription = "Imagen del teléfono",
                        modifier = Modifier.fillMaxSize()
                    )*/
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ShowImagePreview() {
    val applicationContext = LocalContext.current.applicationContext as MyApp
    val supermarketRepository = applicationContext.supermarketRepository
    val supermarketViewModel = SupermarketViewModelFactory(supermarketRepository).create(SupermarketViewModel::class.java)

    val name = "Leche"
    val quantity = "1"
    val path = "/storage/emulated/0/Android/data/com.example.lab7/files/20241109_232854.jpg"

    ShowImage(name, quantity, path, supermarketViewModel)
}